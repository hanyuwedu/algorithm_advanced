package data_structure.unionfind;

public class GraphValidTree {
    /**
     * 12/31
     * Union Find Set
     *
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (edges.length == 0) {
            if (n == 1) {
                return true;
            } else {
                return false;
            }
        }

        UnionFindSet<Integer> ufs = new UnionFindSet<>();
        for (int i = 0; i <= n - 1; i++) {
            ufs.add(i);
        }

        for (int[] edge : edges) {
            if (!ufs.union(edge[0], edge[1])) {
                return false;
            }
        }

        return ufs.getComponents() == 1;
    }
}
