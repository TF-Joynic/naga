package indi.joynic.naga.lib.lb;


import indi.joynic.naga.lib.lb.node.ServiceNode;

/**
 * TODO: shall run health checkup thread to evict bad node!
 */
public interface LoadBalancer {

    ServiceNode nextServerNode();


}
