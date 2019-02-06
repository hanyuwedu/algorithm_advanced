package data_structure.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ImplementTrie {
    TrieNode root;

    public ImplementTrie() {
        this.root = new TrieNode('#');
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        TrieNode current = this.root;
        for (Character c : word.toCharArray()) {
            if (current.next[c - 'a'] == null) {
                current.next[c - 'a'] = new TrieNode(c);
            }
            current = current.next[c - 'a'];
        }
        current.isWord = true;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode current = this.root;
        for (Character c : word.toCharArray()) {
            if (current.next[c - 'a'] == null) {
                return false;
            }
            current = current.next[c - 'a'];
        }
        return current.isWord;
    }

    public List<String> getWordsByPrefix(String prefix) {
        TrieNode current = this.root;
        Stack<Character> stack = new Stack<>();
        for (Character c : prefix.toCharArray()) {
            if (current.next[c - 'a'] == null) {
                return new ArrayList<>();
            }
            current = current.next[c - 'a'];
            stack.push(c);
        }

        List<String> result = new ArrayList<>();
        dfs(current, stack, result);
        return result;
    }

    private void dfs(TrieNode current, Stack<Character> stack, List<String> result) {
        if (current.isWord) {
            result.add(stackToString(stack));
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (current.next[c - 'a'] != null) {
                stack.push(c);
                dfs(current.next[c - 'a'], stack, result);
                stack.pop();
            }
        }
    }

    private String stackToString(Stack<Character> stack) {
        List<Character> list = new ArrayList<>(stack);
        StringBuilder builder = new StringBuilder();
        for (Character c : list) {
            builder.append(c);
        }

        return builder.toString();
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return !this.getWordsByPrefix(prefix).isEmpty();
    }

    public class TrieNode {
        boolean isWord;
        TrieNode[] next;
        Character c;

        public TrieNode(Character c) {
            this.c = c;
            this.isWord = false;
            this.next = new TrieNode[26];
        }
    }
}
