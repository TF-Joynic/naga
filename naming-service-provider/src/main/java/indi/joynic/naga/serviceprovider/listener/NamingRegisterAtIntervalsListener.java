package indi.joynic.naga.serviceprovider.listener;

import indi.joynic.naga.lib.utils.DateTimeUtil;
import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerSubjectWithThrift;
import indi.joynic.naga.serviceprovider.portal.server.client.ThriftNamingServerPortalClient;
import indi.joynic.naga.serviceprovider.task.NamingRegisterTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NamingRegisterAtIntervalsListener implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(NamingRegisterAtIntervalsListener.class);

    private static final ScheduledExecutorService registerScheduledThreadPool
            = Executors.newSingleThreadScheduledExecutor();

    private ThriftNamingServerPortalClient thriftNamingServerPortalClient;
    private RegisterOnServerSubjectWithThrift.AccessArgs accessArgs;
    private Long registerInterval;

    public NamingRegisterAtIntervalsListener(ThriftNamingServerPortalClient thriftNamingServerPortalClient,
                                             RegisterOnServerSubjectWithThrift.AccessArgs accessArgs, Long registerInterval) {

        this.thriftNamingServerPortalClient = thriftNamingServerPortalClient;
        this.accessArgs = accessArgs;
        this.registerInterval = registerInterval;
    }

    public static RegisterOnServerSubjectWithThrift.AccessArgs buildAccessArgs(String namespace,
                                                                               String protocolType,
                                                                               String serviceName,
                                                                               Integer port,
                                                                               Integer weight
                                                                               ) {

        if (StringUtils.isEmpty(namespace) || StringUtils.isEmpty(protocolType)
                || StringUtils.isEmpty(serviceName) || null == weight) {

            throw new IllegalArgumentException(
                    "invalid RegisterOnServerPortalAccessor initiate args -> ns: "+ namespace
                            + ", protocolType: " + protocolType
                            + " or serviceName: " + serviceName + " or weight: " + weight);

        }

        RegisterOnServerSubjectWithThrift.AccessArgs accessArgs
                = new RegisterOnServerSubjectWithThrift.AccessArgs();

        accessArgs.setNamespace(namespace);
        accessArgs.setProtocolType(protocolType.toUpperCase());
        accessArgs.setServiceName(serviceName);

        accessArgs.setHost(SocketAddrUtil.getIntranetIp());

        SocketAddrUtil.checkPort(port);
        accessArgs.setPort(port);
        accessArgs.setWeight(weight);

        return accessArgs;
    }

    private void applicationReady(ApplicationReadyEvent applicationReadyEvent) {

        NamingRegisterTask namingRegisterTask
                = new NamingRegisterTask(accessArgs, thriftNamingServerPortalClient);

        registerScheduledThreadPool
                .scheduleAtFixedRate(namingRegisterTask, 5000L,
                        registerInterval, TimeUnit.MILLISECONDS);

        if (logger.isInfoEnabled()) {

            logger.info("register listener initialized! "
                    + DateTimeUtil.date(DateTimeUtil.DATETIME_MILLIS));

        }
    }

    public void contextStopped(ContextStoppedEvent contextStoppedEvent) {
        registerScheduledThreadPool.shutdown();

        if (logger.isInfoEnabled()) {

            logger.info("register listener destroyed! "
                    + DateTimeUtil.date(DateTimeUtil.DATETIME_MILLIS));

        }
    }


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationReadyEvent) {
            applicationReady((ApplicationReadyEvent) applicationEvent);
        } else if (applicationEvent instanceof ContextStoppedEvent) {
            contextStopped((ContextStoppedEvent) applicationEvent);
        }
    }
}
