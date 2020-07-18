package depthfirstsearch;

// Source : https://leetcode.com/problems/path-sum-ii/
// Id     : 113
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Depth-First Search
// Level  : Hard
// Other  :
// Tips   :
// Result : 99.67% 1ms 7.69%

public class BinaryTreeMaximumPathSum {
    int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        oneSideMax(root);
        return ans;
    }

    /**
     * dfs returns the max value a root and one of its two child can be
     *
     * the max path however can be a root with both children
     * so we calculate ans separately.
     *
     * @param root
     * @return
     */
    private int oneSideMax(TreeNode root) {
        if (root == null)
            return 0;

        // you can give up child if they are less than 0
        int maxLeft = Math.max(oneSideMax(root.left), 0);
        int maxRight = Math.max(oneSideMax(root.right), 0);
        ans = Math.max(ans, maxLeft + maxRight + root.val);

        return root.val + Math.max(maxLeft, maxRight);
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
