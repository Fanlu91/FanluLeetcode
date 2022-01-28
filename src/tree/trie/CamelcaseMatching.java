package tree.trie;

// Source : https://leetcode.com/problems/camelcase-matching/
// Id     : 1023
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Trie
// Level  : Medium
// Date   : 2019-05-12
// Other  :
// Tips   :
// Result : 100.00% 99.58%

import java.util.LinkedList;
import java.util.List;

public class CamelcaseMatching {

    // 60.58% 1ms 99.26%
    public List<Boolean> camelMatchOrigin(String[] queries, String pattern) {
        List<Boolean> ansList = new LinkedList<>();
        // construct the pattern tree.trie first
        TrieNode patternRoot = new TrieNode();
        TrieNode tmp = patternRoot;
        for (int i = 0; i < pattern.length(); i++) {
            if (tmp.children[pattern.charAt(i) - 'A'] == null)
                tmp.children[pattern.charAt(i) - 'A'] = new TrieNode();
            tmp = tmp.children[pattern.charAt(i) - 'A'];
        }
        tmp.isEnd = true;
        // start to compare
        for (int i = 0; i < queries.length; i++) {
            ansList.add(searchPattern(queries[i], 0, patternRoot));
        }
        return ansList;
    }

    public boolean searchPattern(String string, int index, TrieNode parent) {
        if (index == string.length())
            return parent.isEnd;
        int tmp = string.charAt(index) - 'A';
        if (parent.children[tmp] != null)
            return searchPattern(string, ++index, parent.children[tmp]);
        else if (tmp < 26) { // char is uppercase
            return false;
        } else {
            return searchPattern(string, ++index, parent);
        }
    }

    class TrieNode {
        public TrieNode[] children = new TrieNode[58];
        public boolean isEnd = false;
    }

    // 100.00% 0ms 99.58%
    // for this problem there's a faster solution
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> results = new LinkedList<>();
        for (String query : queries) {
            results.add(match(query, pattern));
        }
        return results;
    }

    private boolean match(String query, String pattern) {
        if (pattern.length() > query.length())
            return false;

        int i = 0, j = 0;
        while (i < query.length() || j < pattern.length()) {
            if (i >= query.length())
                return false;
            char c = query.charAt(i);
            if (j < pattern.length() && c == pattern.charAt(j)) {
                j++;
            } else {
                if (c < 'a' || c > 'z')
                    return false;
            }
            i++;
        }
        return true;
    }
}
