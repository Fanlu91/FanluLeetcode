package tree.binarysearchtree;
// Source : https://leetcode.com/problems/binary-search-tree-iterator/
// Id     : 173
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/25
// Topic  : Binary Search Tree，Stack
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 72.82% 100%

import java.util.Stack;

public class BinarySearchTreeIterator {

    // 33.50% 26 ms 100.00%
    /*Queue<Integer> queue = new LinkedList<>();

    public BinarySearchTreeIterator(TreeNode root) {
//    public BSTIterator(TreeNode root) {
        inOrder(root);
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            queue.add(root.val);
            inOrder(root.right);
        }
    }

    *//**
     * @return the next smallest number
     *//*
    public int next() {
        return queue.poll();
    }

    *//**
     * @return whether we have a next smallest number
     *//*
    public boolean hasNext() {
        return !queue.isEmpty();
    }*/


    /**
     * 一种更充分利用了bst特性的解法
     * <p>
     * 使用栈做存储
     * 初始化仅压入左子树
     * next（pop）的过程中，若有，将节点的右子树的左子树按照同样的规则压入。
     *
     * 其实效率可能并不会提升
     * 只是对知识的运用体现的更好一些。
     */
    // 72.82% 24 ms 100%
    private Stack<TreeNode> cache;

    public BinarySearchTreeIterator(TreeNode root) {
//    public BSTIterator(TreeNode root) {
        cache = new Stack<TreeNode>();
        while (root != null) {
            cache.push(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = cache.pop();
        TreeNode subNode = node.right;
        while (subNode != null) {
            cache.push(subNode);
            subNode = subNode.left;
        }
        return node.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !cache.empty();
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