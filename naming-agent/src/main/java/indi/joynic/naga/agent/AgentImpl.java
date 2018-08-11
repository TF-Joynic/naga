package indi.joynic.naga.agent;

import indi.joynic.naga.agent.registry.CachedRegistry;
import indi.joynic.naga.lib.LookupKey;

import java.util.concurrent.atomic.AtomicInteger;

public class AgentImpl implements Agent {

    /**
     * 0 - INIT(init config & try connect naming server)
     * 1 - SYNCING(data from server)
     * 2 - UPDATING(server push data to this agent, agent doing update( old data & cache)).
     * 3 - SERVING (agent is serving. client request is permitted.)
     * 4 - CLOSED(agent no longer serving, client request is declined.)
     */
    private final AtomicInteger state = new AtomicInteger(0);

    @Override
    public boolean compareAndSetState(int expect, int update) {
        return state.compareAndSet(expect, update);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void sendSyncRequest() {

    }

    @Override
    public void lookup(LookupKey lookupKey) {
        return CachedRegistry。
    }

    @Override
    public void updateLocalCache() {

    }
}