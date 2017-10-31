package indi.joynic.naga.server.client.registry;

import indi.joynic.naga.server.client.lib.Registry;

/**
 * client-side local registry cache.
 * when the naming server is down or unreachable, client uses local cache.
 *
 * @author Terrance Fung
 * @since 1.0
 */
public interface RegistryCache extends Registry {

    /**
     * clear all the local registry cache once for all
     */
    void clear();
}
