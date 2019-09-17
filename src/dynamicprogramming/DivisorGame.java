package dynamicprogramming;

// Source : https://leetcode.com/problems/divisor-game/
// Id     : 1025
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-16
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 13.33%

import java.util.Arrays;

public class DivisorGame {

    // 20.17% 4ms 13.33%
    public boolean divisorGameDp(int N) {
        boolean[] res = new boolean[N + 1];//dp, booleans default to false
        res[1] = false;//base case
        for (int i = 2; i <= N; i++) {
            for (int j = i - 1; j >= 1; j--) {//try all x < N to find divisor of N
                if (i % j == 0 && res[i - j] == false) {
                    //at the first position where N-x is a losing position,
                    // N is a winning position, so stop looking for winning position of N
                    res[i] = true;
                    break;
                }
            }
        }
        return res[N];
    }


    public boolean divisorGameDp2(int N) {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);
        return dfs(N, dp);
    }

    private boolean dfs(int N, int[] dp) {
        if (N == 1) {
            return false;
        }
        if (dp[N] != -1) {
            return dp[N] == 1;
        }
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) {
                int j = N / i;

                if (N > j && !dfs(N - j, dp)) {
                    dp[N] = 1;
                    return true;
                }
                if (N > i && !dfs(N - i, dp)) {
                    dp[N] = 1;
                    return true;
                }
            }
        }
        dp[N] = 0;
        return false;
    }

    /**
     * 1 Alice lose
     * 2 Alice will
     * If Alice will lose for N, then Alice will must win for N+1
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }
}

