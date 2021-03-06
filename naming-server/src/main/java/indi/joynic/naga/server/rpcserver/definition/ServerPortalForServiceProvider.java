package indi.joynic.naga.server.rpcserver.definition;

import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.ProtocolType;
import indi.joynic.naga.lib.ServiceProviderServiceNode;
import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import indi.joynic.naga.server.registry.storage.RegistryStorage;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "serverPortalForServiceProvider")
public class ServerPortalForServiceProvider implements ThriftNamingServerPortal.Iface {

    @Resource
    private RegistryStorage registryStorage;

    /**
     * register one node and its info on naming server
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
    public boolean doRegister(String ns, String protocolType, String serviceName, String host, int port, int weight) throws TException {
        if (StringUtils.isEmpty(protocolType) || StringUtils.isEmpty(ns) || StringUtils.isEmpty(serviceName)
                || StringUtils.isEmpty(host)) {

            return false;

        }

        try {
            SocketAddrUtil.checkPort(port);
        } catch (IllegalArgumentException e) {
            return false;
        }

        LookupKey lookupKey = LookupKey.valueOf(ns, serviceName, ProtocolType.valueOf(protocolType));
        ServiceProviderServiceNode serverNode = ServiceProviderServiceNode.valueOf(host, port, weight);

        // register to storage
        return registryStorage.save(lookupKey, serverNode);
    }
}
