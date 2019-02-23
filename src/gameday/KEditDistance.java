package gameday;


import java.util.ArrayList;
import java.util.List;

public class KEditDistance {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/k-edit-distance/description
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
        dfs(target, "", null, k, list, trie.root);
        return list;
    }

    private void dfs(String target, String current, int[] last, int k, List<String> list, TrieNode node) {
        int[] dp = new int[target.length() + 1];

        if (node.c == '#') {
            for (int i = 1; i <= target.length(); i++) {
                dp[i] = i;
            }
        } else {
            dp[0] = current.length() + 1;
            for (int i = 1; i <= target.length(); i++) {
                if (node.c == target.charAt(i - 1)) {
                    dp[i] = Math.min(last[i - 1], Math.min(last[i] + 1, dp[i - 1] + 1));
                } else {
                    dp[i] = Math.min(last[i - 1] + 1, Math.min(last[i] + 1, dp[i - 1] + 1));
                }
            }
        }

        if (node.c != '#') {
            current += node.c;
        }

        if (dp[target.length()] <= k && node.isWord) {
            list.add(current);
        }

        for (int i = 0; i <= 25; i++) {
            if (node.next[i] != null) {
                dfs(target, current, dp, k, list, node.next[i]);
            }
        }
    }

    public class TrieNode{
        char c;
        TrieNode[] next;
        boolean isWord;

        public TrieNode(char c) {
            this.c = c;
            this.next = new TrieNode[26];
            this.isWord = false;
        }
    }

    public class Trie {
        public TrieNode root;

        public Trie() {
            this.root = new TrieNode('#');
        }

        public void add(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.next[c - 'a'] == null) {
                    current.next[c - 'a'] = new TrieNode(c);
                }
                current = current.next[c - 'a'];
            }
            current.isWord = true;
        }
    }

    public static void main(String[] args) {
        String[] input = {"as","ab","cf","da","ee","e","adee","eeda"};
        String target = "eefab";

        System.out.println(new KEditDistance().kDistance(input, target, 1));
    }
}
