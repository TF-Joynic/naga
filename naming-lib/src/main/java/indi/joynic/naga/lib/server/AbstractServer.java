package indi.joynic.naga.lib.server;

public abstract class AbstractServer implements Server {
    private volatile boolean started = false;

    public AbstractServer() {

    }

    @Override
    public void start() {
        new ServerStarter().start();
        started = true;
    }

    public abstract void doStart();

    /**
     * invoke start()
     */
    class ServerStarter extends Thread {
        public void run() {
            try {
                doStart();
            } catch (Exception e) {

            } finally {
                
            }
        }
    }
}
