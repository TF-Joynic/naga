package indi.joynic.naga.lib;

import indi.joynic.naga.lib.lb.node.ServiceNode;

import java.net.InetSocketAddress;
import java.util.Calendar;

/**
 * The representation class of node that providing RPC service.
 *
 */
public class ServiceProviderServiceNode implements ServiceNode {
    private InetSocketAddress inetSocketAddress;

    // weight for Client LB
    private volatile int weight;

    private volatile Calendar onlineSince;

    private volatile boolean isOnline;

    public ServiceProviderServiceNode(InetSocketAddress address, int weight) {
        if (null == address) {
            throw new NullPointerException("invalid socket address");
        }
        this.weight = weight;

        this.inetSocketAddress = address;
    }

    public static ServiceProviderServiceNode valueOf(String ip, int port, int weight) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);
        return new ServiceProviderServiceNode(inetSocketAddress, weight);
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean isOnline() {
        return isOnline;
    }

    @Override
    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Calendar getOnlineSince() {
        return onlineSince;
    }

    public void setOnlineSince(Calendar calendar) {
        this.onlineSince = calendar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceProviderServiceNode that = (ServiceProviderServiceNode) o;

        return inetSocketAddress.equals(that.inetSocketAddress);
    }

    @Override
    public int hashCode() {
        return inetSocketAddress.hashCode();
    }

    @Override
    public String toString() {
        return "ServiceNode{" +
                "inetSocketAddress=" + inetSocketAddress +
                ", weight=" + weight +
                ", onlineSince=" + onlineSince +
                '}';
    }
}
