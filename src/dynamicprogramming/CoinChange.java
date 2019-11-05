package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/coin-change/
// Id     : 322
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-11
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 99.99% 100%
public class CoinChange {
    Map<Integer, Integer> map = new HashMap<>();

    // 5.77% 109 ms 5.33%
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        if (map.containsKey(amount))
            return map.get(amount);

        int leastStep = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {

            if (amount < coins[i])
                continue;
            // too many steps wasted here, as a lot function should not be called.
            int step = 1 + coinChange(coins, amount - coins[i]);
            if (step > 0)
                leastStep = Math.min(leastStep, step);
        }
        if (leastStep == Integer.MAX_VALUE)
            leastStep = -1;

        map.put(amount, leastStep);
        return leastStep;
    }


    // 70.86%% 10 ms 94.08%
    public int coinChangeImprove(int[] coins, int amount) {
//    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    private int[] coins;
    private Integer result = null;

    // 99.99% 1ms 100%
    public int coinChangeBest(int[] coins, int amount) {
//    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        this.coins = coins;
        dfs(amount, coins.length - 1, 0);
        return result == null ? -1 : result;
    }

    private void dfs(int amount, int end, int current) {
        if (amount == 0) {
            result = current;
            return;
        }
        if (end < 0) {
            return;
        }

        int coin = coins[end];
        if (result != null && current + (amount + coin - 1) / coin >= result) {
            return;
        }

        int remain = amount % coin;
        for (int i = amount / coin; i >= 0; i--) {
            dfs(remain, end - 1, current + i);
            remain += coin;
        }
    }


    public static void main(String[] args) {
        CoinChange c = new CoinChange();
//        System.out.println(c.coinChange(new int[]{404, 83, 3}, 264));
//        System.out.println(c.coinChangeImprove(new int[]{11, 5, 1}, 15));
        System.out.println(c.coinChangeBest(new int[]{11, 5, 1}, 15));
    }

}
