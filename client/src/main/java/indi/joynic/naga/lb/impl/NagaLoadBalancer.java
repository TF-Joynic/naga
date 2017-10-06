package indi.joynic.naga.lb.impl;

import indi.joynic.naga.ServerNode;
import indi.joynic.naga.lb.LoadBalancer;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by xiaolei on 2017/10/6.
 */
public class NagaLoadBalancer implements LoadBalancer {
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
