package indi.joynic.naga.client.naming.impl;

import indi.joynic.naga.lib.lb.LoadBalancer;
import indi.joynic.naga.lib.lb.impl.LoadBalancerImpl;
import indi.joynic.naga.client.naming.NamingClient;
import indi.joynic.naga.lib.lb.node.ServiceNode;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Client that connects naming-server to fetch service host:port by specifying a service name.
 * Each naming-server host:port has its own weight.
 *
 * NOTICE: all time unit is MILLISECOND
 *
 * @author Terrance Fung
 * @since 1.0
 */
public class NamingClientImpl implements NamingClient {
    private final static int DEFAULT_CONNECT_TIMEOUT = 2000;
    private final static int DEFAULT_RPC_TIMEOUT = 3000;
    private final static int DEFAULT_HEARTBEAT_INTERVAL = 1000;
    private final static int DEFAULT_HEAL_INTERVAL = 1500;

    private int connectTimeout;
    private int rpcTimeout;
    private int heartbeatInterval;
    private int healInterval;

//    private Connector
    private LoadBalancer loadBalancer;

    public NamingClientImpl(List<InetSocketAddress> addressList) {

    }

    /**
     * @param connnectTimeout
     */
    public NamingClientImpl(int connnectTimeout) {
        this.connectTimeout = connnectTimeout;
    }

    /**
     * connect to naming-server to fetch service provider list
     */
    private Future<Boolean> connect() {
        return null;
    }

    @Override
    public boolean register(String serviceName, String hostIp, int port) {
        return false;
    }

    @Override
    public boolean unregister(String serviceName, String hostIp, int port) {
        return false;
    }

    public ServiceNode getNextServerNode() {
        if (null == loadBalancer) {
            loadBalancer = new LoadBalancerImpl();
        }

        return loadBalancer.nextServerNode();
    }

}
