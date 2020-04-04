package breadthfirstsearch;

// Source : https://leetcode.com/problems/average-of-levels-in-binary-tree/
// Id     : 637
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-03-19
// Topic  : Breadth First Search
// Other  :
// Tips   :
// Result : 99.18% 24.76%

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {

    // 99.18% 1 ms 24.76%
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new LinkedList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            ans.add(sum / size);
        }
        return ans;
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