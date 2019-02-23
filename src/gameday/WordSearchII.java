package gameday;

import java.util.*;

public class WordSearchII {
    /**
     * 2/23/2019
     * GameDay
     * https://www.lintcode.com/problem/word-search-ii/description
     *
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        if (board == null || board.length == 0) {
            return new ArrayList<>();
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        Set<String> set = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        for (int i = 0; i <= board.length - 1; i++) {
            for (int j = 0; j <= board[i].length - 1; j++) {
                dfs(board, i, j, trie.root.next[board[i][j] - 'a'], set, visited, "", dx, dy);
            }
        }

        return new ArrayList<>(set);
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, Set<String> set, Set<Integer> visited, String current, int[] dx, int[] dy) {
        if (node == null) {
            return;
        }

        if (visited.contains(i * board[i].length + j)) {
            return;
        }

        current += node.c;
        visited.add(i * board[i].length + j);

        if (node.isWord) {
            set.add(current);
        }

        for (int d = 0; d <= 3; d++) {
            int x = i + dx[d], y = j + dy[d];
            if (x < 0 || x > board.length - 1 || y < 0 || y > board[x].length - 1) {
                continue;
            }

            dfs(board, x, y, node.next[board[x][y] - 'a'], set, visited, current, dx, dy);
        }

        visited.remove(i * board[i].length + j);
    }

    private class TrieNode {
        char c;
        boolean isWord;
        TrieNode[] next;

        private TrieNode(char c) {
            this.c = c;
            this.isWord = false;
            this.next = new TrieNode[26];
        }
    }

    private class Trie {
        private TrieNode root;

        private Trie() {
            this.root = new TrieNode('#');
        }

        private void add(String next) {
            TrieNode current = this.root;

            for (char c : next.toCharArray()) {
                if (current.next[c - 'a'] == null) {
                    current.next[c - 'a'] = new TrieNode(c);
                }

                current = current.next[c - 'a'];
            }

            current.isWord = true;
        }
    }
}