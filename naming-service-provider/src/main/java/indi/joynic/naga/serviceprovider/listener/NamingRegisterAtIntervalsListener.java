package indi.joynic.naga.serviceprovider.listener;

import indi.joynic.naga.lib.utils.DateTimeUtil;
import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerSubjectWithThrift;
import indi.joynic.naga.serviceprovider.portal.server.client.ThriftNamingServerPortalClient;
import indi.joynic.naga.serviceprovider.task.NamingRegisterAtIntervalsTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NamingRegisterAtIntervalsListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(NamingRegisterAtIntervalsListener.class);

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

    /*@Value("${naming.server.hosts}")
    private String hosts;

    @Value("${naming.register.interval}")
    private Long registerInterval;

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
    private ThriftNamingServerPortalClient thriftNamingServerPortalClient;*/

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        NamingRegisterAtIntervalsTask namingRegisterAtIntervalsTask
                = new NamingRegisterAtIntervalsTask(accessArgs, thriftNamingServerPortalClient, registerInterval);

        Thread namingRegisterAtIntervalsThread = new Thread(namingRegisterAtIntervalsTask);
        namingRegisterAtIntervalsThread.setName("namingRegisterAtIntervals");
        namingRegisterAtIntervalsThread.setDaemon(true);
        namingRegisterAtIntervalsThread.start();

        logger.info("namingRegisterAtIntervalsThread started! " + DateTimeUtil.date(DateTimeUtil.DEFAULT_PATTERN));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("namingRegisterAtIntervalsThread stop! " + DateTimeUtil.date(DateTimeUtil.DEFAULT_PATTERN));
    }
}
