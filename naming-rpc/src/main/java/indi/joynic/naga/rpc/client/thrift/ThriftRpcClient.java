/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.client.thrift;

import indi.joynic.naga.rpc.client.RpcClient;
import indi.joynic.naga.rpc.connection.thrift.ThriftRpcConnection;
import org.apache.thrift.TServiceClient;

public interface ThriftRpcClient<Client extends TServiceClient> extends RpcClient {

    void open() throws Exception ;

    Client getClient();

    ThriftRpcConnection getConnection();
}
