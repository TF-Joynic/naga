/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.protocol.thrift.impl;

import indi.joynic.naga.rpc.connection.thrift.ThriftRpcConnection;
import indi.joynic.naga.rpc.protocol.thrift.ThriftRpcProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class ThriftRpcProtocolImpl implements ThriftRpcProtocol {
    private static final Logger logger = LoggerFactory.getLogger(ThriftRpcProtocolImpl.class);

    private TProtocol inputProtocol;
    private TProtocol outputProtocol;

    public ThriftRpcProtocolImpl(Class<? extends TProtocol> inputProtocolClazz,
                                 Class<? extends TProtocol> outputProtocolClazz,
                                 ThriftRpcConnection rpcConnection)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        this.inputProtocol = inputProtocolClazz.getDeclaredConstructor(TTransport.class)
                .newInstance(rpcConnection.getConnection());

        this.outputProtocol = outputProtocolClazz.getDeclaredConstructor(TTransport.class)
                .newInstance(rpcConnection.getConnection());

    }

    public ThriftRpcProtocolImpl(Class<? extends TProtocol> rpcProtocolClazz, ThriftRpcConnection rpcConnection)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        this.inputProtocol = rpcProtocolClazz.getDeclaredConstructor(TTransport.class)
                .newInstance(rpcConnection.getConnection());

        this.outputProtocol = this.inputProtocol;
    }

    @Override
    public TProtocol getInputProtocol() {
        return inputProtocol;
    }

    @Override
    public TProtocol getOutputProtocol() {
        return outputProtocol;
    }
}
