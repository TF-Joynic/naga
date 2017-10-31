package indi.joynic.naga.server.client.rpc.impl;

import indi.joynic.naga.server.client.rpc.RpcClient;
import indi.joynic.naga.server.client.rpc.RpcConnection;
import indi.joynic.naga.server.client.rpc.RpcProtocol;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RpcClientImpl<Client extends TServiceClient> implements RpcClient<Client> {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientImpl.class);

    private static final String THRIFT_SERVICE_CLIENT_FACTORY_CLASS_NAME = "Factory";
    private static final String THRIFT_SERVICE_CLIENT_FACTORY_METHOD_NAME = "getClient";

    private RpcConnection connection;
    private RpcProtocol protocol;
    private Client client;

    private Class<? extends TServiceClient> clientClazz;

    public RpcClientImpl(Class<? extends TServiceClient> clientClazz, Class<? extends TProtocol> rpcProtocolClazz, String host, int port, int timeout) {
        this.connection = new RpcConnectionImpl(host, port, timeout);
        this.protocol = new RpcProtocolImpl(rpcProtocolClazz, connection);
        this.clientClazz = clientClazz;
    }

    public Client getClient() {
        if (null != client) {
            return client;
        }

        getServiceClient();
        return client;
    }

    @SuppressWarnings("unchecked")
    private void getServiceClient() {
        try {
            Class<?>[] subs = clientClazz.getClasses();
            if (null == subs) {
                throw new NullPointerException("Thrift service client Factory clazz NOT found!");
            }

            for (Class<?> sub : subs) {

                logger.info("got Factory clazz: {}", sub.getName());
                try {
                    Method targetMethod = sub.getMethod(THRIFT_SERVICE_CLIENT_FACTORY_METHOD_NAME, TProtocol.class, TProtocol.class);
                    if (null != targetMethod) {

                        logger.info("method: {}", targetMethod.getName());

                        Object serviceClient = targetMethod.invoke(sub.newInstance(),
                                protocol.getInputProtocol(), protocol.getOutputProtocol());

                        this.client = (Client) serviceClient;
                    }

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    logger.error("faild to init Service.Client, {}", e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            logger.error("getClasses() encountered SecurityException! {}", e.getMessage());
        }
    }

    public RpcConnection getConnection() {
        try {
            this.connection.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    public void open() throws Exception {
        this.connection.open();
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

}
