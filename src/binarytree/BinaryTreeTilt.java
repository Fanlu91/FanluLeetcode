package binarytree;
// Source : https://leetcode.com/problems/binary-tree-tilt/
// Id     : 563
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/19
// Topic  : binarytree 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 31.25%

public class BinaryTreeTilt {

    int sum = 0;

    // 23.49% 14 ms 5.27%
    public int findTilt(TreeNode root) {
        if (root == null)
            return 0;
        sum += Math.abs(getSum(root.left) - getSum(root.right));
        findTilt(root.left);
        findTilt(root.right);
        return sum;
    }

    public int getSum(TreeNode root) {
        if (root == null)
            return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    /**
     * 有的时候题目的解可能是一个恰当递归过程的中间产物，
     * 于是只盯着结果想递归要素是不行的，需要跳出来一步
     *
     * 比如此题实际上是递归计算数的节点之和
     * 引申出求左右子节点之和
     *
     * 过程中能够顺便求tilt
     *
     * 又比如 543 DiameterOfBinaryTree 中的例子
     *
     */
    int tilt = 0;   // 结果
    public int findTilt1(TreeNode root) {
//    public int findTilt(TreeNode root) {
        helper(root);
        return tilt;
    }

    public int helper(TreeNode root) {
        if (root == null) { // 递归出口，空树的节点之和为0
            return 0;
        }
        int leftTreeSum = helper(root.left);    // 计算当前节点的左子树节点之和
        int rightTreeSum = helper(root.right);  // 计算当前节点的右子树节点之和
        tilt += Math.abs(leftTreeSum - rightTreeSum);   // 计算当前节点的坡度并加入结果
        return root.val + leftTreeSum + rightTreeSum;   // 返回以当前节点为根的整棵树的节点之和
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
