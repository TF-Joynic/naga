package indi.joynic.naga.server.rpcserver;

import indi.joynic.naga.lib.server.AbstractServer;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Rpc Server encapsulation of Thrift Simple Server
 *
 * @author Terrance Fung 2018-7-22
 */
public class ThriftSimpleServerOfPortalForServiceProvider extends AbstractServer {
    private static final Logger logger = LoggerFactory.getLogger(ThriftSimpleServerOfPortalForServiceProvider.class);

    private int serverPort;
    private TProtocolFactory protocolFactory;

    private static TServer tServer = null;

    @Resource(name = "serverPortalForServiceProvider")
    private ThriftNamingServerPortal.Iface serverPortalForServiceProvider;

    public ThriftSimpleServerOfPortalForServiceProvider(int serverPort, TProtocolFactory protocolFactory) {
        this.serverPort = serverPort;
        this.protocolFactory = protocolFactory;
    }

    @Override
    protected void doStart() {
        TProcessor tprocessor = new ThriftNamingServerPortal.Processor<>(
                serverPortalForServiceProvider);

        TServerTransport serverTransport = null;
        try {
            serverTransport = new TServerSocket(serverPort);

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);

            // Thrift SimpleServer & BinaryProtocol for test
            tArgs.protocolFactory(protocolFactory/*new TBinaryProtocol.Factory()*/);
            tServer = new TSimpleServer(tArgs);

            logger.warn("ThriftSimpleServerOfPortalForServiceProvider started and try serve at port: {}", serverPort);
            tServer.serve();
        } catch (TTransportException e) {
            logger.error("ThriftSimpleServerOfPortalForServiceProvider start failed!", e);
        }
    }

    @Override
    public boolean isStarted() {
        return tServer.isServing();
    }

    @Override
    protected void doStop() {
        tServer.stop();
    }
}
