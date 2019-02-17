package dfs.continuous_enumeration;

import data_structure.trie.Trie;

import java.util.ArrayList;
import java.util.List;

public class WordSquares {
    /**
     * 2/8/2019
     * Continuous enumeration
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

        List<String> current = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        int n = words[0].length();
        dfs(current, list, trie, 0, n);

        return list;
    }

    private void dfs(List<String> current, List<List<String>> list, Trie trie, int i, int n) {
        if (i == n) {
            list.add(new ArrayList<>(current));
            return;
        }

        for (int j = i; j <= n - 1; j++) {
            String prefix = "";
            for (String word : current) {
                prefix += word.charAt(j);
            }

            if (trie.getWordsByPrefix(prefix).isEmpty()) {
                return;
            }
        }

        String prefix = "";
        for (String word : current) {
            prefix += word.charAt(i);
        }

        List<String> nexts = trie.getWordsByPrefix(prefix);
        for (String next : nexts) {
            current.add(next);
            dfs(current, list, trie, i + 1, n);
            current.remove(current.size() - 1);
        }
    }
}
