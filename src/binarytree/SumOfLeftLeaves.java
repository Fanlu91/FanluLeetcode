package binarytree;
// Source : https://leetcode.com/problems/sum-of-left-leaves/
// Id     : 404
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/18
// Topic  : Binary Tree
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 88.90%

public class SumOfLeftLeaves {

    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        helper(root, false);
        return sum;

    }

    private void helper(TreeNode root, boolean isLeft) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            if (isLeft)
                sum += root.val;
            return;
        }
        helper(root.left, true);
        helper(root.right, false);
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
