/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.connection.thrift.impl;

import indi.joynic.naga.rpc.connection.thrift.ThriftRpcConnection;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ThriftRpcConnectionImpl implements ThriftRpcConnection {

    private static final Logger logger = LoggerFactory.getLogger(ThriftRpcConnectionImpl.class);

    private TTransport connection;

    public ThriftRpcConnectionImpl(Class<? extends TTransport> transportClazz, String host, int port, int timeout)
            throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {

        this.connection = transportClazz.getDeclaredConstructor(String.class, int.class, int.class)
                .newInstance(host, port, timeout);
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
    }

    @Override
    public void close() throws IOException {
        if (null != this.connection && this.connection.isOpen()) {
            this.connection.close();
        }
    }

}
