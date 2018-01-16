/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.client.thrift.impl;

import indi.joynic.naga.rpc.client.thrift.ThriftRpcClient;
import indi.joynic.naga.rpc.connection.thrift.ThriftRpcConnection;
import indi.joynic.naga.rpc.connection.thrift.impl.ThriftRpcConnectionImpl;
import indi.joynic.naga.rpc.protocol.thrift.ThriftRpcProtocol;
import indi.joynic.naga.rpc.protocol.thrift.impl.ThriftRpcProtocolImpl;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ThriftRpcClientImpl<Client extends TServiceClient> implements ThriftRpcClient<Client> {

    private static final Logger logger = LoggerFactory.getLogger(ThriftRpcClientImpl.class);

    private static final String THRIFT_SERVICE_CLIENT_FACTORY_CLASS_NAME = "Factory";
    private static final String THRIFT_SERVICE_CLIENT_FACTORY_METHOD_NAME = "getClient";

    private ThriftRpcConnection connection;
    private ThriftRpcProtocol protocol;
    private Client client;

    private Class<? extends TServiceClient> clientClazz;

    public ThriftRpcClientImpl(Class<? extends TServiceClient> clientClazz, Class<? extends TProtocol> rpcProtocolClazz,
                               Class<? extends TTransport> transportClazz, String host, int port, int timeout) {

        this.connection = new ThriftRpcConnectionImpl(transportClazz, host, port, timeout);
        this.protocol = new ThriftRpcProtocolImpl(rpcProtocolClazz, connection);
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

                try {
                    Method targetMethod = sub.getMethod(THRIFT_SERVICE_CLIENT_FACTORY_METHOD_NAME, TProtocol.class, TProtocol.class);
                    if (null != targetMethod) {

                        Object serviceClient = targetMethod.invoke(sub.newInstance(),
                                protocol.getInputProtocol(), protocol.getOutputProtocol());

                        this.client = (Client) serviceClient;
                    }

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    logger.error("failed to init Service.Client, {}", e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            logger.error("getClasses() encountered SecurityException! {}", e.getMessage());
        }
    }

    public ThriftRpcConnection getConnection() {
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


}
