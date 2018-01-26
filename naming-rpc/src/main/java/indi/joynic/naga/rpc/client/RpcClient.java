package indi.joynic.naga.rpc.client;

import indi.joynic.naga.rpc.connection.RpcConnection;

import java.io.Closeable;

public interface RpcClient extends Closeable {
    RpcConnection getConnection() throws Exception;
}
