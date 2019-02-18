package data_structure.trie;

import util.Utilities;

import java.util.ArrayList;
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

        boolean[][] visited = new boolean[board.length][board[0].length];
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int[] max = {0};
        int[] currentWords = {0};


        collectWords(board, visited, trie, max, currentWords,0, dx, dy);
        return max[0];
    }

    private void collectWords(char[][] board, boolean[][] visited, Trie trie, int[] max, int[] currentWords, int pos, int[] dx, int[] dy) {
        if (pos == board.length * board[0].length) {
            max[0] = Math.max(max[0], currentWords[0]);
            return;
        }

        /// Option 1: skip this character
        collectWords(board, visited, trie, max, currentWords, pos + 1, dx, dy);

        /// Option 2: use this character
        int i = pos / board[0].length, j = pos % board[0].length;

        if (visited[i][j]) {
            return;
        }

        List<List<Integer>> availableIndecies = new ArrayList<>();
        Stack<Integer> indecies = new Stack<>();

        findAvailableWords(board, indecies, availableIndecies, visited, trie.root, i, j, dx, dy);

        System.out.println("pos: " + pos + ", indecies: " + availableIndecies);

        for (int n = 0; n <= availableIndecies.size() - 1; n++) {
            selectWord(availableIndecies.get(n), visited, currentWords);

            collectWords(board, visited, trie, max, currentWords, pos + 1, dx, dy);

            unselectWord(availableIndecies.get(n), visited, currentWords);
        }
    }



    private void findAvailableWords(char[][] board, Stack<Integer> indecies, List<List<Integer>> indexes, boolean[][] visited, TrieNode current, int i, int j, int[] dx, int[] dy) {
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
        indecies.push(i * board[0].length + j);
        if (current.isWord) {
            indexes.add(new ArrayList<>(indecies));
        }

        for (int d = 0; d <= 3; d++) {
            int x = i + dx[d], y = j + dy[d];

            findAvailableWords(board, indecies, indexes, visited, current, x, y, dx, dy);
        }

        visited[i][j] = false;
        indecies.pop();
    }

    private void selectWord(List<Integer> index, boolean[][] visited, int[] currentWords) {
        for (int i : index) {
            visited[i / visited[0].length][i % visited[0].length] = true;
        }
        currentWords[0]++;
    }

    private void unselectWord(List<Integer> index, boolean[][] visited, int[] currentWords) {
        for (int i : index) {
            visited[i / visited[0].length][i % visited[0].length] = false;
        }
        currentWords[0]--;
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
