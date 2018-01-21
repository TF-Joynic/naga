package indi.joynic.naga.serviceprovider.config;

import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerPortalAccessor;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerSubjectWithThrift;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortalConfig {

    @Value("${naming.server.hosts}")
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

    /**
     * TODO: getConnection(connpool, pick connection should apply LB rule)
     *
     * not bean
     *
     * @return
     */
    @Bean
    public RegisterOnServerPortalAccessor registerOnServerPortalAccessor() {

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
            throw new BeanInstantiationException(RegisterOnServerPortalAccessor.class, "invalid node weight: "+ weight);
        }

        accessArgs.setWeight(weight);

        // TODO: fix me
        return null;

        /*ThriftNamingServerPortal.Iface client = new ThriftNamingServerPortal.Client();

        return new RegisterOnServerPortalAccessor(accessArgs, );*/
    }
}
