package breadthfirstsearch;

// Source : https://leetcode.com/problems/minimum-depth-of-binary-tree/
// Id     : 111
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Breadth First Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.33%

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfBinaryTree {
    // 100.00% 0ms 5.33%
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }


    /**
     * bfs approach
     * @param root
     * @return
     */
    //
    public int minDepth1(TreeNode root) {
//    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
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

