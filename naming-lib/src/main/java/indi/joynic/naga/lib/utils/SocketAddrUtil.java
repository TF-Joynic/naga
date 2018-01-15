package indi.joynic.naga.lib.utils;

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

}
