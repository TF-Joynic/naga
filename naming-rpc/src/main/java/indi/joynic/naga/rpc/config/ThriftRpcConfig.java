package indi.joynic.naga.rpc.config;

import indi.joynic.naga.rpc.config.enums.RpcMode;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

public class ThriftRpcConfig extends RpcConfig {

    private RpcMode rpcMode = RpcMode.THRIFT;

    private Class<? extends TServiceClient> clientClazz;
    private Class<? extends TProtocol> protocolClazz;
    private Class<? extends TTransport> transportClazz;

    public Class<? extends TServiceClient> getClientClazz() {
        return clientClazz;
    }

    public void setClientClazz(Class<? extends TServiceClient> clientClazz) {
        this.clientClazz = clientClazz;
    }

    public Class<? extends TProtocol> getProtocolClazz() {
        return protocolClazz;
    }

    public void setProtocolClazz(Class<? extends TProtocol> protocolClazz) {
        this.protocolClazz = protocolClazz;
    }

    public Class<? extends TTransport> getTransportClazz() {
        return transportClazz;
    }

    public void setTransportClazz(Class<? extends TTransport> transportClazz) {
        this.transportClazz = transportClazz;
    }
}
