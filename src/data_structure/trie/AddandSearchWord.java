package data_structure.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AddandSearchWord {
    TrieNode root;

    public AddandSearchWord() {
        this.root = new TrieNode('#');
    }


    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    public void addWord(String word) {
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
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        return !this.fuzzySearchWord(word).isEmpty();
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
