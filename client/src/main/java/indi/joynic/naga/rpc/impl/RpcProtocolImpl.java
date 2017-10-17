package indi.joynic.naga.rpc.impl;

import indi.joynic.naga.rpc.RpcConnection;
import indi.joynic.naga.rpc.RpcProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;


public class RpcProtocolImpl implements RpcProtocol {
    private static final Logger logger = LoggerFactory.getLogger(RpcProtocolImpl.class);

    private TProtocol inputProtocol;
    private TProtocol outputProtocol;

    public RpcProtocolImpl(Class<? extends TProtocol> inputProtocolClazz,
                           Class<? extends TProtocol> outputProtocolClazz, RpcConnection rpcConnection) {

        try {
            this.inputProtocol = inputProtocolClazz.getDeclaredConstructor(TTransport.class).newInstance(rpcConnection.getConnection());
            this.outputProtocol = outputProtocolClazz.getDeclaredConstructor(TTransport.class).newInstance(rpcConnection.getConnection());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public RpcProtocolImpl(Class<? extends TProtocol> rpcProtocolClazz, RpcConnection rpcConnection) {
        try {
            this.inputProtocol = rpcProtocolClazz.getDeclaredConstructor(TTransport.class).newInstance(rpcConnection.getConnection());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        logger.info("using same input & output protocol");
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
