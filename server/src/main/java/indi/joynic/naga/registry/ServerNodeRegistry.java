package indi.joynic.naga.registry;

import indi.joynic.naga.Registry;
import indi.joynic.naga.ServerNode;

/**
 * Server-side registry
 *
 * In consideration of registry storage failing, server node must register itself automatically and continuously.
 *
 * The newly registered node will be put into healing pool immediately, existed node do nothing
 *
 * @author Terrance Fung
 * @since 1.0
 */
public interface ServerNodeRegistry extends Registry {

    /**
     * register one service provider to this service
     *
     * @param serviceName
     * @param serverNode
     * @return
     */
    boolean register(String serviceName, ServerNode serverNode);

    /**
     * service provider unbind this server node from registry for maintenance purpose etc.
     *
     * @param serviceName
     * @param serverNode
     * @return
     */
    boolean unregister(String serviceName, ServerNode serverNode);
}
