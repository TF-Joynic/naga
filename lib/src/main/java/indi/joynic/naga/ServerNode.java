package indi.joynic.naga;

import java.net.InetSocketAddress;

public class ServerNode {
    private InetSocketAddress address;

    // last online time
    private Long lastOnlineTime;

    ServerNode(InetSocketAddress address) {
        this.address = address;
    }

    public Long getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Long lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    @Override
    public String toString() {
        return "ServerNode{" +
                "address=" + address +
                ", lastOnlineTime=" + lastOnlineTime +
                '}';
    }
}
