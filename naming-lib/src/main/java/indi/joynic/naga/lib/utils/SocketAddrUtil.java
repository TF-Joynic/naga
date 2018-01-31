package indi.joynic.naga.lib.utils;

import org.apache.commons.lang3.StringUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketAddrUtil {

    /**
     * check if the given port is legal or not, if not an IllegalArgumentException
     * shall be thrown.
     *
     * @param port
     * @return
     */
    public static int checkPort(int port) {
        if (port < 0 || port > 0xFFFF)
            throw new IllegalArgumentException("port out of range:" + port);

        return port;
    }

    /**
     * get intranet ip(string) of this very machine.
     *
     * @return
     */
    public static String getIntranetIp() {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ip;
    }

    public static String[] splitHostPort(String hosts) {
        return splitHostPort(hosts, ":");
    }

    public static String[] splitHostPort(String hosts, String separator) {
        if (StringUtils.isEmpty(hosts) || StringUtils.isEmpty(separator)) {
            throw new IllegalArgumentException("hosts can not be null!");
        }

        hosts = hosts.trim();
        String[] hostPortArr = hosts.split(separator);

        if (2 != hostPortArr.length) {
            throw new IllegalArgumentException("hosts is not format of [ip:port]!");
        }

        return hostPortArr;
    }

}
