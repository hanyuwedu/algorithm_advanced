package gameday;

import data_structure.unionfind.UnionFindSet;

public class SurroundedRegions {
    /**
     * 1/2
     * UnionFindSet
     *
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
        if (board == null|| board.length == 0) {
            return;
        }

        int m = board.length, n = board[0].length;

        UnionFindSet<Integer> ufs = new UnionFindSet<>();
        ufs.add(-1);

        for (int i = 0; i <= m - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                if (board[i][j] == '0') {
                    int current = i * n + j;
                    ufs.add(current);

                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        ufs.union(current, -1);
                    }

                    if (i > 0 && board[i - 1][j] == '0') {
                        ufs.union(current, (i - 1) * n + j);
                    }

                    if (j > 0 && board[i][j - 1] == '0') {
                        ufs.union(current, i * n + j - 1);
                    }
                }
            }
        }

        for (int i = 0; i <= m - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                if (board[i][j] == '0' && !ufs.isUnioned(i * n + j, -1)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
