package dfs.continuous_enumeration;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    /**
     * 2/8/2019
     * DFS on linear structure
     *
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<Integer> queens = new ArrayList<>();
        List<List<String>> boards = new ArrayList<>();

        dfs(queens, 0, n, boards);

        return boards;
    }

    private void dfs(List<Integer> queens, int i, int n, List<List<String>> boards) {
        if (i == n) {
            boards.add(draw(queens));
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
                dfs(queens, i + 1, n, boards);
                queens.remove(queens.size() - 1);
            }
        }
    }

    private List<String> draw(List<Integer> queens) {
        List<String> board = new ArrayList<>();
        int n = queens.size();

        for (int i = 0; i <= n - 1; i++) {
            char[] chars = new char[n];
            for (int j = 0; j <= n - 1; j++) {
                if (j == queens.get(i)) {
                    chars[j] = 'Q';
                } else {
                    chars[j] = '.';
                }
            }
            board.add(new String(chars));
        }

        return board;
    }
}
