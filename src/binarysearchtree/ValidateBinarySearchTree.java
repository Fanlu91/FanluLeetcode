package binarysearchtree;
// Source : https://leetcode.com/problems/validate-binary-search-tree/
// Id     : 98
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/25
// Topic  : Binary Search Tree
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 5.80%

import java.util.ArrayList;
import java.util.List;

public class ValidateBinarySearchTree {
    /*
    //! 只比较了每棵树及其左右子树三个节点，没有考虑子树中其他节点也必须遵守大小规则
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;
        if (root.left == null) {
            if (root.val < root.right.val)
                return isValidBST(root.right);
            return false;
        }

        if (root.right == null) {
            if (root.val < root.left.val)
                return isValidBST(root.left);
            return false;
        }

        if (root.val > root.left.val && root.val < root.right.val)
            return isValidBST(root.right) || isValidBST(root.left);
        return false;
    }*/

    /**
     * 利用 bst 中序遍历的结果是有序序列这一条件
     * <p>
     * 这样看来是充要条件。
     */
    List<Integer> res = new ArrayList<>();

    // 33.32% 2ms 14.49%
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        inOrder(root);
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i) <= res.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            res.add(root.val);
            inOrder(root.right);
        }
    }


    /**
     * 直接在中序遍历时，判断当前节点是否大于中序遍历的前一个节点
     * <p>
     * 下面的递归中 pre 的位置非常精妙
     * 比较root和左子树时，pre等于左子树的值
     * 比较右子树和root时，pre等于root的值
     *
     * @param root
     * @return
     */
    long pre = Long.MIN_VALUE;

    // 100.00% 0ms 5.80%
    public boolean isValidBST1(TreeNode root) {
//    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；
        // 否则继续遍历。
        if (root.val <= pre) { // root 小于等于left； right 小于等于root；
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
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