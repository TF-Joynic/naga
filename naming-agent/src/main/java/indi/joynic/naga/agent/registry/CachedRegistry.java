package indi.joynic.naga.agent.registry;

import indi.joynic.naga.lib.Registry;

/**
 * client-side local registry.
 * when the naming server is down or unreachable, client uses local cache.
 *
 * @author Terrance Fung
 * @since 1.0
 */
public interface CachedRegistry extends Registry {

    /**
     * clear all the local registry cache once for all
     */
    void clear();
}
