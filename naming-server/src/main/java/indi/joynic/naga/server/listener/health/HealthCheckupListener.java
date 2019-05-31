package indi.joynic.naga.server.listener.health;

import indi.joynic.naga.lib.lb.node.ServiceNode;

/**
 * Listener lists phase(s) of health checkup procedure.
 *
 * @author Terrance Fung
 */
public interface HealthCheckupListener {

    /**
     * will be called once the checkup finished
     *
     * @param serviceNode
     * @param result
     */
    void onCheckupFinished(ServiceNode serviceNode, boolean result);
}