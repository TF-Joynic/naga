package indi.joynic.naga.agent.registry;

import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.lb.node.ServiceNode;

import java.util.List;

public class CacheRegistryImpl implements CachedRegistry {

    @Override
    public boolean register(LookupKey lookupKey, ServiceNode serverNode) {
        return false;
    }


    @Override
    public List<ServiceNode> lookup(LookupKey lookupKey) {
        return null;
    }

    @Override
    public boolean evict(LookupKey lookupKey, ServiceNode serverNode) {
        return false;
    }

    @Override
    public void clear() {

    }

}
