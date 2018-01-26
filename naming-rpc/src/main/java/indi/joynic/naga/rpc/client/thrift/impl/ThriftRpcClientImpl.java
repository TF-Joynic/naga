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

import java.io.IOException;
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

    /**
     * Constructor using same input protocol & output protocol
     *
     * @param clientClazz
     * @param rpcProtocolClazz
     * @param transportClazz
     * @param host
     * @param port
     * @param timeout
     */
    public ThriftRpcClientImpl(Class<? extends TServiceClient> clientClazz,
                               Class<? extends TProtocol> rpcProtocolClazz,
                               Class<? extends TTransport> transportClazz,
                               String host,
                               int port,
                               int timeout)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        // TODO: Delegate Adapter on ConnectionProvider
        this.connection = new ThriftRpcConnectionImpl(transportClazz, host, port, timeout);
        this.protocol = new ThriftRpcProtocolImpl(rpcProtocolClazz, connection);
        this.clientClazz = clientClazz;
    }

    /**
     * Constructor using different input protocol & output protocol
     *
     * @param clientClazz
     * @param inputRpcProtocolClazz
     * @param outputRpcProtocolClazz
     * @param transportClazz
     * @param host
     * @param port
     * @param timeout
     */
    public ThriftRpcClientImpl(Class<? extends TServiceClient> clientClazz,
                               Class<? extends TProtocol> inputRpcProtocolClazz,
                               Class<? extends TProtocol> outputRpcProtocolClazz,
                               Class<? extends TTransport> transportClazz,
                               String host,
                               int port,
                               int timeout)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        this.connection = new ThriftRpcConnectionImpl(transportClazz, host, port, timeout);
        this.protocol = new ThriftRpcProtocolImpl(inputRpcProtocolClazz, outputRpcProtocolClazz, connection);
        this.clientClazz = clientClazz;
    }

    public Client getClient() throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        if (null != client) {
            return client;
        }

        getServiceClient();
        return client;
    }

    @SuppressWarnings("unchecked")
    private void getServiceClient() throws SecurityException, NoSuchMethodException,
            IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<?>[] subs = clientClazz.getClasses();
        if (null == subs) {
            throw new NoSuchMethodException("Thrift service client Factory clazz NOT found!");
        }

        String targetFactoryClazzName = clientClazz.getName()
                + "$" + THRIFT_SERVICE_CLIENT_FACTORY_CLASS_NAME;

        for (Class<?> sub : subs) {
            if (!sub.getName().equals(targetFactoryClazzName)) {
                continue;
            }

            Method targetMethod = sub.getMethod(THRIFT_SERVICE_CLIENT_FACTORY_METHOD_NAME,
                    TProtocol.class, TProtocol.class);

            if (null != targetMethod) {

                Object serviceClient = targetMethod.invoke(sub.newInstance(),
                        protocol.getInputProtocol(), protocol.getOutputProtocol());

                this.client = (Client) serviceClient;
                return ;
            }
        }
    }

    public ThriftRpcConnection getConnection() throws Exception {
        return this.connection;
    }

    public void open() throws Exception {
        this.connection.open();
    }

    @Override
    public void close() throws IOException {
        this.connection.close();
    }

}
