package indi.joynic.naga.lib.utils;

import org.junit.Assert;
import org.junit.Test;

public class SocketAddrUtilTest {
    @Test
    public void getIntranetIp() throws Exception {
        String ip = SocketAddrUtil.getIntranetIp();
        System.out.println(ip);
        Assert.assertEquals(ip, "10.10.102.26");
    }

}