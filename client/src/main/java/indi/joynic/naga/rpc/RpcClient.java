package indi.joynic.naga.rpc;

import org.apache.thrift.TServiceClient;

public interface RpcClient<Client extends TServiceClient> extends AutoCloseable {
    void open() throws Exception ;
    Client getClient();
    RpcConnection getConnection();
}
