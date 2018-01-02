package indi.joynic.naga.client.rpc;

import org.apache.thrift.transport.TTransport;

public interface RpcConnection extends AutoCloseable {
    TTransport getConnection();

    void open() throws Exception;
}
