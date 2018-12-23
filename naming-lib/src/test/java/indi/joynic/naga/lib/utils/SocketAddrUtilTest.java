package indi.joynic.naga.lib.utils;

import org.junit.Test;

public class SocketAddrUtilTest {
    @Test
    public void getIntranetIp() throws Exception {
        String ip = SocketAddrUtil.getIntranetIp();
        System.out.println(ip);
    }

    @Test
    public void testPort() {
        System.out.println(SocketAddrUtil.portUsed(5679));
    }

}