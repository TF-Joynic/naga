package indi.joynic.naga.lib.lb.node;

import java.net.InetSocketAddress;

/**
 * upstream service node
 */
public interface ServiceNode {
    InetSocketAddress getInetSocketAddress();

    int getWeight();

    void setWeight(int weight);

    boolean isOnline();

    void setOnline(boolean isOnline);
}
