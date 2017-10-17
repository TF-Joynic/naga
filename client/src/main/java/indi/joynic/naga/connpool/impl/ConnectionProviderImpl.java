/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.connpool.impl;

import indi.joynic.naga.connpool.ConnectionProvider;
import indi.joynic.naga.connpool.enums.ConnectionStatus;
import indi.joynic.naga.rpc.RpcConnection;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionProviderImpl implements ConnectionProvider {

    private final ConcurrentHashMap<RpcConnection, ConnectionStatus> connectionPool = new ConcurrentHashMap<>();

    public ConnectionProviderImpl() {

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
