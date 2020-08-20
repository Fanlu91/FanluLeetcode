package binarytree;
// Source : https://leetcode.com/problems/invert-binary-tree/
// Id     : 226
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/17
// Topic  : Binary Tree
// Level  : Easy-
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 48.73%

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode tmp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(tmp);
        return root;
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
