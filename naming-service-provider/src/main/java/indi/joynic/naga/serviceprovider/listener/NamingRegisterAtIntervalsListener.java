package indi.joynic.naga.serviceprovider.listener;

import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerPortalAccessor;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerSubjectWithThrift;
import indi.joynic.naga.serviceprovider.portal.server.client.ThriftNamingServerPortalClient;
import indi.joynic.naga.serviceprovider.task.NamingRegisterAtIntervalsTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NamingRegisterAtIntervalsListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(NamingRegisterAtIntervalsListener.class);

    @Value("${naming.server.hosts}")
    private String hosts;

    @Value("${naming.register.interval}")
    private Long registerInterval;

    @Value("${naming.register.timeout}")
    private int registerTimeout;

    @Value("${naming.serviceprovider.namespace}")
    private String namespace;

    @Value("${naming.serviceprovider.protocoltype}")
    private String protocolType;

    @Value("${naming.serviceprovider.servicename}")
    private String serviceName;

    @Value("${naming.serviceprovider.port}")
    private Integer port;

    @Value("${naming.serviceprovider.weight}")
    private Integer weight;

    @Resource
    private ThriftNamingServerPortalClient thriftNamingServerPortalClient;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        // TODO
        if (StringUtils.isEmpty(namespace) || StringUtils.isEmpty(protocolType)
                || StringUtils.isEmpty(serviceName)) {

            throw new BeanInstantiationException(RegisterOnServerPortalAccessor.class,
                    "invalid RegisterOnServerPortalAccessor initiate args -> ns: "+ namespace
                            + ", protocolType: " + protocolType + " or serviceName: " + serviceName);

        }

        RegisterOnServerSubjectWithThrift.AccessArgs accessArgs
                = new RegisterOnServerSubjectWithThrift.AccessArgs();

        accessArgs.setNamespace(namespace);
        accessArgs.setProtocolType(protocolType.toUpperCase());
        accessArgs.setServiceName(serviceName);

        accessArgs.setHost(SocketAddrUtil.getIntranetIp());

        SocketAddrUtil.checkPort(port);
        accessArgs.setPort(port);

        if (null == weight || 0 == weight) {
            logger.error("invalid node weight: "+ weight);
        }

        accessArgs.setWeight(weight);

        NamingRegisterAtIntervalsTask namingRegisterAtIntervalsTask
                = new NamingRegisterAtIntervalsTask(accessArgs, thriftNamingServerPortalClient, registerInterval);

        Thread namingRegisterAtIntervalsThread = new Thread(namingRegisterAtIntervalsTask);
        namingRegisterAtIntervalsThread.setName("namingRegisterAtIntervals");
        namingRegisterAtIntervalsThread.setDaemon(true);
        namingRegisterAtIntervalsThread.start();

        logger.info("namingRegisterAtIntervalsThread started! " + System.currentTimeMillis());
    }
}
