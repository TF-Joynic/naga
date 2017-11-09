package indi.joynic.naga.lib;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaolei on 2017/11/9.
 */
public class ProtocolTypeTest {

    @Test
    public void testValueOf() {
        String a = "THRIFT";

        System.out.println(ProtocolType.valueOf(a) instanceof ProtocolType);
    }

}