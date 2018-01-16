/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.rpc.connection;

import java.io.Closeable;

public interface RpcConnection<C extends Closeable> extends Closeable {
    C getConnection();
}
