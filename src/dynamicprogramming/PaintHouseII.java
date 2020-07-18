package dynamicprogramming;
// Source : https://leetcode.com/problems/paint-house-ii/
// Id     : 265
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/15
// Topic  : Dynamic Programming
// Level  : Hard-
// Other  :
// Tips   : Almost share the same difficulty level with 256(EASY)
// Links  : 256
// Result : 99.53% 100.00%

public class PaintHouseII {

    // 25.00% 5ms 100.00%
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs[0].length;
        int[][] dp = new int[costs.length + 1][n];

        for (int i = 1; i < costs.length + 1; i++) {
            for (int j = 0; j < n; j++) {
                int min = dp[i - 1][(j + 1) % n];
                for (int k = 2; k < n; k++) {
                    min = Math.min(min, dp[i - 1][(j + k) % n]);
                }
                dp[i][j] = costs[i - 1][j] + min;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp[costs.length])
            ans = Math.min(ans, i);
        return ans;
    }

    /**
     * improve performance by replacing the array with two variables.
     * <p>
     * only the smallest two needs to be stored.
     *
     * @param costs
     * @return
     */
    // 99.53% 2ms 100.00%
    public int minCostII1(int[][] costs) {
//    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length, k = costs[0].length;
        int min = 0, secondMin = 0, minIndex = -1;
        for (int i = 0; i < n; i++) {
            int tempMin = Integer.MAX_VALUE, tempSecondMin = Integer.MAX_VALUE, tempMinIndex = -1;
            for (int j = 0; j < k; j++) {
                int cur = (minIndex == j ? secondMin : min) + costs[i][j];
                if (cur < tempMin) {
                    tempSecondMin = tempMin;
                    tempMin = cur;
                    tempMinIndex = j;
                } else if (cur < tempSecondMin) {
                    tempSecondMin = cur;
                }
            }
            min = tempMin;
            secondMin = tempSecondMin;
            minIndex = tempMinIndex;
        }
        return min;
    }


}