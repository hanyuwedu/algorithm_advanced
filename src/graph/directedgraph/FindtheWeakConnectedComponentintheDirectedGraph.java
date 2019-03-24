package graph.directedgraph;

import data_structure.unionfind.UnionFindSet;

import java.util.*;

public class FindtheWeakConnectedComponentintheDirectedGraph {
    /**
     * 3/18/2019
     * Union Find Set
     *
     * @param nodes: a array of Directed graph node
     * @return: a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return new ArrayList<>();
        }

        UnionFindSet<DirectedGraphNode> set = new UnionFindSet<>();
        for (DirectedGraphNode node : nodes) {
            set.add(node);
        }

        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                set.union(node, neighbor);
            }
        }

        Set<Set<DirectedGraphNode>> elementsAlongComponents = set.getElementsAlongComponent();
        List<List<Integer>> connectedSet = new ArrayList<>();
        for (Set<DirectedGraphNode> components : elementsAlongComponents) {
            List<Integer> list = new ArrayList<>();
            for (DirectedGraphNode node : components) {
                list.add(node.label);
            }
            Collections.sort(list);
            connectedSet.add(list);
        }

        return connectedSet;
    }
}
