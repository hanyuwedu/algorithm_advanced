package data_structure.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Trie {
    public TrieNode root;

    public Trie() {
        this.root = new TrieNode('#');
    }

    public void add(String word) {
        TrieNode current = this.root;
        for (Character c : word.toCharArray()) {
            if (current.next[c - 'a'] == null) {
                current.next[c - 'a'] = new TrieNode(c);
            }
            current = current.next[c - 'a'];
        }
        current.isWord = true;
    }

    public boolean containsWord(String word) {
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

    public List<String> fuzzySearchWord(String word) {
        List<String> list = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        dfs(word, 0, list, stack, this.root);
        return list;
    }

    private void dfs(String word, int i, List<String> list, Stack<Character> stack, TrieNode current) {
        if (i == word.length()) {
            if (current.isWord) {
                list.add(stackToString(stack));
            }
            return;
        }

        if (word.charAt(i) == '.') {
            for (char c = 'a'; c <= 'z'; c++) {
                if (current.next[c - 'a'] != null) {
                    stack.push(c);
                    dfs(word, i + 1, list, stack, current.next[c - 'a']);
                    stack.pop();
                }
            }
        } else {
            if (current.next[word.charAt(i) - 'a'] == null) {
                return;
            }
            stack.push(word.charAt(i));
            dfs(word, i + 1, list, stack, current.next[word.charAt(i) - 'a']);
            stack.pop();
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


    public TrieNode getTrieNode(String word) {
        TrieNode current = this.root;

        for (Character c : word.toCharArray()) {
            current = current.next[c - 'a'];
            if (current == null) {
                return null;
            }
        }

        return current;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("add");
        trie.add("addon");
        trie.add("adn");

        trie.add("broadcast");

        System.out.println(trie.containsWord("add"));
        System.out.println(trie.getWordsByPrefix("ad"));
        System.out.println(trie.getWordsByPrefix("broad"));

        System.out.println(trie.fuzzySearchWord(".dd"));
        System.out.println(trie.fuzzySearchWord("addon"));
        System.out.println(trie.fuzzySearchWord("ad."));
        System.out.println(trie.fuzzySearchWord("..."));
        System.out.println(trie.fuzzySearchWord(".."));
        System.out.println(trie.getWordsByPrefix(""));
    }
}
