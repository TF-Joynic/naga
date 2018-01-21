package indi.joynic.naga.client.agent;


import indi.joynic.naga.rpc.connection.RpcConnection;

public interface AgentConnectionManager {
    RpcConnection getConnection();
}
