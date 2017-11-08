package indi.joynic.naga.lib;

import java.net.InetSocketAddress;
import java.util.Calendar;

/**
 * The representation class of node that providing RPC service.
 *
 */
public class ServerNode {
    private InetSocketAddress inetSocketAddress;

    // weight for Client LB
    private volatile int weight;

    // last online time
    private Calendar lastOnlineTime;

    public ServerNode(InetSocketAddress address, int weight) {
        if (null == address) {
            throw new NullPointerException("invalid socket address");
        }
        this.weight = weight;

        this.inetSocketAddress = address;
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

    public Calendar getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Calendar calendar) {
        this.lastOnlineTime = calendar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerNode that = (ServerNode) o;

        return inetSocketAddress.equals(that.inetSocketAddress);
    }

    @Override
    public int hashCode() {
        return inetSocketAddress.hashCode();
    }

    @Override
    public String toString() {
        return "ServerNode{" +
                "inetSocketAddress=" + inetSocketAddress +
                ", weight=" + weight +
                ", lastOnlineTime=" + lastOnlineTime +
                '}';
    }
}
