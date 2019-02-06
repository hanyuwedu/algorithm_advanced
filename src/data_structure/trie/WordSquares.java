package data_structure.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WordSquares {
    /**
     * DFS on trie
     * 2/2/2019
     *
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        Stack<String> stack = new Stack<>();
        List<List<String>> list = new ArrayList<>();
        int target = words[0].length();

        dfs(stack, list, trie, 0, target, "");

        return list;
    }

    private void dfs(Stack<String> stack, List<List<String>> list, Trie trie, int length, int target, String prefix) {
        if (length == target) {
            list.add(new ArrayList<>(stack));
            return;
        }

        for (String next : trie.getWordsByPrefix(prefix)) {
            stack.push(next);
            dfs(stack, list, trie, length + 1, target, generatePrefix(stack, length + 1));
            stack.pop();
        }
    }

    private String generatePrefix(Stack<String> stack, int next) {
        if (next >= stack.peek().length()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (String str : stack) {
            builder.append(str.charAt(next));
        }

        return builder.toString();
    }
}
