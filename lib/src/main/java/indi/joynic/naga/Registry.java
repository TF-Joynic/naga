package indi.joynic.naga;

import java.util.Set;

/**
 * The registry class that hold node lists of healthy services.
 * Each service and node are one-to-many mapping.
 *
 * @author Terrance Fung<wkf.joynic@gmail.com>
 * @since 1.0
 */
public interface Registry {

    /**
     * lookup node server list by specifying service name
     *
     * @return
     */
    Set<ServerNode> lookup(String serviceName);

    /**
     * remove a invalid server node
     * on account of server is no longer able to provide service
     *
     * @return
     */
    boolean evict(String serviceName, ServerNode serverNode);
}
