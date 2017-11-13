package indi.joynic.naga.server.registry;

import indi.joynic.naga.lib.Registry;
import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.ServerNode;

import java.util.List;

public class ServerRegistry implements Registry {
    @Override
    public List<ServerNode> lookup(LookupKey lookupKey) {
        return null;
    }

    @Override
    public boolean evict(LookupKey lookupKey, ServerNode serverNode) {
        return false;
    }
}
