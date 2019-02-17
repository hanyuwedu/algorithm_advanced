package dfs.continuous_enumeration;

import java.util.ArrayList;
import java.util.List;

public class NQueensII {
    /**
     * 2/8/2019
     * DFS on linear structure
     *
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        List<Integer> queens = new ArrayList<>();
        int[] solutions = {0};

        dfs(queens, 0, n, solutions);

        return solutions[0];
    }

    private void dfs(List<Integer> queens, int i, int n, int[] solutions) {
        if (i == n) {
            solutions[0]++;
            return;
        }

        for (int x = 0; x <= n - 1; x++) {
            boolean valid = true;

            for (int j = 0; j < i; j++) {
                if (queens.get(j) == x || i + x == j + queens.get(j) || i - x == j - queens.get(j)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                queens.add(x);
                dfs(queens, i + 1, n, solutions);
                queens.remove(queens.size() - 1);
            }
        }
    }
}
