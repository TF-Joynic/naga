package indi.joynic.naga.server.config;

import indi.joynic.naga.server.rpcserver.ThriftSimpleServerOfPortalForServiceProvider;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Value("${naming.server.serviceprovider.port}")
    private Integer serverPort;

    @Bean
    public ThriftSimpleServerOfPortalForServiceProvider thriftSimpleServerOfPortalForServiceProvider() {
        return new ThriftSimpleServerOfPortalForServiceProvider(serverPort, new TBinaryProtocol.Factory());
    }
}
