package depthfirstsearch;

// Source : https://leetcode.com/problems/path-sum/
// Id     : 112
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Depth-First Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 6.52%

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PathSum {
    // 100.00% 0ms 6.52%
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        if (hasPathSum(root.left, sum - root.val))
            return true;
        if (hasPathSum(root.right, sum - root.val))
            return true;
        return false;
    }

    public boolean hasPathSumIteration(TreeNode root, int sum) {
//    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // "a root-to-leaf path "
            if (node.val == sum && node.left == null && node.right == null)
                return true;
            if (node.left != null) {
                node.left.val += node.val;
                queue.add(node.left);
            }
            if (node.right != null) {
                node.right.val += node.val;
                queue.add(node.right);
            }
        }
        return false;
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


