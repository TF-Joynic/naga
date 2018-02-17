package indi.joynic.naga.server.config;

import indi.joynic.naga.server.listener.LaunchServerPortalForServiceProviderListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {

    @Bean
    public LaunchServerPortalForServiceProviderListener launchServerPortalForServiceProviderListener() {
        return new LaunchServerPortalForServiceProviderListener();
    }


}
