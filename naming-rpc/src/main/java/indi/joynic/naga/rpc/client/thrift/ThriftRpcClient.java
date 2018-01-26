/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.client.thrift;

import indi.joynic.naga.rpc.client.RpcClient;
import indi.joynic.naga.rpc.connection.thrift.ThriftRpcConnection;
import org.apache.thrift.TServiceClient;

import java.io.IOException;

public interface ThriftRpcClient<Client extends TServiceClient> extends RpcClient {

    Client getClient() throws Exception;

    ThriftRpcConnection getConnection() throws Exception;

    void open() throws Exception;

    void close() throws IOException;
}
