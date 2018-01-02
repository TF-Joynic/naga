package indi.joynic.naga.client.rpc;

import org.apache.thrift.protocol.TProtocol;

public interface RpcProtocol {

    TProtocol getInputProtocol();

    TProtocol getOutputProtocol();

}
