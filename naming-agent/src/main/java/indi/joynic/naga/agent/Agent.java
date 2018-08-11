package indi.joynic.naga.agent;

import indi.joynic.naga.lib.LookupKey;

/**
 * an agent interface that describe its very responsibilities
 *
 * @author Terrance Fung
 */
public interface Agent {

    /**
     * turn into new state
     *
     * @param expect
     * @param update
     */
    boolean compareAndSetState(int expect, int update);

    /**
     * validate config, try connect server etc
     */
    void initialize();

    /**
     * send request to server to sync registry data from server
     */
    void sendSyncRequest();

    /**
     * naming lookup
     *
     * @param lookupKey
     */
    void lookup(LookupKey lookupKey);

    /**
     * update local registry cache
     */
    void updateLocalCache();
}
