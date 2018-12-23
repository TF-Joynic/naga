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
     *
     * @param lookupKey
     * @param serverNode
     * @return
     */
    boolean register(LookupKey lookupKey, ServiceNode serverNode);

    /**
     * lookup node server list by specifying lookup key
     *
     * @param lookupKey
     * @return
     */
    List<ServiceNode> lookup(LookupKey lookupKey);

    /**
     * remove a invalid server node
     * on account of server is no longer able to provide service
     *
     * @param lookupKey
     * @param serverNode
     * @return
     */
    boolean evict(LookupKey lookupKey, ServiceNode serverNode);
}
