package tree.binarytree;

// Source : https://leetcode.com/problems/symmetric-tree/
// Id     : 101
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Tree
// Level  : Easy
// Other  : n15
// Tips   :
// Result : 100.00% 26.66%

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;

        if (left.val != right.val)
            return false;
        if (!isMirror(left.right, right.left))
            return false;
        if (!isMirror(left.left, right.right))
            return false;
        return true;
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

