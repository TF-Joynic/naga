package indi.joynic.naga.lib;

/**
 * LookupKey class, Encapsulation of elements to locate a service provider node.
 */
public class LookupKey {

    private String namespace;
    private String serviceName;
    private ProtocolType protocolType;

    public LookupKey(String namespace, String serviceName, ProtocolType protocolType) {
        this.namespace = namespace;
        this.serviceName = serviceName;
        this.protocolType = protocolType;
    }

    /**
     * static factory
     *
     * @param namespace
     * @param serviceName
     * @param protocolType
     * @return
     */
    public static LookupKey valueOf(String namespace, String serviceName, ProtocolType protocolType) {
        return new LookupKey(namespace, serviceName, protocolType);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getServiceName() {
        return serviceName;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LookupKey lookupKey = (LookupKey) o;

        if (!namespace.equals(lookupKey.namespace)) return false;
        if (!serviceName.equals(lookupKey.serviceName)) return false;
        return protocolType == lookupKey.protocolType;
    }

    @Override
    public int hashCode() {
        int result = namespace.hashCode();
        result = 31 * result + serviceName.hashCode();
        result = 31 * result + protocolType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LookupKey{" +
                "namespace='" + namespace + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", protocolType=" + protocolType +
                '}';
    }
}
