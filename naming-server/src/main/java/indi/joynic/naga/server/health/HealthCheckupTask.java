package indi.joynic.naga.server.health;

/**
 * Daemon Health Checkup thread
 * using thread pool?
 *
 * @since 1.0
 */
public class HealthCheckupTask implements Runnable {



    @Override
    public void run() {
        while (true) {

            // TODO

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
