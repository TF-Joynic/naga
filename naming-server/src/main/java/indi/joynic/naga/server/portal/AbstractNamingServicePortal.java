package indi.joynic.naga.server.portal;

public abstract class AbstractNamingServicePortal {
    abstract boolean doRegister(String ns, String protocolType, String serviceName, String host, int port, int weight) throws Exception;
}
