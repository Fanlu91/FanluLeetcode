package dynamicprogramming.minimax;

// Source : https://leetcode.com/problems/guess-number-higher-or-lower-ii/
// Id     : 375
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Minimax
// Level  : Medium
// Other  : n
// Tips   :
// Result : 99.47% 5.41%

public class GuessNumberHigherOrLowerII {
    // 15.65% 14 ms 5.41%
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return getMoneyAmount(1, n, dp);
    }

    private int getMoneyAmount(int low, int high, int[][] dp) {
        if (low >= high) {
            return 0;
        }
        if (dp[low][high] != 0) {
            return dp[low][high];
        }

        int res = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            res = Math.min(res, Math.max(getMoneyAmount(low, i - 1, dp), getMoneyAmount(i + 1, high, dp)) + i);
        }
        dp[low][high] = res;

        return res;
    }


    // 99.47% 2ms 5.41%
    public int getMoneyAmount2(int n) {
//        public int getMoneyAmount ( int n){
        if (n == 1) return 0;
        int[][] mem = new int[n + 1][n + 1];
        return calculateMoney(1, n, mem);

    }

    private int calculateMoney(int low, int high, int[][] mem) {
        if (low >= high)
            return 0;
        if (mem[low][high] != 0)
            return mem[low][high];
        int minRes = Integer.MAX_VALUE;
        for (int i = (low + high) / 2; i <= high; i++) {
            int res = i + Math.max(calculateMoney(i + 1, high, mem), calculateMoney(low, i - 1, mem));
            minRes = Math.min(res, minRes);
        }
        mem[low][high] = minRes;
        return minRes;
    }
}
