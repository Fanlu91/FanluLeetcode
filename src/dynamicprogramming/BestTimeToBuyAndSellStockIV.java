package dynamicprogramming;

// Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
// Id     : 188
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-16
// Topic  : Dynamic Programming
// Level  : Hard
// Other  :
// Tips   : My approach is almost identical to the one that solved III, add a "quickSolve" function to tackle some corner cases to avoid TLE.
// Result : 100.00% 100.00%

import java.util.Arrays;


public class BestTimeToBuyAndSellStockIV {

    // 97.82% 4ms 50.49%
    public int maxProfitSlow(int k, int[] prices) {
//        public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        if (k > prices.length / 2 + 1) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i - 1] > 0)
                    ans += prices[i] - prices[i - 1];
            }
            return ans;
        }

        int[][] dp = new int[k + 1][prices.length];

        for (int i = 1; i <= k; i++) {
            int minPrice = prices[0];
            for (int j = 1; j < prices.length; j++) {
                minPrice = Math.min(minPrice, prices[j] - dp[i - 1][j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] - minPrice);
            }
        }
        return dp[k][prices.length - 1];
    }

    // 100.00% 1 ms, 100.00%
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        if (k > prices.length / 2 + 1) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i - 1] > 0)
                    ans += prices[i] - prices[i - 1];
            }
            return ans;
        }

        int[] minPrice = new int[k + 1];
        Arrays.fill(minPrice, prices[0]);

        int[] dp = new int[k + 1];

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                minPrice[j] = Math.min(minPrice[j], prices[i] - dp[j - 1]);
                dp[j] = Math.max(dp[j], prices[i] - minPrice[j]);
            }
        }
        return dp[k];
    }

    public static void main(String[] args) {
        int[] a = {3, 3, 5, 0, 0, 3, 1, 4};
        BestTimeToBuyAndSellStockIV b = new BestTimeToBuyAndSellStockIV();
        System.out.print(b.maxProfitSlow(2, a));
    }
}
