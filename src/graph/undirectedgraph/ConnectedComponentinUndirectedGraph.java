package graph.undirectedgraph;

import data_structure.unionfind.UnionFindSet;

import java.util.*;

public class ConnectedComponentinUndirectedGraph {
    /**
     * 3/11/2019
     * Union find set
     *
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return new ArrayList<>();
        }

        UnionFindSet<UndirectedGraphNode> set = new UnionFindSet<>();
        for (UndirectedGraphNode node : nodes) {
            set.add(node);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                set.add(neighbor);
                set.union(node, neighbor);
            }
        }

        Set<Set<UndirectedGraphNode>> components = set.getElementsAlongComponent();
        List<List<Integer>> result = new ArrayList<>();

        for (Set<UndirectedGraphNode> component : components) {
            List<Integer> list = new ArrayList<>();
            for (UndirectedGraphNode node : component) {
                list.add(node.label);
            }
            Collections.sort(list);
            result.add(list);
        }

        return result;
    }

    /**
     * 3/11/2019
     * BFS
     *
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet_bfs(List<UndirectedGraphNode> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Set<UndirectedGraphNode>> components = new HashSet<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();

        for (UndirectedGraphNode node : nodes) {
            if (visited.contains(node)) {
                continue;
            }

            Set<UndirectedGraphNode> component = getConnectedComponents(node, visited);
            components.add(component);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (Set<UndirectedGraphNode> component : components) {
            List<Integer> list = new ArrayList<>();
            for (UndirectedGraphNode node : component) {
                list.add(node.label);
            }
            Collections.sort(list);
            result.add(list);
        }

        return result;
    }

    private Set<UndirectedGraphNode> getConnectedComponents(UndirectedGraphNode node, Set<UndirectedGraphNode> visited) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);

        Set<UndirectedGraphNode> component = new HashSet<>();
        while (!queue.isEmpty()) {
            UndirectedGraphNode current = queue.remove();
            component.add(current);

            for (UndirectedGraphNode neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return component;
    }
}
