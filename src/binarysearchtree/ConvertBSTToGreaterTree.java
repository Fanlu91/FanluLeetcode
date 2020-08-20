package binarysearchtree;
// Source : https://leetcode.com/problems/convert-bst-to-greater-tree/
// Id     : 538
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/18
// Topic  : Binary Search Tree
// Level  : Easy
// Other  :
// Tips   :
// Links  : 1038
// Result : 98.33% 62.29%

public class ConvertBSTToGreaterTree {

    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return root;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
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
