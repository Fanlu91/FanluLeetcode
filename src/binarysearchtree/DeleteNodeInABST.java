package binarysearchtree;
// Source : https://leetcode.com/problems/delete-node-in-a-bst/
// Id     : 450
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/25
// Topic  : Binary Search Tree
// Level  : Medium+
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 20.00%

public class DeleteNodeInABST {

    /**
     * 同时存在左右子树的时候，用successor 或 presuccesor都可以
     * 下面的解法默认用 successor
     * 没有右子树才会用 predecessor
     * 虽然没有利用左右子树中有一个为空可以直接使用另外一个替换的点
     * 不过实现看起来更简洁
     * <p>
     * 递归的时候，应当尽量注意把子问题包含进去，否则逻辑会变得复杂并很可能无法收敛
     * <p>
     * 比如这里，找到 successor之后，除了处理key节点，还需要把successor也删除
     *
     * @param root
     * @param key
     * @return
     */
    // 0ms
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (key > root.val)// delete from the right subtree
            root.right = deleteNode(root.right, key);
        else if (key < root.val)// delete from the left subtree
            root.left = deleteNode(root.left, key);
        else {// delete the current node
            if (root.left == null && root.right == null) // the node is a leaf
                root = null;
            else if (root.right != null) {// the node is not a leaf and has a right child
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {// the node is not a leaf, has no right child, and has a left child
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }


    // One step right and then always left
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null)
            root = root.left;
        return root.val;
    }

    // One step left and then always right
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
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