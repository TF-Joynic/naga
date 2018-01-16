package indi.joynic.naga.lib;

import org.junit.Assert;
import org.junit.Test;

public class ProtocolTypeTest {

    @Test
    public void testValueOf() {
        String a = "THRIFT";

        Assert.assertTrue(ProtocolType.valueOf(a) instanceof ProtocolType);

        String b = "HTTP";
        Assert.assertTrue(ProtocolType.valueOf(b) instanceof ProtocolType);
    }

}