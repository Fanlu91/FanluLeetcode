package breadthfirstsearch;
// Source : https://leetcode.com/problems/binary-tree-right-side-view/
// Id     : 199
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/17
// Topic  : Breadth First Search
// Level  : Medium-
// Other  :
// Tips   :
// Links  :
// Result : 95.83% 16.21%

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode node = queue.peek();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            ans.add(node.val);
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
