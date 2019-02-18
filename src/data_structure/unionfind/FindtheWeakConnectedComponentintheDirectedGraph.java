package data_structure.unionfind;

import util.DirectedGraphNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindtheWeakConnectedComponentintheDirectedGraph {
    /**
     * 1/2
     * UnionFindSet
     *
     * @param nodes: a array of Directed graph node
     * @return: a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(final ArrayList<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<>();
        }

        UnionFindSet<DirectedGraphNode> ufs = new UnionFindSet<>();
        for (DirectedGraphNode node : nodes) {
            ufs.add(node);
        }

        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                ufs.union(node, neighbor);
            }
        }

        Set<Set<DirectedGraphNode>> elementsAlongComponents = ufs.getElementsAlongComponent();
//        List<List<Integer>> list = elementsAlongComponents.stream().map(components -> {
//            List<Integer> current = components.stream().map(node -> node.label).collect(Collectors.toList());
//            Collections.sort(current);
//            return current;
//        }).collect(Collectors.toList());

        List<List<Integer>> list = new ArrayList<>();
        for (Set<DirectedGraphNode> sets : elementsAlongComponents) {
            List<Integer> current = new ArrayList<>();
            for (DirectedGraphNode node : sets) {
                current.add(node.label);
            }
            Collections.sort(current);
            list.add(current);
        }


        return list;
    }
}
