package indi.joynic.naga;

import indi.joynic.naga.lb.LoadBalancer;
import indi.joynic.naga.lb.impl.NagaLoadBalancer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Client connects server to fetch service host:port by specifying service name
 */
public class NamingClient {
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

    private List<InetSocketAddressWrapper> addressWrappers;

    public NamingClient(List<InetSocketAddress> addressList, int[] weights) {
        if (null == addressList) {
            throw new NullPointerException("invalid address list");
        }

        for (int i = 0; i < addressList.size(); i ++) {

            InetSocketAddressWrapper inetSocketAddressWrapper
                    = new InetSocketAddressWrapper(addressList.get(i), (i + 1), weights[i]);

            addressWrappers.add(inetSocketAddressWrapper);

        }


    }

    /**
     * @param hostPortWeights
     * @param connnectTimeout
     */
    public NamingClient(String hostPortWeights, int connnectTimeout) {
        if (null == hostPortWeights) {
            throw new NullPointerException("null hostPortWeights!");
        }

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

        // do connect to server
        connect();
    }

    /**
     * connect to naga-server
     */
    private Future<Boolean> connect() {
        return null;
    }

    public ServerNode getNextServerNode() {
        if (null == loadBalancer) {
            loadBalancer = new NagaLoadBalancer();
        }

        return loadBalancer.getNextServerNode();
    }
}
