package binarytree;
// Source : https://leetcode.com/problems/balanced-binary-tree/
// Id     : 110
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-01
// Topic  : Binary Tree
// Level  : Easy
// Other  :
// Tips   :
// Result : 99.69% 1ms 23.53%

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(height(root.left) - height(root.right)) < 2
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        // An empty tree has height -1
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
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
