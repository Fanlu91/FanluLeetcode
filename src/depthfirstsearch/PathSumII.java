package depthfirstsearch;

// Source : https://leetcode.com/problems/path-sum-ii/
// Id     : 113
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Depth-First Search
// Level  : Medium
// Other  :
// Tips   :
// Result : 65.46% 5.26%


import java.util.LinkedList;
import java.util.List;

public class PathSumII {
    // 2ms
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        dfs(ans, list, root, sum);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, TreeNode root, int sum) {
        if (root == null)
            return;

        list.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            // the most important step is here.
            // you must be very clear about Value passing and reference passing
            // it can be confusing with recursion
            ans.add(new LinkedList<Integer>(list));
        }

        dfs(ans, list, root.left, sum - root.val);
        dfs(ans, list, root.right, sum - root.val);

        list.remove(list.size() - 1);
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


