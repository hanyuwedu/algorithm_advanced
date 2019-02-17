package dfs.doubly_data_structure;

import data_structure.trie.Trie;
import data_structure.trie.TrieNode;
import util.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BoggleGame {
    /**
     * 2/9/2019
     * DFS on trie
     *
     * @param board: a list of lists of character
     * @param words: a list of string
     * @return: an integer
     */
    public int boggleGame(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        int[] max = {0};
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        collectWords(0, 0, max, visited, board, trie, dx, dy);

        return max[0];
    }

    private void collectWords(int pos, int count, int[] max, boolean[][] visited, char[][] board, Trie trie, int[] dx, int[] dy) {
        int len = board[0].length;
        int r = pos / len, c = pos % len;

        if (r == board.length) {
            max[0] = Math.max(max[0], count);
            return;
        }

        collectWords(pos + 1, count, max, visited, board, trie, dx, dy);

        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> candidates = new ArrayList<>();
        dfs_next_level_trienode(r, c, trie.root.next[board[r][c] - 'a'], board, visited, stack, candidates, dx, dy);
//        dfs_current_level_trienode(r, c, trie.root, board, visited, stack, candidates, dx, dy);

        System.out.println(candidates);

        for (List<Integer> candidate : candidates) {
            for (int i : candidate) {
                visited[i / len][i % len] = true;
            }

            collectWords(pos + 1, count + 1, max, visited, board, trie, dx, dy);

            for (int i : candidate) {
                visited[i / len][i % len] = false;
            }
        }
    }

    private void dfs_current_level_trienode(int r, int c, TrieNode node, char[][] board, boolean[][] visited, Stack<Integer> stack, List<List<Integer>> candidates, int[] dx, int[] dy) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return;
        }

        if (visited[r][c]) {
            return;
        }

        if (node.next[board[r][c] - 'a'] == null) {
            return;
        }

        /// Select
        node = node.next[board[r][c] - 'a'];
        stack.push(r * board[0].length + c);
        visited[r][c] = true;

        if (node.isWord) {
            candidates.add(new ArrayList<>(stack));
            stack.pop();
            visited[r][c] = false;
            return;
        }

        for (int d = 0; d <= 3; d++) {
            dfs_current_level_trienode(r + dx[d], c + dy[d], node, board, visited, stack, candidates, dx, dy);
        }

        stack.pop();
        visited[r][c] = false;
    }

    private void dfs_next_level_trienode(int r, int c, TrieNode node, char[][] board, boolean[][] visited, Stack<Integer> stack, List<List<Integer>> candidates, int[] dx, int[] dy) {
        if (node == null) {
            return;
        }

        if (visited[r][c]) {
            return;
        }

        stack.push(r * board[0].length + c);
        visited[r][c] = true;

        if (node.isWord) {
            candidates.add(new ArrayList<>(stack));
            stack.pop();
            visited[r][c] = false;
            return;
        }

        for (int d = 0; d <= 3; d++) {
            int x = r + dx[d], y = c + dy[d];

            if (x < 0 || x >= board.length || y < 0 || y >= board[r].length) {
                continue;
            }

            dfs_next_level_trienode(x, y, node.next[board[x][y] - 'a'], board, visited, stack, candidates, dx, dy);
        }

        stack.pop();
        visited[r][c] = false;
    }


    public static void main(String[] args) {
//        char[][] board = {{'d','o','a','f'}, {'a','g','a','i'}, {'d','c','a','n'}};
//        String[] words = {"dog", "dad", "dgdg", "can", "again", "fin"};

        char[][] board = {{'a','b','c'}, {'d','e','f'}, {'g','h','i'}};
        String[] words = {"abc", "defi", "gh"};

//        char[][] board = {{'a','a','a','a'}, {'a','a','a','a'}, {'a','a','a','a'}, {'a','a','a','a'}};
//        String[] words = {"a"};

//        String[] strs = {"wuiiuww"};
//        String[] strs = {"ghihjui","wuiiuww"};
//        String[] strs = {"lintcodeisnice","lintcodeisgood","uilwowwwwowwqw","poiuywqaazxcvw","poiuytrewzxcvb","tyuwqazxhuwqaz"};
//        String[] words = {"a","lint","code","ccode","poiy","ppty","cvbwwd","ngoo"};



//        char[][] board = Utilities.stringToCharMatrix(strs);
        System.out.println(new BoggleGame().boggleGame(board, words));
    }
}
