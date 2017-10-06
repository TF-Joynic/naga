package indi.joynic.naga.lb;

import indi.joynic.naga.ServerNode;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by xiaolei on 2017/10/6.
 */
public interface LoadBalancer {

    Future<Boolean> heathCheck();

    ServerNode getNextServerNode();

    List<ServerNode> getLocalServerNodeListCache(String serviceName);

    void clearLocalServerNodeListCache(String serviceName);


}
