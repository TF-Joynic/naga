package indi.joynic.naga.server.registry.storage.impl;

import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.ServerNode;
import indi.joynic.naga.server.registry.storage.RegistryStorage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

public class RedisRegistryStorageImpl implements RegistryStorage {

    private static final char KEY_FIELD_SEPARATOR = ',';
    private static final char VALUE_FIELD_SEPARATOR = ':';

    @Value("${naming.server.redis.registry.data.hash.key}")
    private String registryDataHashKey;

    @Value("${naming.server.redis.registry.namespace.index.hash.key}")
    private String registryNamespaceIndexHashKey;

    @Value("${naming.server.redis.registry.namespace.servicename.index.hash.key}")
    private String registryNamespaceAndServiceNameIndexHashKey;


    @Resource(name = "redisRegistryTemplate")
    private RedisTemplate<String, String> redisRegistryTemplate;

    /**
     *
     * @param lookupKey
     * @param serverNode
     * @return
     */
    @Override
    public boolean save(LookupKey lookupKey, ServerNode serverNode) {

        if (null == lookupKey || null == serverNode
                || StringUtils.isEmpty(lookupKey.getNamespace())
                || StringUtils.isEmpty(lookupKey.getServiceName())
                || null == lookupKey.getProtocolType()
                || null == serverNode.getInetSocketAddress()) {

            throw new IllegalArgumentException("LookupKey or ServerNode arg is invalid! ");
        }

        saveRegistryData(lookupKey, serverNode);
        return false;
    }

    private boolean saveRegistryData(LookupKey lookupKey, ServerNode serverNode) {
        String namespace = lookupKey.getNamespace();
        String serviceName = lookupKey.getServiceName();
        String protocolName = lookupKey.getProtocolType().name();

        // data table: ns_serviceName_protocol => ip:port:weight
        String key = StringUtils.join(new String[]{namespace, serviceName, protocolName}, KEY_FIELD_SEPARATOR);

        String value = StringUtils.join(new String[]{serverNode.getInetSocketAddress().getHostString(),
                String.valueOf(serverNode.getInetSocketAddress().getPort())}, VALUE_FIELD_SEPARATOR);
        redisRegistryTemplate.opsForHash().put(registryDataHashKey, key, value);

        // ns index table: ns => ns_serviceName_protocol
        redisRegistryTemplate.opsForHash().put(registryNamespaceIndexHashKey, namespace, key);

        // ns & serviceName index table: ns_serviceName => ns_serviceName_protocol
        String namespaceAndServiceNameIndexKey = StringUtils.join(new String[]{namespace, serviceName}, KEY_FIELD_SEPARATOR);
        redisRegistryTemplate.opsForHash().put(registryNamespaceAndServiceNameIndexHashKey, namespaceAndServiceNameIndexKey, key);
        return true;
    }

    @Override
    public List<Pair<LookupKey, ServerNode>> listByNamespace(String namespace) {

        return null;
    }

    @Override
    public List<Pair<LookupKey, ServerNode>> listByNamespaceAndServiceName(String namespace, String serviceName) {
        return null;
    }

    @Override
    public List<Pair<LookupKey, ServerNode>> listByLookupKey(LookupKey lookupKey) {
        return null;
    }

    @Override
    public void delete(LookupKey lookupKey) {

    }
}
