package indi.joynic.naga.serviceprovider.listener;

import indi.joynic.naga.lib.server.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

import javax.annotation.Resource;

public class ThriftServerLaunchListener implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger
            = LoggerFactory.getLogger(ThriftServerLaunchListener.class);

    @Resource
    private RpcServer rpcServer;

    private void contextStarted(ContextStartedEvent contextStartedEvent) {
        this.rpcServer.start();
    }

    private void contextStopped(ContextStoppedEvent contextStoppedEvent) {
        this.rpcServer.stop();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextStartedEvent) {
            contextStarted((ContextStartedEvent) applicationEvent);
        } else if (applicationEvent instanceof ContextStoppedEvent) {
            contextStopped((ContextStoppedEvent) applicationEvent);
        }
    }
}
