package indi.joynic.naga.agent.registry;

import indi.joynic.naga.lib.Registry;

/**
 * local registry.
 * while the naming server is down or unreachable, return cached registry data to client.
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
