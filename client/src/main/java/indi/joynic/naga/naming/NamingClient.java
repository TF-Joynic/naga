package indi.joynic.naga.naming;

import indi.joynic.naga.ServerNode;

/**
 * Created by xiaolei on 2017/10/16.
 */
public interface NamingClient {
    boolean register(String serviceName, );
    public boolean unregister(String serviceName, ServerNode serverNode);
}
