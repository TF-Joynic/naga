package indi.joynic.naga.lib;

import indi.joynic.naga.lib.lb.node.ServiceNode;

import java.util.List;

/**
 * The registry class that hold node lists of healthy services.
 * Each service and node are one-to-many mapping.
 *
 * @author Terrance Fung<wkf.joynic@gmail.com>
 * @since 1.0
 */
public interface Registry {

    /**
     * register
     */
    boolean register(LookupKey lookupKey, ServiceNode serverNode);

    /**
     * lookup node server list by specifying lookup key
     *
     * @return
     */
    List<ServiceNode> lookup(LookupKey lookupKey);

    /**
     * remove a invalid server node
     * on account of server is no longer able to provide service
     *
     * @return
     */
    boolean evict(LookupKey lookupKey, ServiceNode serverNode);
}
