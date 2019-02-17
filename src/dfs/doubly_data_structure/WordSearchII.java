package dfs.doubly_data_structure;

import data_structure.trie.Trie;
import data_structure.trie.TrieNode;

import java.util.*;

public class WordSearchII {
    /**
     * 2/9/2019
     * DFS on trie with trienode current level
     *
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII_trienode_current_level(char[][] board, List<String> words) {
        if (words == null || words.size() == 0) {
            return new ArrayList<>();
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> set = new HashSet<>();

        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        for (int i = 0; i <= board.length - 1; i++) {
            for (int j = 0; j <= board[i].length - 1; j++) {
                dfs_trienode_current_level("", trie.root, i, j, set, board, visited, dx, dy);
            }
        }

        return new ArrayList<>(set);
    }

    private void dfs_trienode_current_level(String current, TrieNode node, int i, int j, Set<String> set, char[][] board, boolean[][] visited, int[] dx, int[] dy) {
        node = node.next[board[i][j] - 'a'];
        visited[i][j] = true;
        current += node.c;

        if (node.isWord) {
            set.add(current);
        }

        for (int d = 0; d <= 3; d++) {
            int x = i + dx[d], y = j + dy[d];
            if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
                continue;
            }

            if (visited[x][y]) {
                continue;
            }

            if (node.next[board[x][y] - 'a'] == null) {
                continue;
            }

            dfs_trienode_current_level(current, node, x, y, set, board, visited, dx, dy);
        }

        /// Unselect
        visited[i][j] = false;
    }


    /**
     * 2/9/2019
     * DFS on trie with next level state
     *
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII_next_level(char[][] board, List<String> words) {
        if (words == null || words.size() == 0) {
            return new ArrayList<>();
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> set = new HashSet<>();

        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        for (int i = 0; i <= board.length - 1; i++) {
            for (int j = 0; j <= board[i].length - 1; j++) {
                dfs_next_level("", trie.root.next[board[i][j] - 'a'], i, j, set, board, visited, dx, dy);
            }
        }

        return new ArrayList<>(set);
    }

    private void dfs_next_level(String current, TrieNode node, int i, int j, Set<String> set, char[][] board, boolean[][] visited, int[] dx, int[] dy) {
        if (node == null) {
            return;
        }

        /// Select
        current += board[i][j];
        visited[i][j] = true;

        if (node.isWord) {
            set.add(current);
        }

        for (int d = 0; d <= 3; d++) {
            int x = i + dx[d], y = j + dy[d];
            if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
                continue;
            }

            if (visited[x][y]) {
                continue;
            }

            dfs_next_level(current, node.next[board[x][y] - 'a'], x, y, set, board, visited, dx, dy);
        }

        /// Unselect
        visited[i][j] = false;
    }


    public static void main(String[] args) {
        char[][] matrix = {{'d','o','a','f'}, {'a','g','a','i'}, {'d','c','a','n'}};
        List<String> words = Arrays.asList("dog","dad","dgdg","can","again");

        System.out.println(new WordSearchII().wordSearchII_trienode_current_level(matrix, words));
    }
}
