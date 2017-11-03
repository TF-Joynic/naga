package indi.joynic.naga.server.portal;

import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.server.portal.serviceprovider.NamingServerPortal;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;

public class ServerPortalForServiceProvider implements NamingServerPortal.Iface {

    /**
     * Service Provider register itself to the naming server
     *
     * @param protocolType
     * @param ns
     * @param serviceName
     * @param host
     * @param port
     * @return
     * @throws TException
     */
    @Override
    public boolean doRegister(String protocolType, String ns, String serviceName, String host, int port) throws TException {
        if (StringUtils.isEmpty(protocolType) || StringUtils.isEmpty(ns) || StringUtils.isEmpty(serviceName)
                || StringUtils.isEmpty(host)) {

            return false;

        }

        try {
            SocketAddrUtil.checkPort(port);
        } catch (IllegalArgumentException e) {
            return false;
        }

        // register to storage
    }
}
