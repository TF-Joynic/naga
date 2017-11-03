/*
 * @author Terrance Fung<wkf.joynic@gmail.com>
 */

package indi.joynic.naga.client.agent.impl;

import indi.joynic.naga.client.agent.AgentConnectionManager;
import indi.joynic.naga.client.rpc.RpcConnection;


/**
 * role that manage connection(s) between naming-client and localhost agent.
 * LB is unnecessary.
 */
public class AgentConnectionManagerImpl implements AgentConnectionManager {

    public AgentConnectionManagerImpl() {

    }

    @Override
    public RpcConnection getConnection() {
        return null;
    }
}
