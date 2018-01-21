/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.connpool.impl;

import indi.joynic.naga.rpc.connection.RpcConnection;
import indi.joynic.naga.rpc.connpool.RpcConnectionProvider;
import indi.joynic.naga.rpc.connpool.enums.ConnectionStatus;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class RpcConnectionProviderImpl implements RpcConnectionProvider {

    private final ConcurrentHashMap<RpcConnection, ConnectionStatus> connectionPool = new ConcurrentHashMap<>();
    private final int maxConnectionCount;

    public RpcConnectionProviderImpl(int maxConnectionCount) {
        this.maxConnectionCount = maxConnectionCount;
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
