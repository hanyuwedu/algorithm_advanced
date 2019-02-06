package data_structure.trie;

public class TrieNode {
    public boolean isWord;
    public TrieNode[] next;
    public Character c;

    public TrieNode(Character c) {
        this.c = c;
        this.isWord = false;
        this.next = new TrieNode[26];
    }
}
