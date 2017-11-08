package indi.joynic.naga.server.heal;

import indi.joynic.naga.lib.ServerNode;

/**
 * Role which attempt to heal server node and put it back to the heath server node list.
 * It shall be an Daemon thread that heals bad server node.
 *
 * @since 1.0
 */
public interface Healer {

    /**
     * attempt to heal the server node.
     * return true if successfully headed this server node, false otherwise.
     *
     * @param serverNode
     * @return
     */
    boolean heal(ServerNode serverNode);


}
