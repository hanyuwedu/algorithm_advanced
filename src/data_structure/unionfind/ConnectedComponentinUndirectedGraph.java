package data_structure.unionfind;

import util.DirectedGraphNode;
import util.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ConnectedComponentinUndirectedGraph {
    /**
     * 1/2
     * UnionFindSet
     *
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(final List<UndirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<>();
        }

        UnionFindSet<UndirectedGraphNode> ufs = new UnionFindSet<>();
        for (UndirectedGraphNode node : nodes) {
            ufs.add(node);
        }

        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbor : node.neighbors) {
                ufs.union(node, neighbor);
            }
        }

        Set<Set<UndirectedGraphNode>> elementsAlongComponents = ufs.getElementsAlongComponent();
//        List<List<Integer>> list = elementsAlongComponents.stream().map(components -> {
//            List<Integer> current = components.stream().map(node -> node.label).collect(Collectors.toList());
//            Collections.sort(current);
//            return current;
//        }).collect(Collectors.toList());

        List<List<Integer>> list = new ArrayList<>();
        for (Set<UndirectedGraphNode> sets : elementsAlongComponents) {
            List<Integer> current = new ArrayList<>();
            for (UndirectedGraphNode node : sets) {
                current.add(node.label);
            }
            Collections.sort(current);
            list.add(current);
        }


        return list;
    }
}
