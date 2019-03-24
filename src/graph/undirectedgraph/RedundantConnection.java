package graph.undirectedgraph;

import data_structure.unionfind.UnionFindSet;

public class RedundantConnection {
    /**
     * 3/11/2019
     * Union Find Set
     *
     * @param edges: List[List[int]]
     * @return: List[int]
     */
    public int[] findRedundantConnection(int[][] edges) {
        UnionFindSet<Integer> set = new UnionFindSet<>();
        for (int[] edge : edges) {
            set.add(edge[0]);
            set.add(edge[1]);
            if (set.isUnioned(edge[0], edge[1])) {
                return edge;
            } else {
                set.union(edge[0], edge[1]);
            }
        }

        return null;
    }
}
