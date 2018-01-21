package indi.joynic.naga.serviceprovider.portal.server.client;

import indi.joynic.naga.rpc.client.thrift.ThriftRpcClient;
import indi.joynic.naga.rpc.client.thrift.impl.ThriftRpcClientImpl;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

public class ThriftNamingServerPortalClient implements ThriftNamingServerPortal.Iface {

    private ThriftRpcClient<ThriftNamingServerPortal.Client> rpcClient;

    public ThriftNamingServerPortalClient(Class<? extends TProtocol> rpcProtocolClazz,
                                          Class<? extends TTransport> transportClazz, String host, int port, int timeout) {

        rpcClient = new ThriftRpcClientImpl<>(ThriftNamingServerPortal.Client.class,
                rpcProtocolClazz, transportClazz, host, port, timeout);

    }

    @Override
    public boolean doRegister(String ns, String protocolType, String serviceName,
                              String host, int port, int weight) throws TException {

        return rpcClient.getClient().doRegister(ns, protocolType, serviceName, host, port, weight);
    }
}
