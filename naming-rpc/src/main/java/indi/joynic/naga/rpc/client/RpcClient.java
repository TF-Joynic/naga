package indi.joynic.naga.rpc.client;

import indi.joynic.naga.rpc.connection.RpcConnection;

public interface RpcClient {
    RpcConnection getConnection();
}
