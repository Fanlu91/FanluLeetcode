package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/coin-change-2/
// Id     : 518
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-11
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 8.33%
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            if (coin > amount)
                continue;
            for (int i = coin; i < amount + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
