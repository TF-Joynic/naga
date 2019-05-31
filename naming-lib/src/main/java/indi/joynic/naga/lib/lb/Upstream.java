package indi.joynic.naga.lib.lb;

import indi.joynic.naga.lib.lb.node.ServiceNode;

import java.util.Iterator;
import java.util.Set;

/**
 * Wrapper class of ServiceNode(s)
 *
 * A group of backend servers with totalWeight and could be iterated over.
 *
 * @author Terrance Fung
 * @since 1.0
 */
public class Upstream implements Iterator<ServiceNode> {

    // sum of weight of all nodes
    private int totalWeight;

    // all nodes of this upstream known to this very client
    private final Set<ServiceNode> nodes;

    public Upstream(Set<ServiceNode> serviceNodes) {
        this.nodes = serviceNodes;
    }

    public void init() {
        int sumWeight = 0;
        if (null != nodes) {
            for (ServiceNode serviceNode : nodes) {
                sumWeight += serviceNode.getWeight();
            }
        }

        totalWeight = sumWeight;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public ServiceNode next() {
        return null;
    }

    @Override
    public void remove() {
        // TODO
    }
}
