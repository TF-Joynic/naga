package indi.joynic.naga.server.client.rpc;

import org.apache.thrift.protocol.TProtocol;

/**
 * Created by xiaolei on 2017/10/10.
 */
public interface RpcProtocol {

    TProtocol getInputProtocol();

    TProtocol getOutputProtocol();

}
