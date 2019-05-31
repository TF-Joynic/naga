package indi.joynic.naga.server.health;

import java.util.concurrent.Callable;

/**
 * Daemon Health Checkup thread
 * using thread pool?
 *
 * @since 1.0
 */
public class HealthCheckupTask implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        return false;
    }
}
