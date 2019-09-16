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

    // 100.00% 1 ms, 100.00%
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // quick case
        if (k > prices.length / 2 + 1) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i - 1] > 0)
                    ans += prices[i] - prices[i - 1];
            }
            return ans;
        }

        int[] min = new int[k + 1];
        Arrays.fill(min, prices[0]);

        int[] dp = new int[k + 1];

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                min[j] = Math.min(min[j], prices[i] - dp[j - 1]);
                dp[j] = Math.max(dp[j], prices[i] - min[j]);
            }
        }
        return dp[k];
    }

    public static void main(String[] args) {
        int[] a = {3, 3, 5, 0, 0, 3, 1, 4};
        BestTimeToBuyAndSellStockIV b = new BestTimeToBuyAndSellStockIV();
//        System.out.print(b.maxProfit(a));
    }
}
