package dfs.tree_structure;

import data_structure.trie.Trie;
import data_structure.trie.TrieNode;

import java.util.ArrayList;
import java.util.List;

public class KEditDistance {
    /**
     * 2/8/2019
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

        List<String> list = new ArrayList<>();

        dfs(null, trie.root, "", list, target, k, 0);
        return list;
    }

    private void dfs(int[] last, TrieNode node, String current, List<String> list, String target, int k, int len) {
        int[] dp = new int[target.length() + 1];

        if (len == 0) {
            for (int i = 0; i <= dp.length - 1; i++) {
                dp[i] = i;
            }
            // current = "";
        } else {
            dp[0] = len;
            for (int i = 1; i <= dp.length - 1; i++) {
                if (node.c == target.charAt(i - 1)) {
                    dp[i] = Math.min(last[i - 1], Math.min(last[i] + 1, dp[i - 1] + 1));
                } else {
                    dp[i] = Math.min(last[i - 1] + 1, Math.min(last[i] + 1, dp[i - 1] + 1));
                }
            }
            // current += node.c;
        }

        if (dp[target.length()] <= k && node.isWord) {
            list.add(current);
        }

        for (int i = 0; i <= 25; i++) {
            if (node.next[i] == null) {
                continue;
            }

            // dfs(dp, node.next[i], current, list, target, k, len + 1);
            dfs(dp, node.next[i], current + node.next[i].c, list, target, k, len + 1);
        }
    }
}
