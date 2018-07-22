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

    protected abstract void doStart();

    protected abstract void doStop();

    public boolean isStarted() {
        return this.started;
    }

    public void stop() {
        new ServerTerminator().start();
    }

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

    class ServerTerminator extends Thread {
        public void run() {
            try {
                doStop();
            } finally {

            }
        }
    }
}
