package indi.joynic.naga.server.registry.storage.impl;

import indi.joynic.naga.lib.LookupKey;
import indi.joynic.naga.lib.ProtocolType;
import indi.joynic.naga.lib.ServerNode;
import indi.joynic.naga.server.registry.storage.RegistryStorage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RedisRegistryStorageImpl implements RegistryStorage {

    private static final Logger logger = LoggerFactory.getLogger(RedisRegistryStorageImpl.class);

    private static final char KEY_FIELD_SEPARATOR = ',';
    private static final char VALUE_FIELD_SEPARATOR = ':';

    @Value("${naming.server.redis.registry.data.hash.key}")
    private String registryDataHashKey;

    /*@Value("${naming.server.redis.registry.namespace.index.set.key}")
    private String registryNamespaceIndexSetKey;

    @Value("${naming.server.redis.registry.namespace.servicename.index.set.key}")
    private String registryNamespaceAndServiceNameIndexSetKey;*/


    @Resource(name = "redisRegistryTemplate")
    private RedisTemplate<String, String> redisRegistryTemplate;

    /**
     * If the data exists, probably the client is doing register operation repeatedly.
     * Thus call hash.putIfAbsent() simply.
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

        boolean saveDataResult = redisRegistryTemplate.opsForHash().putIfAbsent(registryDataHashKey, key, value);

        // ns index table: ns => ns_serviceName_protocol
        Long saveNsIndexCount = redisRegistryTemplate.opsForSet().add(namespace, key);

        // ns & serviceName index table: ns_serviceName => ns_serviceName_protocol
        String namespaceAndServiceNameIndexKey = StringUtils.join(new String[]{namespace, serviceName},
                KEY_FIELD_SEPARATOR);

        Long saveNsServiceNameIndexCount = redisRegistryTemplate.opsForSet().add(namespaceAndServiceNameIndexKey, key);

        return saveDataResult && null != saveNsIndexCount && null != saveNsServiceNameIndexCount;
    }

    private Pair<LookupKey, ServerNode> locateWithLookupkeyImploded(String namespace, String lookupKeyImploded) {
        if (StringUtils.isEmpty(lookupKeyImploded)) {
            return null;
        }

        Object serverNodeImplodedResult = redisRegistryTemplate.opsForHash()
                .get(registryDataHashKey, lookupKeyImploded);

        if (null == serverNodeImplodedResult) {

            // bummer! not found map data, remove set index
            Long removeSetMemberCount = redisRegistryTemplate.opsForSet()
                    .remove(namespace, lookupKeyImploded);

            if (removeSetMemberCount == 0) { // remove failed

                logger.warn("remove dirty set index data from redis failed, key: {}, member: {}",
                        namespace, lookupKeyImploded);

            }

            return null;
        }

        String[] lookupKeyArr = lookupKeyImploded.split(String.valueOf(KEY_FIELD_SEPARATOR));

        String serverNodeImploded = (String) serverNodeImplodedResult;
        String[] serverNodeArr = serverNodeImploded.split(String.valueOf(VALUE_FIELD_SEPARATOR));

        LookupKey lookupKey = concreteLookupKey(lookupKeyArr);
        ServerNode serverNode = concreteServerNode(serverNodeArr);
        if (null == lookupKey || null == serverNode) {

            logger.error("Could not concrete lookupKey or serverNode, lookupKeyImploded: {}, serverNodeImploded",
                    lookupKeyImploded, serverNodeImploded);

        }

        return Pair.of(lookupKey, serverNode);
    }

    /**
     * client call for listing nodes by specifying namespace
     *
     * @param namespace
     * @return
     */
    @Override
    public List<Pair<LookupKey, ServerNode>> listByNamespace(String namespace) {
        if (StringUtils.isEmpty(namespace)) {
            return null;
        }
        Set<String> nsMembers = redisRegistryTemplate.opsForSet().members(namespace);
        if (CollectionUtils.isEmpty(nsMembers)) {
            return null;
        }

        List<Pair<LookupKey, ServerNode>> registries = new ArrayList<>();
        for (String lookupKeyImploded : nsMembers) {

            Pair<LookupKey, ServerNode> registry = locateWithLookupkeyImploded(namespace, lookupKeyImploded);
            if (null != registry) {
                registries.add(registry);
            }
        }

        return registries;
    }

    private LookupKey concreteLookupKey(String[] lookupKeyArr) {
        if (null == lookupKeyArr || lookupKeyArr.length < 3) {

            throw new IllegalArgumentException("lookupKeyArr is invalid! "
                    + ((null != lookupKeyArr) ? Arrays.toString(lookupKeyArr) : "null"));

        }

        String namespace = lookupKeyArr[0];
        String serviceName = lookupKeyArr[1];
        String protocolTypeName = lookupKeyArr[2];

        if (StringUtils.isEmpty(namespace) || StringUtils.isEmpty(serviceName) || StringUtils.isEmpty(protocolTypeName)) {
            return null;
        }

        return LookupKey.valueOf(namespace, serviceName, ProtocolType.valueOf(protocolTypeName.toUpperCase()));
    }

    private ServerNode concreteServerNode(String[] serverNodeArr) {
        if (null == serverNodeArr || serverNodeArr.length < 3) {

            throw new IllegalArgumentException("serverNodeArr is invalid! "
                    + ((null != serverNodeArr) ? Arrays.toString(serverNodeArr) : "null"));

        }

        String ip = serverNodeArr[0];
        String port = serverNodeArr[1];
        String weight = serverNodeArr[2];

        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(port) || StringUtils.isEmpty(weight)) {
            return null;
        }

        return ServerNode.valueOf(ip, Integer.valueOf(port), Integer.valueOf(weight));
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
