package indi.joynic.naga.rpc.client.thrift.impl;

import indi.joynic.naga.rpc.client.RpcClientBuilder;
import indi.joynic.naga.rpc.client.thrift.ThriftRpcClient;
import indi.joynic.naga.rpc.config.ThriftRpcConfig;
import org.apache.thrift.TServiceClient;

public class ThriftRpcClientBuilder<Client extends TServiceClient> implements RpcClientBuilder {
    private ThriftRpcConfig thriftRpcConfig;

    public ThriftRpcClientBuilder(ThriftRpcConfig thriftRpcConfig) {
        this.thriftRpcConfig = thriftRpcConfig;
    }

    @Override
    public ThriftRpcClient<Client> build() throws Exception {

        return new ThriftRpcClientImpl<>(thriftRpcConfig.getClientClazz(),
                thriftRpcConfig.getProtocolClazz(),
                thriftRpcConfig.getTransportClazz(), thriftRpcConfig.getHost(),
                thriftRpcConfig.getPort(), thriftRpcConfig.getTimeoutMillis());

    }
}
