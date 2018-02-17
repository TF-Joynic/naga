package indi.joynic.naga.lib.server;

public interface Server {
    boolean isStarted();
    void start();
    void stop();
}
