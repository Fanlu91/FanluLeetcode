package backtracking;
// Source : https://leetcode.com/problems/word-search-ii/
// Id     : 212
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Hard
// Date   : 2019-09-29
// Other  :
// Tips   :
// Result : 99.98% 77.78%

import java.util.LinkedList;
import java.util.List;

public class WordSearchII {

    List<String> ans;

    // 48.99% 24 ms 75.56%
    public List<String> findWords(char[][] board, String[] words) {
        ans = new LinkedList<>();
        TrieNode root = new TrieNode();
        for (String s : words) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.word = s;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                exist(board, i, j, root);
            }
        }
        return ans;
    }

    private void exist(char[][] board, int i, int j, TrieNode node) {
        if (i == -1 || j == -1 || i == board.length || j == board[0].length)
            return;

        for (int k = 0; k < 26; k++) {
            if (node.children[k] != null && ('a' + k) == board[i][j]) {
                if (node.children[k].word != null) {
                    ans.add(node.children[k].word);
                    node.children[k].word = null;
                }

                board[i][j] ^= 256;
                exist(board, i + 1, j, node.children[k]);
                exist(board, i - 1, j, node.children[k]);
                exist(board, i, j + 1, node.children[k]);
                exist(board, i, j - 1, node.children[k]);
                board[i][j] ^= 256;
            }
        }
    }


    /**
     * Improves a little by initialing children later, so we can avoid some meaningless for loop.
     * Use '#' instead of xor so we can skip quicker as well.
     *
     * @param board
     * @param words
     * @return
     */
    // 50.47%  21 ms 88.89%
    public List<String> findWordsImprove(char[][] board, String[] words) {
//    public List<String> findWords(char[][] board, String[] words) {
        ans = new LinkedList<>();
        TrieNode root = new TrieNode();
        for (String s : words) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children == null)
                    node.children = new TrieNode[26];
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.word = s;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                existImprove(board, i, j, root);
            }
        }
        return ans;
    }

    private void existImprove(char[][] board, int i, int j, TrieNode node) {
        if (node.children == null || board[i][j] == '#')
            return;

        for (int k = 0; k < 26; k++) {
            char c = board[i][j];
            if (node.children[k] != null && ('a' + k) == c) {
                if (node.children[k].word != null) {
                    ans.add(node.children[k].word);
                    node.children[k].word = null;
                }

                board[i][j] = '#';
                if (i < board.length - 1) existImprove(board, i + 1, j, node.children[k]);
                if (i > 0) existImprove(board, i - 1, j, node.children[k]);
                if (j < board[0].length - 1) existImprove(board, i, j + 1, node.children[k]);
                if (j > 0) existImprove(board, i, j - 1, node.children[k]);
                board[i][j] = c;
            }
        }
    }

    /**
     * while focusing on the small part to "improve performance",
     * Just realised I made a big mistake, very stupid one..
     * I use tree.trie, or the children array, like a linked list....
     * which is why it's so slow of course
     * <p>
     * never let this happen again.
     *
     * @param board
     * @param words
     * @return
     */
    // 99.98% 8 ms 77.78%
    public List<String> findWordsBetter(char[][] board, String[] words) {
//    public List<String> findWords(char[][] board, String[] words) {
        ans = new LinkedList<>();
        TrieNode root = new TrieNode();
        for (String s : words) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.word = s;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                existBetter(board, i, j, root);
            }
        }
        return ans;
    }

    private void existBetter(char[][] board, int i, int j, TrieNode node) {
        if (i == -1 || j == -1 || i == board.length || j == board[0].length || board[i][j] == '#')
            return;

        char c = board[i][j];
        /**
         * compare with original, below change saved 2ms
         */
        node = node.children[c - 'a'];
        if (node != null) {
            if (node.word != null) {
                ans.add(node.word);
                node.word = null;
            }
            board[i][j] = '#';
            existBetter(board, i + 1, j, node);
            existBetter(board, i - 1, j, node);
            existBetter(board, i, j + 1, node);
            existBetter(board, i, j - 1, node);
            board[i][j] = c;
        }
    }

    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String word;
    }

    /**
     * used by findWordsImprove method
     */

//    class TrieNode {
//        public TrieNode[] children;
//        public String word;
//    }
    public static void main(String[] args) {
        WordSearchII w = new WordSearchII();
        System.out.println(w.findWords(new char[][]{{'a', 'b'}, {'c', 'd'}}, new String[]{"ab", "cb", "ad", "bd", "ac", "ca", "da", "bc", "db", "adcb", "dabc", "abb", "acb"}));
    }
}
