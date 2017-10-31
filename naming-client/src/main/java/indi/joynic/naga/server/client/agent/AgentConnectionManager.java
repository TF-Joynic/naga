package indi.joynic.naga.server.client.agent;

import indi.joynic.naga.server.client.rpc.RpcConnection;

/**
 * Created by xiaolei on 2017/10/31.
 */
public interface AgentConnectionManager {
    RpcConnection getConnection();
}
