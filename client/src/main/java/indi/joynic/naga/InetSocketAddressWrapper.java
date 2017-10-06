package indi.joynic.naga;

import java.net.InetSocketAddress;

/**
 * Created by xiaolei on 2017/10/6.
 */
public class InetSocketAddressWrapper {
    private InetSocketAddress inetSocketAddress;
    private int order;
    private int weight;

    InetSocketAddressWrapper(InetSocketAddress inetSocketAddress, int order, int weight) {
        this.inetSocketAddress = inetSocketAddress;
        this.order = order;
        this.weight = weight;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public void setInetSocketAddress(InetSocketAddress inetSocketAddress) {
        this.inetSocketAddress = inetSocketAddress;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "InetSocketAddressWrapper{" +
                "inetSocketAddress=[" + inetSocketAddress +
                ", order=" + order +
                ", weight=" + weight +
                '}';
    }
}
