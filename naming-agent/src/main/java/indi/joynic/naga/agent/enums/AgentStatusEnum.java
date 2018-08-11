package indi.joynic.naga.agent.enums;

/**
 * status of this agent server
 */
public enum AgentStatusEnum {
    /***
     * init config & try connect naming server
     */
    INIT,

    /**
     * SYNCING data from server
     */
    SYNCING,

    /**
     * server push data to this agent, agent doing update( old data & cache).
     */
    UPDATING,

    /**
     * agent is serving. client request is permitted.
     */
    SERVING,

    /**
     * agent no longer serving, client request is declined.
     */
    CLOSED;
}
