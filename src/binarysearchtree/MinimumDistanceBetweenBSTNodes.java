package com.fanlu.leetcode.binarysearchtree;
// Source : https://leetcode.com/problems/minimum-distance-between-bst-nodes/
//          or https://leetcode.com/problems/minimum-absolute-difference-in-bst/
// Id     : 783 or 530
// Author : Fanlu Hai
// Date   : 2018-05-02
// Other  : 783 and 530 are almost the same , below solution will ac both.
// Tips   : This is a BST, which means the inorder traversal of its nodes results in a sorted list of values.
//          good test case [7,3,9,1,6,8,11] , [11,3,13,1,7,null,null,null,null,5,10]
// 如果是bst，那么深度优先中序遍历就能解决。不是bst的话， 就构造一个方便查询pre的顺序结构。

public class MinimumDistanceBetweenBSTNodes {
    int min = Integer.MAX_VALUE;
    Integer pre = null;

    // 100.00% 52.53%
    public int minDiffInBST(TreeNode root) {

        if (root.left != null)
            minDiffInBST(root.left);
        if (pre != null)
            min = Math.min(min, root.val - pre);
//        System.out.println("dfs recursion " + root.val);
        pre = root.val;
        if (root.right != null)
            minDiffInBST(root.right);
        return min;
    }


    //! only considered connected nodes. but its any given nodes in the description
    // And I didn't even think about the bst thing,
    // treated it as an ordaniry btree which is highly likely to be wrong, at least inefficient.
    public int minDiffInBSTWrongAnswer(TreeNode root) {
        dfsWrongAnswer(root);
        return min;
    }

    public void dfsWrongAnswer(TreeNode root) {
        if (root == null) {
            return;
        }

        //left null
        if (root.left == null) {
            // right null
            if (root.right == null) {
                return;
            }
            // right has
            if (min - Math.abs(root.val - root.right.val) > 0)
                min = Math.abs(root.val - root.right.val);
            dfsWrongAnswer(root.right);
        } else {
            //left has
            if (min - Math.abs(root.val - root.left.val) > 0) {
                min = Math.abs(root.val - root.left.val);
            }
            dfsWrongAnswer(root.left);
            // right null
            if (root.right == null) {
                return;
            }
            //right has
            if (min - Math.abs(root.val - root.right.val) > 0)
                min = Math.abs(root.val - root.right.val);
            dfsWrongAnswer(root.right);
        }
        dfsWrongAnswer(root.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(11);
        TreeNode treeNode4 = new TreeNode(1);
        //       TreeNode treeNode5 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(6);
        TreeNode treeNode6 = new TreeNode(8);
        TreeNode treeNode7 = new TreeNode(13);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        MinimumDistanceBetweenBSTNodes m = new MinimumDistanceBetweenBSTNodes();
        m.minDiffInBST(treeNode1);
    }
}
