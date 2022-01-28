package tree.binarytree;
// Source : https://leetcode.com/problems/diameter-of-binary-tree/
// Id     : 543
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/19
// Topic  : tree.binarytree
// Level  : Easy+
// Other  :
// Tips   :
// Links  : 563
// Result : 100.00% 23.72%

public class DiameterOfBinaryTree {

    /**
     * 有的时候题目的解可能是一个恰当递归过程的中间产物，
     * 于是只盯着结果想递归要素是不行的，需要跳出来一步
     * <p>
     * 比如此题实际上是递归计算树的深度
     * 引申出求左右子树的深度
     * <p>
     * 过程中能够顺便求 diameter
     * <p>
     * 又比如 563 BinaryTreeTilt 中的例子
     */
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = depth(node.left);
        int right = depth(node.right);
        // key step is here
        ans = Math.max(left + right, ans);
        return Math.max(left, right) + 1;
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
