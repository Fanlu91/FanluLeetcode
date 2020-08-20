package binarysearchtree;
// Source : https://leetcode.com/problems/unique-binary-search-trees/
// Id     : 96
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/14
// Topic  : Binary Search Tree;Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 99.41%

public class UniqueBinarySearchTrees {
    /**
     * 并没有实际用到太多bst的特性，
     * 仅利用了bst有序的特点来构造这些树
     * 与具体的节点值无关，与节点总数有关。
     * <p>
     * 在充分理解题意后会发现实际上是一道并不复杂的dp问题
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n == 0)
            return 1;
        if (n < 3)
            return n;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += dp[j] * dp[i - 1 - j];
            }
            dp[i] = sum;
        }
        return dp[n];
    }

}