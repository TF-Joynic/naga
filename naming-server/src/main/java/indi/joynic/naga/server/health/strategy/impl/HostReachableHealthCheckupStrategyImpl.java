package indi.joynic.naga.server.health.strategy.impl;

import indi.joynic.naga.server.health.strategy.HealthCheckupStrategy;

public class HostReachableHealthCheckupStrategyImpl implements HealthCheckupStrategy {
    @Override
    public boolean execute() {
        return false;
    }
}