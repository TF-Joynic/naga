package indi.joynic.naga.lib;

import org.junit.Assert;
import org.junit.Test;

import java.net.InetSocketAddress;

import static org.junit.Assert.*;

public class ServerNodeTest {
    @Test
    public void getInetSocketAddress() throws Exception {

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9078);
        ServerNode serverNode = new ServerNode(inetSocketAddress, 45);

        System.out.println(serverNode.getInetSocketAddress().getHostString());
        System.out.println(serverNode.getInetSocketAddress().getPort());

        Assert.assertEquals("127.0.0.1", serverNode.getInetSocketAddress().getHostString());
        Assert.assertEquals(9078, serverNode.getInetSocketAddress().getPort());
    }

    @Test
    public void getWeight() throws Exception {
    }

    @Test
    public void setWeight() throws Exception {
    }

    @Test
    public void getLastOnlineTime() throws Exception {
    }

    @Test
    public void setLastOnlineTime() throws Exception {
    }

    @Test
    public void equals() throws Exception {
    }

    @Test
    public void testHashCode() throws Exception {
    }

    @Test
    public void testToString() throws Exception {
    }

}