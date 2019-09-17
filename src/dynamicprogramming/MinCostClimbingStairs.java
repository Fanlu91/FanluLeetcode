package dynamicprogramming;

// Source : https://leetcode.com/problems/min-cost-climbing-stairs/
// Id     : 746
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Result : 99.86% 100.00%

public class MinCostClimbingStairs {

    // 99.86% 1ms 7.14%
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0)
            return 0;
        if (cost.length == 1)
            return cost[0];

        int dp[] = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = cost[0];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i - 1];
        }

//        return dp[cost.length];
        return Math.min(dp[cost.length - 1], dp[cost.length]);
    }

    // 99.86% 100.00%
    public int minCostClimbingStairsImprove(int[] cost) {
//    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0)
            return 0;
        if (cost.length == 1)
            return cost[0];

        int dp0 = 0;
        int dp1 = cost[0];

        for (int i = 1; i < cost.length; i++) {
            int dp2 = Math.min(dp1, dp0) + cost[i];
            dp0 = dp1;
            dp1 = dp2;
        }
        return Math.min(dp1, dp0);
    }
}
