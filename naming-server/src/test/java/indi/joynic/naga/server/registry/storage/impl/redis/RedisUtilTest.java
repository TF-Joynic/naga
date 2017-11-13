package indi.joynic.naga.server.registry.storage.impl.redis;

import org.junit.Test;

/**
 * Created by xiaolei on 2017/11/13.
 */
public class RedisUtilTest {
    @Test
    public void implodeKey() throws Exception {
        System.out.println(ip("a", "b", "cd"));
    }

    private String ip(String...cd) {
        return RedisUtil.implode('_', cd);
    }

}