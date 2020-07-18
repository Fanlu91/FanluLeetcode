package dynamicprogramming;
// Source : https://leetcode.com/problems/paint-house/
// Id     : 256
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/15
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 98.79% 100.00%

public class PaintHouse {
    // 98.79% 1ms 100.00%
    public int minCost(int[][] costs) {
        int[][] dp = new int[3][costs.length + 1];
        // 0 red 1 green 2 blue
        for (int i = 1; i < costs.length + 1; i++) {
            for (int j = 0; j < 3; j++) {
//                dp[i][j] = costs[i - 1][j] + Math.min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3]);
                dp[j][i] = costs[i - 1][j]+ Math.min(dp[(j+1)%3][i-1],dp[(j+2)%3][i-1]);
            }
        }
        return Math.min(Math.min(dp[0][costs.length], dp[1][costs.length]), dp[2][costs.length]);
    }
}