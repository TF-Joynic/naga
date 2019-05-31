package indi.joynic.naga.lib.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketAddrUtil {

    public static final String LOCAL_HOST = "127.0.0.1";

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

    public static boolean portUsed(int port) {
        return portUsed(port, LOCAL_HOST);
    }

    public static boolean portUsed(int port, String host) {
        int checkedPort = checkPort(port);

        if (StringUtils.isAnyBlank(host)) {
            throw new IllegalArgumentException("Specified host is invalid");
        }

        boolean portUsed = false;
        // try to connect to given port
        Socket socket = new Socket();
        try {
            socket.bind(new InetSocketAddress(host, checkedPort));
        } catch (IOException e) {
            portUsed = true;
        } finally {
            if (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

        return portUsed;
    }
}
