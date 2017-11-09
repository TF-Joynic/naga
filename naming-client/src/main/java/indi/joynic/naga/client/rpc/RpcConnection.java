package indi.joynic.naga.client.rpc;

import org.apache.thrift.transport.TTransport;

/**
 * Created by xiaolei on 2017/10/10.
 */
public interface RpcConnection extends AutoCloseable {
    TTransport getConnection();

    void open() throws Exception;
}