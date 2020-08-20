package breadthfirstsearch;

// Source : https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
// Id     : 107
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Breadth First Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 99.79% 5.13%

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalI {
    // 99.79% 1ms 5.13%
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            ans.add(0, list);
        }
        return ans;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 1;//每一层节点个数
        while (!queue.isEmpty() && root != null) {
            List<Integer> list = new LinkedList<>();
            int n = 0;//下一层节点个数
            while (sum > 0) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                    n++;
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    n++;
                }
                sum--;
            }
            sum = n;
            res.addFirst(list);
        }

        return res;
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
