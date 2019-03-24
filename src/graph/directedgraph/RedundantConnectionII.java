package graph.directedgraph;

import java.util.*;

public class RedundantConnectionII {
    /**
     * 3/19/2019
     * DFS in finding cycle
     *
     * @param edges: List[List[int]]
     * @return: return List[int]
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, List<Integer>> graph = generateGraph(edges);
        Set<Integer> cycle = getCycle(graph);

        System.out.println(graph);

        int root = getRoot(graph);
        if (cycle.isEmpty()) {
            for (int i = edges.length - 1; i >= 0; i--) {
                if (edges[i][1] == root) {
                    return edges[i];
                }
            }
        } else {
            if (root != -1) {
                for (int i = edges.length - 1; i >= 0; i--) {
                    if (edges[i][1] == root && cycle.contains(edges[i][0])) {
                        return edges[i];
                    }
                }
            } else {
                for (int i = edges.length - 1; i >= 0; i--) {
                    if (cycle.contains(edges[i][0]) && cycle.contains(edges[i][1])) {
                        return edges[i];
                    }
                }
            }
        }

        return new int[2];
    }

    private Map<Integer,List<Integer>> generateGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }

            if (!graph.containsKey(edge[1])) {
                graph.put(edge[1], new ArrayList<>());
            }

            graph.get(edge[0]).add(edge[1]);
        }

        return graph;
    }

    private int getRoot(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int node : graph.keySet()) {
            for (int neighbor : graph.get(node)) {
                indegree.put(neighbor, indegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        for (int root : indegree.keySet()) {
            if (indegree.get(root) > 1) {
                return root;
            }
        }

        return -1;
    }

    private Set<Integer> getCycle(Map<Integer, List<Integer>> graph) {
        int root = -1;
        for (int node : graph.keySet()) {
            root = findRoot(node, graph);
            if (root != -1) {
                break;
            }
        }

        if (root != -1) {
            return generateCycle(root, graph);
        } else {
            return new HashSet<>();
        }
    }

    private int findRoot(int node, Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        visited.add(node);

        for (int next : graph.get(node)) {
            int root = findRoot(next, graph, visited);
            if (root != -1) {
                return root;
            }
        }

        return -1;
    }

    private int findRoot(int next, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (visited.contains(next)) {
            return next;
        }

        visited.add(next);
        for (int neighbor : graph.get(next)) {
            int root = findRoot(neighbor, graph, visited);
            if (root != -1) {
                return root;
            }
        }

        visited.remove(next);
        return -1;
    }

    private Set<Integer> generateCycle(int node, Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();

        return generateCycle(node, node, graph, visited);
    }

    private Set<Integer> generateCycle(int next, int root, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (root == next && !visited.isEmpty()) {
            return visited;
        }

        visited.add(next);
        for (int neighbor : graph.get(next)) {
            Set<Integer> cycle = generateCycle(neighbor, root, graph, visited);
            if (!cycle.isEmpty()) {
                return cycle;
            }
        }

        visited.remove(next);
        return new HashSet<>();
    }

    public static void main(String[] args) {
//        int[][] input = {{1,2}, {1,3}, {2,3}};
//        int[][] input = {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
        int[][] input = {{13,11},{16,5},{11,14},{7,10},{12,19},{20,6},{5,9},{3,16},{8,7},{15,2},{1,18},{14,8},{17,3},{6,12},{18,13},{9,15},{4,18},{10,20},{19,17},{2,4}};
        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(input)));
    }
}
