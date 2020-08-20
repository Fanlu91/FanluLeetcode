package binarytree;

// Source : https://leetcode.com/problems/maximum-depth-of-binary-tree/
// Id     : 104
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Tree
// Level  : Easy
// Other  : 1m
// Tips   :
// Result : 100.00% 25.42%

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
