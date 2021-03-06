package indi.joynic.naga.server.registry;

import indi.joynic.naga.lib.Registry;
import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.lb.node.ServiceNode;

import java.util.List;

public class ServerRegistry implements Registry {
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
}
