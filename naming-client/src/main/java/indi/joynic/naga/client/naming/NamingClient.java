package indi.joynic.naga.client.naming;

/**
 * Created by xiaolei on 2017/10/16.
 */
public interface NamingClient {
    boolean register(String serviceName, String hostIp, int port);
    boolean unregister(String serviceName, String hostIp, int port);
}
