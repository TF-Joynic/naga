package indi.joynic.naga.client.agent;


import indi.joynic.naga.client.rpc.RpcConnection;

public interface AgentConnectionManager {
    RpcConnection getConnection();
}
