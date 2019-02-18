package data_structure.unionfind;

public class NumberofIslands {
    /**
     * 12/31
     * Union Find Set
     *
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        UnionFindSet<Integer> ufs = new UnionFindSet<>();

        for (int i = 0; i <= m - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                if (grid[i][j]) {
                    ufs.add(i * n + j);

                    if (i > 0 && grid[i - 1][j]) {
                        ufs.union(i * n + j, (i - 1) * n + j);
                    }

                    if (j > 0 && grid[i][j - 1]) {
                        ufs.union(i * n + j, i * n + j - 1);
                    }
                }
            }
        }

        return ufs.getComponents();
    }
}
