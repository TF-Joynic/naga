package indi.joynic.naga.client.naming.impl;

import indi.joynic.naga.lib.ServerNode;
import indi.joynic.naga.client.lb.LoadBalancer;
import indi.joynic.naga.client.lb.impl.LoadBalancerImpl;
import indi.joynic.naga.client.naming.NamingClient;

import java.net.InetSocketAddress;
import java.util.ArrayList;
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

        hostPortWeights = hostPortWeights.trim();
        String[] hostPortWeightArr = hostPortWeights.split(",");

        List<InetSocketAddress> inetSocketAddressList
                = new ArrayList<>(hostPortWeightArr.length);

        int[] weights = new int[hostPortWeightArr.length];

        for (int i = 0; i < hostPortWeightArr.length; i ++) {
            String hostPortWeight = hostPortWeightArr[i];
            String[] hostPortWeightSegment = hostPortWeight.split(":");

            if (hostPortWeightSegment.length < 2) {
                throw new IllegalArgumentException("host port weight should be separated by :");
            }

            String host = hostPortWeightSegment[0];
            int port = Integer.valueOf(hostPortWeightSegment[1]);

            if (hostPortWeightSegment.length > 2) {
                weights[i] = Integer.valueOf(hostPortWeightSegment[2]);
            }

            inetSocketAddressList.add(new InetSocketAddress(host, port));
        }
    }

    /**
     * connect to naming-server to fetch service provider list
     */
    private Future<Boolean> connect() {
        return null;
    }

    public boolean register() {

    }

    public boolean unregister() {

    }

    public ServerNode getNextServerNode() {
        if (null == loadBalancer) {
            loadBalancer = new LoadBalancerImpl();
        }

        return loadBalancer.getNextServerNode();
    }
}
