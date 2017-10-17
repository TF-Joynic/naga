package indi.joynic.naga.registry.impl;

import indi.joynic.naga.ServerNode;
import indi.joynic.naga.registry.ServerNodeRegistry;

import java.util.Set;

/**
 * Created by xiaolei on 2017/10/13.
 */
public class ServerNodeRegistryImpl implements ServerNodeRegistry {


    @Override
    public Set<ServerNode> lookup(String serviceName) {
        return null;
    }

    @Override
    public boolean evict(String serviceName, ServerNode serverNode) {
        return false;
    }

    @Override
    public boolean register(String serviceName, ServerNode serverNode) {
        return false;
    }

    @Override
    public boolean unregister(String serviceName, ServerNode serverNode) {
        return false;
    }
}
