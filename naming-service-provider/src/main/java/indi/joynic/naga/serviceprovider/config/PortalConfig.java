package indi.joynic.naga.serviceprovider.config;

import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import indi.joynic.naga.rpc.client.thrift.ThriftRpcClient;
import indi.joynic.naga.rpc.client.thrift.impl.ThriftRpcClientBuilder;
import indi.joynic.naga.rpc.config.ThriftRpcConfig;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortalConfig {

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


    private ThriftRpcConfig thriftRpcConfig() {

        ThriftRpcConfig thriftRpcConfig = new ThriftRpcConfig();
        thriftRpcConfig.setClientClazz(ThriftNamingServerPortal.Client.class);
        thriftRpcConfig.setProtocolClazz(TBinaryProtocol.class);
        thriftRpcConfig.setTransportClazz(TSocket.class);

        String[] hostPortArr = SocketAddrUtil.splitHostPort(hosts);
        thriftRpcConfig.setHost(hostPortArr[0]);
        thriftRpcConfig.setPort(Integer.valueOf(hostPortArr[1]));
        thriftRpcConfig.setTimeoutMillis(registerTimeout);

        return thriftRpcConfig;
    }

    @Bean
    public ThriftRpcClient<ThriftNamingServerPortal.Client> thriftNamingServerPortalClient() {

        ThriftRpcClientBuilder<ThriftNamingServerPortal.Client> thriftRpcClientBuilder
                = new ThriftRpcClientBuilder<>(thriftRpcConfig());

        ThriftRpcClient<ThriftNamingServerPortal.Client> thriftRpcClient = null;

        try {
            thriftRpcClient = thriftRpcClientBuilder.build();
        } catch (Exception e) {
            return null;
        }

        return thriftRpcClient;
    }

}
