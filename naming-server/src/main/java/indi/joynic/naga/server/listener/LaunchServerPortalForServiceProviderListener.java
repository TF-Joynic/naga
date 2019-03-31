package indi.joynic.naga.server.listener;

import indi.joynic.naga.server.rpcserver.ThriftSimpleServerOfPortalForServiceProvider;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LaunchServerPortalForServiceProviderListener implements ApplicationListener<ApplicationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LaunchServerPortalForServiceProviderListener.class);

    @Resource
    private ThriftSimpleServerOfPortalForServiceProvider thriftSimpleServerOfPortalForServiceProvider;

    public void applicationReady(ApplicationReadyEvent applicationReadyEvent) {
        thriftSimpleServerOfPortalForServiceProvider.start();
    }

    public void contextClosed(ContextClosedEvent contextClosedEvent) {
        thriftSimpleServerOfPortalForServiceProvider.stop();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        logger.warn("onApplicationEvent: {}", applicationEvent.getClass().getSimpleName());
        if (applicationEvent instanceof ApplicationReadyEvent) {
            applicationReady((ApplicationReadyEvent) applicationEvent);
        } else if (applicationEvent instanceof ContextClosedEvent) {
            contextClosed((ContextClosedEvent) applicationEvent);
        }
    }
}
