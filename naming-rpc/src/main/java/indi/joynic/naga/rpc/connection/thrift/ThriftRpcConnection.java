/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.connection.thrift;

import indi.joynic.naga.rpc.connection.RpcConnection;
import org.apache.thrift.transport.TTransport;

public interface ThriftRpcConnection extends RpcConnection {
    TTransport getConnection();

    void open() throws Exception;
}
