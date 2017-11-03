package indi.joynic.naga.server.health;

import indi.joynic.naga.lib.ServerNode;

/**
 *
 * @since 1.0
 */
public interface HealthCheckup {

    void heartbeat(ServerNode serverNode);

}
