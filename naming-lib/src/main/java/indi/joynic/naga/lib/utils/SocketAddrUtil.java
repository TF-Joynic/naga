package indi.joynic.naga.lib.utils;

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

}
