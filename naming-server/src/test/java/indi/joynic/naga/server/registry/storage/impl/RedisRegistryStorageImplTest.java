package indi.joynic.naga.server.registry.storage.impl;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaolei on 2017/11/9.
 */
public class RedisRegistryStorageImplTest {
    @Test
    public void save() throws Exception {
        String a = "a";
        String b = "b";

        System.out.println(StringUtils.join(new String[]{a, b}, '_'));
    }

    @Test
    public void listByNamespace() throws Exception {
    }

    @Test
    public void listByNamespaceAndServiceName() throws Exception {
    }

    @Test
    public void listByLookupKey() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}