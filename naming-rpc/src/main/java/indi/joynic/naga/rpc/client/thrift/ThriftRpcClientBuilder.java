package indi.joynic.naga.rpc.client.thrift;

import indi.joynic.naga.rpc.client.RpcClientBuilder;

public interface ThriftRpcClientBuilder extends RpcClientBuilder {
    ThriftRpcClient build();
}
