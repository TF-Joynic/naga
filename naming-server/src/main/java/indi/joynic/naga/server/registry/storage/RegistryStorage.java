package indi.joynic.naga.server.registry.storage;

import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.ProtocolType;
import indi.joynic.naga.lib.ServerNode;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 *  interface of storing registry data to different storage like MySQL, Redis etc.
 */
public interface RegistryStorage {

    /**
     * save data to storage
     *
     * @param lookupKey
     * @param serverNode
     * @return
     */
    boolean save(LookupKey lookupKey, ServerNode serverNode);

    /**
     * client call for listing nodes by specifying namespace
     *
     * @param namespace
     * @return
     */
    List<Pair<LookupKey, ServerNode>> listByNamespace(String namespace);

    /**
     * list the node list by specifying the namespace and serviceName
     *
     * @param namespace
     * @return
     */
    List<Pair<LookupKey, ServerNode>> listByNamespaceAndServiceName(String namespace, String serviceName);

    /**
     * naming-client invocation method
     *
     * @param lookupKey
     * @return
     */
    List<Pair<LookupKey, ServerNode>> listByLookupKey(LookupKey lookupKey);

    void delete(LookupKey lookupKey);

}
