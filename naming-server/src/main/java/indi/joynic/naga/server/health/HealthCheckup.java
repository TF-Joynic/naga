package indi.joynic.naga.server.health;

import indi.joynic.naga.lib.lb.node.ServiceNode;

/**
 * HealthCheckupImpl class
 *
 * check do one round examination to a service node and return result
 * which determines whether we should evict this node or not.
 *
 * @author Terrance Fung
 * @since 1.0
 */
public interface HealthCheckup {

    /**
     * do check etc.
     *
     * @param serverNode
     * @return true if service node crashed; false otherwise
     */
    boolean check(ServiceNode serverNode);

}
