package graph.undirectedgraph;

import data_structure.unionfind.UnionFindSet;

public class GraphValidTree {
    /**
     * 3/11/2019
     * UnionFindSet
     *
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        UnionFindSet<Integer> set = new UnionFindSet<>();
        for (int i = 0; i <= n - 1; i++) {
            set.add(i);
        }

        for (int[] edge : edges) {
            if (set.isUnioned(edge[0], edge[1])) {
                return false;
            } else {
                set.union(edge[0], edge[1]);
            }
        }

        return set.getComponents() == 1;
    }
}
