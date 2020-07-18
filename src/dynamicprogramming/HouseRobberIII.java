package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/house-robber-iii/
// Id     : 337
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 100.00%
public class HouseRobberIII {

    Map<TreeNode, Integer> memo = new HashMap<>();

    //! 5.40% 1136 ms 33.33% without memo
    // 59.03% 3ms 33.33%
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        if (memo.containsKey(root))
            return memo.get(root);
        int ans = Math.max(rob(root, true), rob(root, false));
        memo.put(root, ans);
        return ans;
    }

    private int rob(TreeNode root, boolean rootIncluded) {
        if (root == null)
            return 0;
        if (rootIncluded)
            return root.val + rob(root.left, false) + rob(root.right, false);
        else
            return rob(root.left) + rob(root.right);
    }


    // 100.00% 0ms 33.33%
    public int rob1(TreeNode root) {
//    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        int[] res = solve(root);
        return Math.max(res[0], res[1]);
    }

    private int[] solve(TreeNode root) {
        int[] res = new int[2];
        if (root == null)
            return res;
        int[] left = solve(root.left);
        int[] right = solve(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
