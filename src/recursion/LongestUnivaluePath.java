package recursion;
// Source : https://leetcode.com/problems/longest-univalue-path/
// Id     : 687
// Author : Fanlu Hai
// Topic  : Recursion
// Date   : 2018-04-18
//!Result :


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class LongestUnivaluePath {
    int maxLength = 0;

    public int longestUnivaluePath(TreeNode root) {

        preOrderTraverse(root);
        return maxLength;
    }

    // get path count below each node
    public int treePath(TreeNode root) {
        int pathCountLeft = 0;
        int pathCountRight = 0;

        if (null == root)
            return 0;
        if (null != root.left && root.val == root.left.val) {
            pathCountLeft += 1 + treePath(root.left);
        }
        if (null != root.right && root.val == root.right.val) {
            pathCountRight += 1 + treePath(root.right);
        }
        return pathCountLeft > pathCountRight ? pathCountLeft : pathCountRight;
    }

    public void preOrderTraverse(TreeNode root) {

        if (null == root)
            return;
        int tmp = treePath(root);
//        System.out.println("node value: " + root.val + " | path count value: " + tmp);
        if (tmp > maxLength) {
            maxLength = tmp;
        }
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }


    public static void main(String[] args) {
        LongestUnivaluePath longestUnivaluePath = new LongestUnivaluePath();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(5);
        TreeNode treeNode7 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(4);
        TreeNode treeNode9 = new TreeNode(4);
        TreeNode treeNode10 = new TreeNode(5);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.right = treeNode6;
        treeNode4.left = treeNode7;
        treeNode4.right = treeNode8;
        treeNode5.left = treeNode9;
        treeNode6.left = treeNode10;

        System.out.println(longestUnivaluePath.longestUnivaluePath(treeNode1));

    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {

    }

    TreeNode(int x) {
        val = x;
    }
}