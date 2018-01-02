package indi.joynic.naga.client.lb;

import indi.joynic.naga.lib.ServerNode;

import java.util.List;
import java.util.concurrent.Future;

public interface LoadBalancer {

    Future<Boolean> heathCheck();

    ServerNode getNextServerNode();

    List<ServerNode> getLocalServerNodeListCache(String serviceName);

    void clearLocalServerNodeListCache(String serviceName);


}
