/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.protocol.thrift;

import indi.joynic.naga.rpc.protocol.RpcProtocol;
import org.apache.thrift.protocol.TProtocol;

public interface ThriftRpcProtocol extends RpcProtocol {

    TProtocol getInputProtocol();

    TProtocol getOutputProtocol();

}
