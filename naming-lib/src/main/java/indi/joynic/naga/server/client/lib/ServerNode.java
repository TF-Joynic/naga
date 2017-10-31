package indi.joynic.naga.server.client.lib;

import java.net.InetSocketAddress;
import java.util.Calendar;

/**
 * The representation class of node providing service.
 * 
 *
 */
public class ServerNode {
    private InetSocketAddress inetSocketAddress;
    private volatile int weight;

    // last online time
    private Calendar lastOnlineTime;

    ServerNode(InetSocketAddress address) {
        if (null == address) {
            throw new NullPointerException("invalid socket address");
        }

        this.inetSocketAddress = address;
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
