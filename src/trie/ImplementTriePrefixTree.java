package trie;

// Source : https://leetcode.com/problems/implement-trie-prefix-tree/
// Id     : 208
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-21
// Topic  : Trie
// Level  : Medium-
// Other  : Trie
// Tips   :
// Result : 99.79% 98.24%

public class ImplementTriePrefixTree {
    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    // in leetcode solution the class name is Trie
    // public Trie() {
    public ImplementTriePrefixTree() {
        this.root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 'a'] == null)
                node.children[word.charAt(i) - 'a'] = new TrieNode();
            node = node.children[word.charAt(i) - 'a'];
        }
        node.isEndOfAWord = true;
    }

    // 57.70% 78 ms 97.61%
    /**
     * Returns if the word is in the trie.
     */
    /*
    public boolean search(String word) {
        return doSearch(word, 0, root);
    }

    public boolean doSearch(String word, int index, TrieNode parent) {
        if (parent == null)
            return false;
        if (index == word.length())
            return parent.isEndOfAWord;
        int i = word.charAt(index) - 'a';
        if (parent.children[i] != null) {
            return doSearch(word, ++index, parent.children[i]);
        }
        return false;
    }

    *//*

     *//**
     * Returns if there is any word in the trie that starts with the given prefix.
     *//**//*
    public boolean startsWith(String prefix) {
        return doStartWith(prefix, 0, root);
    }

    public boolean doStartWith(String prefix, int index, TrieNode parent) {
        if (parent == null)
            return false;
        if (index == prefix.length())
            return true;
        int i = prefix.charAt(index) - 'a';
        if (parent.children[i] != null) {
            return doStartWith(prefix, ++index, parent.children[i]);
        }
        return false;
    }*/

    // 99.79% 71ms 98.24%
    // performance comparison is not very accurate as you can image.

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode curr = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (curr.children[chars[i] - 'a'] == null)
                return false;
            curr = curr.children[chars[i] - 'a'];
        }
        return curr.isEndOfAWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (curr.children[chars[i] - 'a'] == null)
                return false;
            curr = curr.children[chars[i] - 'a'];
        }
        return true;
    }

    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndOfAWord = false;
    }

    public static void main(String[] args) {
        ImplementTriePrefixTree i = new ImplementTriePrefixTree();
        i.insert("apple");
        System.out.println(i.search("apple"));
        System.out.println(i.startsWith("apple"));

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
