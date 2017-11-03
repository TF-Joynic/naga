package indi.joynic.naga.server.registry;

import indi.joynic.naga.lib.Registry;
import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.ServerNode;

import java.util.List;

/**
 * Created by xiaolei on 2017/11/2.
 */
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
