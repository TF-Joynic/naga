package indi.joynic.naga.serviceprovider.portal.server.client;

import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import indi.joynic.naga.rpc.client.thrift.ThriftRpcClient;
import indi.joynic.naga.rpc.connection.thrift.ThriftRpcConnection;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftNamingServerPortalClient implements ThriftNamingServerPortal.Iface {

    private static final Logger logger = LoggerFactory.getLogger(ThriftNamingServerPortalClient.class);

    private ThriftRpcClient<ThriftNamingServerPortal.Client> rpcClient;

    public ThriftNamingServerPortalClient(ThriftRpcClient<ThriftNamingServerPortal.Client> rpcClient) {
        this.rpcClient = rpcClient;
    }

    @Override
    public boolean doRegister(String ns, String protocolType, String serviceName,
                              String host, int port, int weight) throws TException {

        try (ThriftRpcConnection connection = rpcClient.getConnection()) {
            return rpcClient.getClient().doRegister(ns, protocolType, serviceName, host, port, weight);
        } catch (Exception e) {
            logger.error("call doRegister err! msg: {}", e);
        }

        return false;
    }

}
