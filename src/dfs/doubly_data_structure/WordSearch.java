package dfs.doubly_data_structure;

public class WordSearch {
    /**
     * 2/9/2019
     * DFS on parallel data structure
     *
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        for (int i = 0; i <= board.length - 1; i++) {
            for (int j = 0; j <= board[i].length - 1; j++) {
                if (dfs(0, i, j, visited, board, word, dx, dy)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int index, int i, int j, boolean[][] visited, char[][] board, String word, int[] dx, int[] dy) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return false;
        }

        if (visited[i][j]) {
            return false;
        }

        if (board[i][j] != word.charAt(index)) {
            return false;
        }

        visited[i][j] = true;

        for (int d = 0; d <= 3; d++) {
            if (dfs(index + 1, i + dx[d], j + dy[d], visited, board, word, dx, dy)) {
                return true;
            }
        }

        visited[i][j] = false;

        return false;
    }

    /**
     * 2/9/2019
     * DFS on parallel data structure
     *
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist_current_index(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        for (int i = 0; i <= board.length - 1; i++) {
            for (int j = 0; j <= board[i].length - 1; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (dfs_current_index(1, i, j, visited, board, word, dx, dy)) {
                        return true;
                    }
                    visited[i][j] = false;
                }

            }
        }

        return false;
    }

    private boolean dfs_current_index(int index, int i, int j, boolean[][] visited, char[][] board, String word, int[] dx, int[] dy) {
        if (index == word.length()) {
            return true;
        }

        for (int d = 0; d <= 3; d++) {
            int x = i + dx[d], y = j + dy[d];

            if (x < 0 || x >= board.length || y < 0 || y >= board[i].length) {
                continue;
            }

            if (visited[x][y]) {
                continue;
            }

            if (board[x][y] != word.charAt(index)) {
                continue;
            }

            visited[x][y] = true;

            if (dfs_current_index(index + 1, x, y, visited, board, word, dx, dy)) {
                return true;
            }

            visited[x][y] = false;
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'a', 'b'}, {'c', 'd'}};
        System.out.println(new WordSearch().exist_current_index(board, "abdc"));
    }
}
