package breadthfirstsearch;

// Source : https://leetcode.com/problems/binary-tree-level-order-traversal/
// Id     : 102
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Breadth First Search
// Level  : Medium
// Other  :
// Tips   :
// Result : 99.79% 5.13%

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    List<List<Integer>> ans;

    public List<List<Integer>> levelOrder(TreeNode root) {
        ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        helper(root, 0);
        return ans;
    }

    private void helper(TreeNode node, int level) {
        if (ans.size() == level)
            ans.add(new ArrayList<>());

        ans.get(level).add(node.val);
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
//    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // start the current level
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }
}
