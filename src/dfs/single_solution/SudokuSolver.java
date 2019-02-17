package dfs.single_solution;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {
    /**
     * 2/8/2019
     * DFS on single Solution
     *
     * @param board: the sudoku puzzle
     * @return: nothing
     */
    public void solveSudoku(int[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][][] sub = new boolean[9][9][10];

        for (int r = 0; r <= 8; r++) {
            for (int c = 0; c <= 8; c++) {
                int x = board[r][c];
                if (x != 0) {
                    row[r][x] = true;
                    col[c][x] = true;
                    sub[r / 3][c / 3][x] = true;
                }
            }
        }

        if (dfs(0, board, row, col, sub)) {
            return;
        } else {
            throw new RuntimeException("Solution not Found!");
        }
    }

    private boolean dfs(int i, int[][] board, boolean[][] row, boolean[][] col, boolean[][][] sub) {
        if (i == 81) {
            return true;
        }

        int r = i / 9, c = i % 9;

        if (board[r][c] != 0) {
            return dfs(i + 1, board, row, col, sub);
        } else {
            for (int x = 1; x <= 9; x++) {
                if (!row[r][x] && !col[c][x] && !sub[r / 9][c / 9][x]) {
                    row[r][x] = col[c][x] = sub[r / 9][c / 9][x] = true;
                    board[r][c] = x;

                    if (dfs(i + 1, board, row, col, sub)) {
                        return true;
                    }

                    row[r][x] = col[c][x] = sub[r / 9][c / 9][x] = false;
                    board[r][c] = 0;
                }
            }
        }

        return false;
    }
}
