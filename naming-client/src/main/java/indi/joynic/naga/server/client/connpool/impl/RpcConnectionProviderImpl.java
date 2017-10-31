/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.server.client.connpool.impl;

import indi.joynic.naga.server.client.connpool.RpcConnectionProvider;
import indi.joynic.naga.server.client.connpool.enums.ConnectionStatus;
import indi.joynic.naga.server.client.rpc.RpcConnection;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class RpcConnectionProviderImpl implements RpcConnectionProvider {

    private final ConcurrentHashMap<RpcConnection, ConnectionStatus> connectionPool = new ConcurrentHashMap<>();

    public RpcConnectionProviderImpl() {

    }

    @Override
    public void initConnectionPool() {

    }

    @Override
    public RpcConnection getConnection() {
        return null;
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

    @Override
    public boolean removeFromConnectionPool(RpcConnection rpcConnection) {
        return false;
    }

    @Override
    public void clearConnectionPool() {

    }
}
