package breadthfirstsearch;
// Source : https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
// Id     : 103
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/17
// Topic  : Breadth First Search
// Level  : Medium-
// Other  :
// Tips   :
// Links  :
// Result : 98.10% 34.57%

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int turn = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // 可以在填充queue的时候采取一轮 add 一轮 push的方式直接构建出符合题意的list
            // 不过实际效率提升可能有限，感觉不如如下方式简洁
            if (turn == 1)
                Collections.reverse(list);

            turn *= -1;
            ans.add(list);
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
