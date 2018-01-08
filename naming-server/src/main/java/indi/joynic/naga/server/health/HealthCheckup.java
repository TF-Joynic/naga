package indi.joynic.naga.server.health;


import indi.joynic.naga.lib.lb.node.ServiceNode;

/**
 *
 * @since 1.0
 */
public interface HealthCheckup {

    void heartbeat(ServiceNode serverNode);

}
