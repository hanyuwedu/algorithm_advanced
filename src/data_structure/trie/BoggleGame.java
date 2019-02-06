package data_structure.trie;

import util.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BoggleGame {
    /**
     * 2/4/2019
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

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        Stack<String> collects = new Stack<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int[] max = {0};


        collectWords(board, visited, trie, collects, max, 0, dx, dy);
        return max[0];
    }

    private void collectWords(char[][] board, boolean[][] visited, Trie trie, Stack<String> collects, int[] max, int pos, int[] dx, int[] dy) {
        max[0] = Math.max(max[0], collects.size());

        int i = pos / board[0].length, j = pos % board[0].length;

        if (i >= board.length) {
            return;
        }

        if (visited[i][j]) {
            return;
        }

        List<String> availableWords = new ArrayList<>();
        List<List<Integer>> availableIndecies = new ArrayList<>();
        Stack<Character> chars = new Stack<>();
        Stack<Integer> indecies = new Stack<>();

        findAvailableWords(board, chars, indecies, availableWords, availableIndecies, visited, trie.root, i, j, dx, dy);

        System.out.println(pos);
        System.out.println(availableWords);

        for (int n = 0; n <= availableWords.size() - 1; n++) {
            selectWord(collects, availableWords.get(n), visited, availableIndecies.get(n));

            for (int p = pos + 1; p <= board.length * board[0].length; p++) {
                collectWords(board, visited, trie, collects, max, p, dx, dy);
            }

            unselectWord(collects, visited, availableIndecies.get(n));
        }

        for (int p = pos + 1; p <= board.length * board[0].length; p++) {
            collectWords(board, visited, trie, collects, max, p, dx, dy);
        }
    }



    private void findAvailableWords(char[][] board, Stack<Character> chars, Stack<Integer> indecies, List<String> availableWords, List<List<Integer>> indexes, boolean[][] visited, TrieNode current, int i, int j, int[] dx, int[] dy) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        if (visited[i][j]) {
            return;
        }

        if (current.next[board[i][j] - 'a'] == null) {
            return;
        }

        current = current.next[board[i][j] - 'a'];
        visited[i][j] = true;
        chars.push(current.c);
        indecies.push(i * board[0].length + j);

        if (current.isWord) {
            availableWords.add(stacktoString(chars));
            indexes.add(new ArrayList<>(indecies));
        }

        for (int d = 0; d <= 3; d++) {
            int x = i + dx[d], y = j + dy[d];

            findAvailableWords(board, chars, indecies, availableWords, indexes, visited, current, x, y, dx, dy);
        }

        visited[i][j] = false;
        chars.pop();
        indecies.pop();

//        for (int d = 0; d <= 3; d++) {
//            int x = i + dx[d], y = j + dy[d];
//
//            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
//                continue;
//            }
//
//            if (visited[x][y]) {
//                continue;
//            }
//
//            if (current.next[board[i][j] - 'a'] == null) {
//                continue;
//            }
//
//            /// Select
//            TrieNode next = current.next[board[i][j] - 'a'];
//            visited[x][y] = true;
//            chars.push(next.c);
//            indecies.push(x * board[0].length + y);
//
//            /// Check result
//            if (next.isWord) {
//                availableWords.add(stacktoString(chars));
//                indexes.add(new ArrayList<>(indecies));
//            }
//
//            /// recursion
//            findAvailableWords(board, chars, indecies, availableWords, indexes, visited, current, x, y, dx, dy);
//
//            /// Unselect
//            visited[x][y] = false;
//            chars.pop();
//            indecies.pop();
//        }
    }

    private void selectWord(Stack<String> collects, String word, boolean[][] visited, List<Integer> index) {
        collects.push(word);
        for (int i : index) {
            visited[i / visited[0].length][i % visited[0].length] = true;
        }
    }

    private void unselectWord(Stack<String> collects, boolean[][] visited, List<Integer> index) {
        collects.pop();
        for (int i : index) {
            visited[i / visited[0].length][i % visited[0].length] = false;
        }
    }

    private String stacktoString(Stack<Character> stack) {
        List<Character> list = new ArrayList<>(stack);
        StringBuilder builder = new StringBuilder();
        for (Character c : list) {
            builder.append(c);
        }

        return builder.toString();
    }


    public static void main(String[] args) {
//        char[][] board = {{'d','o','a','f'}, {'a','g','a','i'}, {'d','c','a','n'}};
//        String[] words = {"dog", "dad", "dgdg", "can", "again", "fin"};

//        char[][] board = {{'a','b','c'}, {'d','e','f'}, {'g','h','i'}};
//        String[] words = {"abc", "cfi", "beh", "defi", "gh"};

//        char[][] board = {{'a','a','a','a'}, {'a','a','a','a'}, {'a','a','a','a'}};
//        String[] words = {"a"};

//        String[] strs = {"wuiiuww"};
//        String[] strs = {"ghihjui","wuiiuww"};
        String[] strs = {"abcdefg","huyuyww","ghihjui","wuiiuww"};
        String[] words = {"efg","defi","gh","iuw","ww","iw","ghih","dasf","aaa"};


        char[][] board = Utilities.stringToCharMatrix(strs);
        System.out.println(new BoggleGame().boggleGame(board, words));
    }
}
