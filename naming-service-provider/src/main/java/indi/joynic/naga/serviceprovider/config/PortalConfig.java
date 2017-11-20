package indi.joynic.naga.serviceprovider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiaolei on 2017/11/20.
 */
@Configuration
public class PortalConfig {

    @Value("${naming.server.hosts}")
    private String namingServerHosts;

    public
}
