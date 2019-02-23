package gameday;

import data_structure.unionfind.UnionFindSet;

import java.util.HashMap;
import java.util.Map;

public class SurroundedRegions {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/surrounded-regions/description
     *
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length - 1, n = board[0].length - 1, l = n + 1;
        UnionFindSet set = new UnionFindSet();
        set.add(-1);

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (board[i][j] == 'O') {
                    int current = i * l + j;
                    set.add(current);

                    if (i == 0 || i == m || j == 0 || j == n) {
                        set.union(current, -1);
                    }

                    if (i > 0 && board[i - 1][j] == 'O') {
                        set.union(current, (i - 1) * l + j);
                    }

                    if (j > 0 && board[i][j - 1] == 'O') {
                        set.union(current, i * l + j - 1);
                    }
                }
            }
        }

        for (int i = 0; i <= m ; i++) {
            for (int j = 0; j <= n; j++) {
                if (board[i][j] == 'O' && !set.isUnion(i * l + j, -1)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private class UnionFindSet {
        Map<Integer, Integer> parent;

        public UnionFindSet() {
            this.parent = new HashMap<>();
        }

        public void add(int next) {
            if (parent.containsKey(next)) {
                return;
            } else {
                parent.put(next, next);
            }
        }

        public void union(int a, int b) {
            int parentA = getParent(a);
            int parentB = getParent(b);

            parent.put(parentA, parentB);
        }

        public boolean isUnion(int a, int b) {
            return getParent(a) == getParent(b);
        }

        private int getParent(int a) {
            if (parent.get(a) == a) {
                return a;
            }

            int parentA = getParent(parent.get(a));
            parent.put(a, parentA);

            return parentA;
        }
    }
}
