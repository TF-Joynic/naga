/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.connpool;


import indi.joynic.naga.rpc.connection.RpcConnection;

public interface RpcConnectionProvider {

    /**
     * init the connection pool with specified pool size
     */
    void initConnectionPool();

    /**
     * fetch rpc connection from the pool and set its status to BUSY
     */
    RpcConnection getConnection();

    /**
     * put back connection to the pool with status IDLE
     */
    boolean closeConnection();

    /**
     * remove connection
     *
     * @param rpcConnection
     * @return
     */
    boolean removeFromConnectionPool(RpcConnection rpcConnection);

    /**
     * clear all connection(s) in this pool
     */
    void clearConnectionPool();
}
