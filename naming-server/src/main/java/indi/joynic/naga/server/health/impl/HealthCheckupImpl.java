package indi.joynic.naga.server.health.impl;

import indi.joynic.naga.lib.lb.node.ServiceNode;
import indi.joynic.naga.server.health.HealthCheckup;

public class HealthCheckupImpl implements HealthCheckup {
    @Override
    public boolean check(ServiceNode serverNode) {
        // TODO socket and ping result check
        return false;
    }
}
