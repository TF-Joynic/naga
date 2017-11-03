package indi.joynic.naga.client.lb.impl;

import indi.joynic.naga.client.lb.LoadBalancer;
import indi.joynic.naga.lib.ServerNode;

import java.util.List;
import java.util.concurrent.Future;

public class LoadBalancerImpl implements LoadBalancer {
    @Override
    public Future<Boolean> heathCheck() {
        return null;
    }

    @Override
    public ServerNode getNextServerNode() {
        return null;
    }

    @Override
    public List<ServerNode> getLocalServerNodeListCache(String serviceName) {
        return null;
    }

    @Override
    public void clearLocalServerNodeListCache(String serviceName) {

    }
}
