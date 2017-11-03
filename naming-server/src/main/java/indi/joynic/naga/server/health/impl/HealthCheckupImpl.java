package indi.joynic.naga.server.health.impl;

import indi.joynic.naga.lib.ServerNode;
import indi.joynic.naga.server.health.HealthCheckup;

/**
 * HealthCheckupImpl class
 * operations for each node checkup
 *
 * @since 1.0
 */
public class HealthCheckupImpl implements HealthCheckup {
    @Override
    public void heartbeat(ServerNode serverNode) {
        // TODO socket and ping result check

    }
}
