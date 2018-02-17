package indi.joynic.naga.server.listener;

import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicBoolean;

public class LaunchServerPortalForServiceProviderListener implements ApplicationListener<ApplicationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LaunchServerPortalForServiceProviderListener.class);

    @Value("${naming.server.serviceprovider.port}")
    private Integer serverPort;

    @Resource
    private ThriftNamingServerPortal.Iface serverPortalForServiceProvider;

    private static TServer tServer = null;
    private static final AtomicBoolean serverStarted = new AtomicBoolean(false);

    public void contextStarted(ContextStartedEvent contextStartedEvent) {

        TProcessor tprocessor = new ThriftNamingServerPortal.Processor<>(
                serverPortalForServiceProvider);

        TServerSocket serverTransport = null;
        try {
            serverTransport = new TServerSocket(serverPort);

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);

            // Thrift SimpleServer & BinaryProtocol for test
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tServer = new TSimpleServer(tArgs);

            serverStarted.compareAndSet(false, true);

            logger.warn("LaunchServerPortalForServiceProviderListener started");
            tServer.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public void contextStopped(ContextStoppedEvent contextStoppedEvent) {
        if (serverStarted.get() && null != tServer) {
            tServer.stop();
        }
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
