package indi.joynic.naga.client.rpc.impl;

import indi.joynic.naga.client.rpc.RpcConnection;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaolei on 2017/10/10.
 */
public class RpcConnectionImpl implements RpcConnection {

    private static final Logger logger = LoggerFactory.getLogger(RpcConnectionImpl.class);

    private TTransport connection;

    public RpcConnectionImpl(String host, int port, int timeout) {
        this.connection = new TSocket(host, port, timeout);
    }

    @Override
    public TTransport getConnection() {
        return connection;
    }

    @Override
    public void open() throws Exception {
        if (connection.isOpen()) {
            return ;
        }

        connection.open();
        logger.warn("conn opened");
    }

    @Override
    public void close() throws Exception {
        if (null != this.connection && this.connection.isOpen()) {
            this.connection.close();

            logger.warn("conn closed");
        }
    }

}
