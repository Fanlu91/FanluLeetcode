package binarysearchtree;
// Source : https://leetcode.com/problems/search-in-a-binary-search-tree/
// Id     : 700
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/25
// Topic  : Binary Search Tree
// Level  : Easy-
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 9.09%

public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        if (val < root.val)
            return searchBST(root.left, val);
        if (val > root.val)
            return searchBST(root.right, val);
        return null;
    }
}