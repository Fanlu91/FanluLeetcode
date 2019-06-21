package trie;
// Source : https://leetcode.com/problems/longest-word-in-dictionary/
// Id     : 720
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Trie
// Level  : Easy
// Date   : 2018-05-12
// Other  :
// Tips   : data structure of the children list will affect the performance significantly; lexicographical order 辞典序;
// Cases  : ["gbra","jy","pl","zn","gb","j","jyh","jyhm","plr","znicn","p","gbr","zni","znic","aq"] ;
// Result : 100.00% 97.61%

import java.util.HashMap;
import java.util.Stack;

public class LongestWordInDictionary {
    String longest = "";

    TrieNodeUsingMap rootNodeUsingMap = new TrieNodeUsingMap('/');

    // 34.81%(22 ms) 97.45%
    public String longestWordSlow(String[] words) {
        for (int i = 0; i < words.length; i++)
            insertUsingMap(words[i], i);

        Stack<TrieNodeUsingMap> stack = new Stack();
        stack.addAll(rootNodeUsingMap.children.values());

        TrieNodeUsingMap node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.index != -1) {
                String tmp = words[node.index];
                if (tmp.length() > longest.length()
                        || (tmp.length() == longest.length() && tmp.compareTo(longest) < 0)) {
                    longest = tmp;
                }
                stack.addAll(node.children.values());
            }
        }
        return longest;
    }

    //51.26% (16 ms) 98.36%
    public String longestWordBetter(String[] words) {
        for (int i = 0; i < words.length; i++)
            insertUsingMap(words[i], i);
        for (TrieNodeUsingMap child : rootNodeUsingMap.children.values())
            searchLongest(child, words);
        return longest;
    }

    public void searchLongest(TrieNodeUsingMap node, String[] words) {
        if (node.index != -1) {
            String tmp = words[node.index];
            if (tmp.length() > longest.length()
                    || (tmp.length() == longest.length() && tmp.compareTo(longest) < 0))
                longest = tmp;

            for (TrieNodeUsingMap child : node.children.values())
                searchLongest(child, words);

        }
    }

    class TrieNodeUsingMap {
        public char c;
        // to keep track which word's insertion created this node
        public int index = -1;
        HashMap<Character, TrieNodeUsingMap> children = new HashMap();

        public TrieNodeUsingMap(char c) {
            this.c = c;
        }

        public TrieNodeUsingMap(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    public void insertUsingMap(String string, int index) {
        TrieNodeUsingMap node = rootNodeUsingMap;
        for (char c : string.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNodeUsingMap(c));
            node = node.children.get(c);
        }
        // not only means this node is the end of a word,but also contains index information for the word.
        node.index = index;
    }

    /**
     * use arrays to construct a trie
     */

    TrieNodeUsingArray rootNodeUsingArray = new TrieNodeUsingArray('/');

    //100.00% (6 ms) 97.61% (37.8 MB)
    public String longestWord(String[] words) {
        for (int i = 0; i < words.length; i++)
            insertUsingArray(words[i], i);
        for (TrieNodeUsingArray child : rootNodeUsingArray.children) {
            if (null != child)
                searchLongest2(child, words);
        }
        return longest;
    }

    public void searchLongest2(TrieNodeUsingArray node, String[] words) {
        if (node.index != -1) {
            String tmp = words[node.index];
            if (tmp.length() > longest.length()
                    || (tmp.length() == longest.length() && tmp.compareTo(longest) < 0)) {
                longest = tmp;
            }
            for (TrieNodeUsingArray child : node.children) {
                if (null != child)
                    searchLongest2(child, words);
            }
        }
    }

    class TrieNodeUsingArray {
        public char c;
        // to keep track which word's insertion created this node
        public int index = -1;
        TrieNodeUsingArray[] children = new TrieNodeUsingArray[26];

        public TrieNodeUsingArray(char c) {
            this.c = c;
        }

        public TrieNodeUsingArray(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    public void insertUsingArray(String string, int index) {
        TrieNodeUsingArray node = rootNodeUsingArray;
        for (char c : string.toCharArray()) {
            int n = c - 'a';
            if (node.children[n] == null) {
                node.children[n] = new TrieNodeUsingArray(c);
            }
            node = node.children[n];
        }
        node.index = index;
    }
}
