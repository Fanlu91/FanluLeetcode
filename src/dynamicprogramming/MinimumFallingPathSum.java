package dynamicprogramming;

// Source : https://leetcode.com/problems/minimum-falling-path-sum/
// Id     : 931
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-20
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 89.09% 86.67%

import java.util.Arrays;

public class MinimumFallingPathSum {

    // 89.09% 3ms 66.67%
    public int minFallingPathSum(int[][] A) {
        int[][] dp = new int[A.length][A.length + 2]; // two extra columns

        for (int i = 0; i < A.length; i++) {
            // notice the index difference
            dp[A.length - 1][i + 1] = A[A.length - 1][i];//last row
            dp[i][0] = Integer.MAX_VALUE;//first column
            dp[i][A.length + 1] = Integer.MAX_VALUE;//last column
        }

        for (int i = A.length - 2; i >= 0; i--) {
            for (int j = 1; j < A.length + 1; j++) {
                dp[i][j] = A[i][j - 1] + Math.min(Math.min(dp[i + 1][j - 1], dp[i + 1][j]), dp[i + 1][j + 1]);
            }
        }
/**
 * java stream is not time efficient here, 36 ms
 */
//        return Arrays.stream(dp[0]).min().getAsInt();
        int res = dp[0][1];
        for (int i = 2; i < A.length + 1; i++) {
            if (res > dp[0][i])
                res = dp[0][i];
        }
        return res;
    }

    // 89.09% 3ms 86.67%
    public int minFallingPathSumImprove(int[][] A) {
//        public int minFallingPathSum(int[][] A) {
        if (A.length == 1)
            return A[0][0];
        int[][] dp = new int[A.length][A.length + 2]; // two extra columns

        for (int i = 0; i < A.length; i++) {
            // notice the index difference
            dp[A.length - 1][i + 1] = A[A.length - 1][i];//last row
            dp[i][0] = Integer.MAX_VALUE;//first column
            dp[i][A.length + 1] = Integer.MAX_VALUE;//last column

        }

        for (int i = A.length - 2; i > 0; i--) {
            for (int j = 1; j < A.length + 1; j++) {
                dp[i][j] = A[i][j - 1] + Math.min(Math.min(dp[i + 1][j - 1], dp[i + 1][j]), dp[i + 1][j + 1]);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            res = Math.min(res, A[0][i] + Math.min(Math.min(dp[1][i], dp[1][i + 1]), dp[1][i + 2]));
        }
        return res;
    }

    public static void main(String[] args) {
        MinimumFallingPathSum m = new MinimumFallingPathSum();
        System.out.println(m.minFallingPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
