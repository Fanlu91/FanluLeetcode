package tree.trie;
// Source : https://leetcode.com/problems/word-break-ii/
// Id     : 140
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/17
// Topic  : tree.trie
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 92.20%

import java.util.*;

public class WordBreakII {
    // 1 ms
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        Trie trie = new Trie(res);
        for (String word : wordDict)
            trie.insert(word);
        trie.findAll(s, 0, new StringBuilder());
        return res;
    }

    class TrieNode {
        public String data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(String data) {
            this.data = data;
        }
    }

    class Trie {
        private TrieNode root = new TrieNode("/");
        List<String> res;

        public Trie(List<String> res) {
            this.res = res;
        }

        public void insert(String word) {
            char[] text = word.toCharArray();
            TrieNode p = root;
            for (int i = 0; i < text.length; i++) {
                int index = text[i] - 'a';
                if (p.children[index] == null) {
                    TrieNode newNode = new TrieNode(word);
                    p.children[index] = newNode;
                }
                p = p.children[index];
            }
            p.isEndingChar = true;
            p.data = word;
        }

        public void findAll(String s, int i, StringBuilder stringBuilder) {
            if (i == s.length()) {
                res.add(stringBuilder.toString().trim());
                return;
            }

            TrieNode p = root;
            for (; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if (p.children[index] == null)
                    return;
                p = p.children[index];
                if (p.isEndingChar) {
                    String word = p.data + " ";
                    stringBuilder.append(word);
                    findAll(s, i + 1, stringBuilder);
                    stringBuilder.delete(stringBuilder.length() - word.length(), stringBuilder.length());
                }
            }
            return;
        }
    }

    // 0ms
    public List<String> wordBreak1(String s, List<String> wordDict) {
//    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        Set<String> set = new HashSet<>(wordDict);
        dfs(s, 0, new StringBuilder(), set, res);
        return res;
    }

    private void dfs(String s, int start, StringBuilder sb, Set<String> set, List<String> res) {
        if (start == s.length()) {
            res.add(sb.toString().trim());
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String word = s.substring(start, i + 1);
            if (set.contains(word)) {
                int l = sb.length();
                sb.append(word).append(" ");
                dfs(s, i + 1, sb, set, res);
                sb.setLength(l);
            }
        }

    }

    public static void main(String[] args) {
        WordBreakII wordBreak = new WordBreakII();
//        System.out.println(wordBreak.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak.wordBreak("aaaaaaa", Arrays.asList("aaa", "aaaa")));

    }
}