package gameday;

import data_structure.trie.Trie;
import data_structure.trie.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KEditDistance {
    /**
     * 2/1/2019
     * DFS on trie
     *
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    public List<String> kDistance(String[] words, String target, int k) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        int[] dp = new int[target.length() + 1];
        for (int i = 0; i <= target.length(); i++) {
            dp[i] = i;
        }

        List<String> list = new ArrayList<>();
        dfs(trie.root, dp, 0, k, target, "", list);

        return list;
    }

    private void dfs(TrieNode current, int[] last, int length, int k, String target, String source, List<String> list) {
        int[] dp = new int[last.length];

        if (length == 0) {
            dp = last;
        } else {
            dp[0] = length;
            for (int t = 1; t <= target.length(); t++) {
                if (target.charAt(t - 1) == current.c) {
                    dp[t] = Math.min(last[t - 1], Math.min(last[t] + 1, dp[t - 1] + 1));
                } else {
                    dp[t] = Math.min(last[t - 1] + 1, Math.min(last[t] + 1, dp[t - 1] + 1));
                }
            }
            source += current.c;
        }

        if (current.isWord && dp[target.length()] <= k) {
            list.add(source);
        }

        for (int i = 0; i <= 25; i++) {
            if (current.next[i] != null) {
                dfs(current.next[i], dp, length + 1, k, target, source, list);
            }
        }
    }
}
