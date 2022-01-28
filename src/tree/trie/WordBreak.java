package tree.trie;
// Source : https://leetcode.com/problems/word-break/
// Id     : 139
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/17
// Topic  : tree.trie
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 99.62% 77.20%

import java.util.*;

public class WordBreak {

    // 1ms
    public boolean wordBreak1(String s, List<String> wordDict) {
//    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();

        for (String word : wordDict)
            trie.insert(word.toCharArray());

        return trie.find(s, 0);
    }

    class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    class Trie {
        private TrieNode root = new TrieNode('/');
        boolean[] failed = new boolean[301]; // s.length <= 300

        public void insert(char[] text) {
            TrieNode p = root;
            for (int i = 0; i < text.length; ++i) {
                int index = text[i] - 'a';
                if (p.children[index] == null) {
                    TrieNode newNode = new TrieNode(text[i]);
                    p.children[index] = newNode;
                }
                p = p.children[index];
            }
            p.isEndingChar = true;
        }

        public boolean find(String s, int i) {
            if (failed[i])
                return false;
            if (i >= s.length())
                return true;
            TrieNode p = root;
            for (; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if (p.children[index] == null) {
                    return false;
                }
                p = p.children[index];
                if (p.isEndingChar) {
                    if (find(s, i + 1))
                        return true;
                    failed[i + 1] = true;
                }
            }
            return false;
        }
    }

    // 8ms
    public boolean wordBreak2(String s, List<String> wordDict) {
//    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int r = 1; r < s.length() + 1; r++) {
            for (int l = 0; l < r; l++) {
                if (dp[l] == true && wordDict.contains(s.substring(l, r))) {
                    dp[r] = true;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak1("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak.wordBreak1("aaaaaaa", Arrays.asList("aaaa", "aaa")));
        System.out.println(wordBreak.wordBreak1("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
        System.out.println(wordBreak.wordBreak1("catsandogcat",
                Arrays.asList("cats", "dog", "sand", "and", "cat", "an")));
    }
}