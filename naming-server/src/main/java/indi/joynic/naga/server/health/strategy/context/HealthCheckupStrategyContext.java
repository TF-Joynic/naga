package indi.joynic.naga.server.health.strategy.context;

import indi.joynic.naga.server.health.strategy.HealthCheckupStrategy;

public class HealthCheckupStrategyContext {
    private HealthCheckupStrategy healthCheckupStrategy;

    public HealthCheckupStrategyContext(HealthCheckupStrategy healthCheckupStrategy) {
        this.healthCheckupStrategy = healthCheckupStrategy;
    }

    public boolean execute() {
        return this.healthCheckupStrategy.execute();
    }
}