package indi.joynic.naga.rpc.config;

import indi.joynic.naga.rpc.config.enums.RpcMode;

/**
 * TODO: design pattern
 */
public class RpcConfig {
    private RpcMode rpcMode;

    private String host;
    private int port;
    private int timeoutMillis;

    public RpcMode getRpcMode() {
        return rpcMode;
    }

    public void setRpcMode(RpcMode rpcMode) {
        this.rpcMode = rpcMode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeoutMillis() {
        return timeoutMillis;
    }

    public void setTimeoutMillis(int timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }
}
