package indi.joynic.naga.server.listener;

import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LaunchServerPortalForServiceProviderListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${naming.server.serviceprovider.port}")
    private Integer serverPort;

    @Resource
    private ThriftNamingServerPortal.Iface serverPortalForServiceProvider;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        TProcessor tprocessor = new ThriftNamingServerPortal.Processor<>(
                serverPortalForServiceProvider);

        TServerSocket serverTransport = null;
        try {
            serverTransport = new TServerSocket(serverPort);

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);

            // Thrift SimpleServer & BinaryProtocol for test
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
