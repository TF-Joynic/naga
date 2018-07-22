package indi.joynic.naga.serviceprovider.listener;

import indi.joynic.naga.lib.server.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import javax.annotation.Resource;

public class ThriftServerLaunchListener implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger
            = LoggerFactory.getLogger(ThriftServerLaunchListener.class);

    @Resource
    private RpcServer rpcServer;

    private void applicationReady(ApplicationReadyEvent applicationReadyEvent) {
        this.rpcServer.start();
    }

    private void contextClosed(ContextClosedEvent contextClosedEvent) {
        this.rpcServer.stop();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationReadyEvent) {
            applicationReady((ApplicationReadyEvent) applicationEvent);
        } else if (applicationEvent instanceof ContextClosedEvent) {
            contextClosed((ContextClosedEvent) applicationEvent);
        }
    }
}
