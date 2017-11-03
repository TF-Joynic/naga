package indi.joynic.naga.client.agent;


import indi.joynic.naga.client.rpc.RpcConnection;

/**
 * Created by xiaolei on 2017/10/31.
 */
public interface AgentConnectionManager {
    RpcConnection getConnection();
}
