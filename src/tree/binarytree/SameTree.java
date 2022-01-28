package tree.binarytree;

// Source : https://leetcode.com/problems/same-tree/
// Id     : 100
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Tree
// Level  : Easy
// Other  : 6.5m
// Tips   :
// Result : 100.00% 0ms 5.33%

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            if (p != q)
                return false;
            else
                return true;

        if (p.val != q.val)
            return false;
        if (!isSameTree(p.left, q.left))
            return false;
        if (!isSameTree(p.right, q.right))
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

