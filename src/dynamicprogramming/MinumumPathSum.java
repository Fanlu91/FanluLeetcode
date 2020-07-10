package dynamicprogramming;

// Source : https://leetcode.com/problems/minimum-path-sum/
// Id     : 64
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-11
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 88.89% 26.71%

public class MinumumPathSum {
    // 88.89% 3ms 26.71%
    public int minPathSum(int[][] grid) {
        if (grid.length == 1) {
            int ans = 0;
            for (int i : grid[0])
                ans += i;
            return ans;
        }

        if (grid[0].length == 1) {
            int ans = 0;
            for (int i = 0; i < grid.length; i++) {
                ans += grid[i][0];
            }
            return ans;
        }

        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];

    }
}
