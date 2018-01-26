package indi.joynic.naga.portal.server.serviceprovider.register;

import indi.joynic.naga.portal.AccessSubject;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subject: Register serverNode on naming server Subject with Thrift.
 *
 *  You may using different <code>accessClient</code> such as HTTP(CURL) to access portal.
 *
 *  For example:
 *    public class RegisterOnNamingServerSubjectWithHttpCurl implements AccessSubject<Boolean> {
 *        ...
 *    }
 *
 * @author Terrance Fung<wkf.joynic@gmail.com>
 * @since 1.0
 */
public class RegisterOnServerSubjectWithThrift implements AccessSubject<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(RegisterOnServerSubjectWithThrift.class);


    private AccessArgs accessArgs;
    private ThriftNamingServerPortal.Iface accessClient;


    public RegisterOnServerSubjectWithThrift(AccessArgs accessArgs, ThriftNamingServerPortal.Iface accessClient) {
        this.accessArgs = accessArgs;
        this.accessClient = accessClient;
    }

    @Override
    public Boolean access() {
        boolean accessResult = false;

        try {
            
            accessResult = accessClient.doRegister(accessArgs.getNamespace(),
                    accessArgs.getProtocolType(), accessArgs.getServiceName(), accessArgs.getHost(),
                    accessArgs.getPort(), accessArgs.getWeight());

        } catch (TException e) {
            logger.error("RegisterOnServerSubjectWithThrift access err! {}", e.getMessage());
        }

        return accessResult;
    }

    /**
     * AccessArgs that using by accessClient
     */
    public static class AccessArgs {
        private String namespace;
        private String protocolType;
        private String serviceName;
        private String host;
        private int port;
        private int weight;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getProtocolType() {
            return protocolType;
        }

        public void setProtocolType(String protocolType) {
            this.protocolType = protocolType;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
