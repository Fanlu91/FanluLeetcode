package dynamicprogramming;

// Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// Id     : 122
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-10
// Topic  : Dynamic Programming
// Level  : Easy-
// Other  :
// Tips   :
// Result : 94.81% 96.19%

public class BestTimeToBuyAndSellStockII {

    // 100.00% 100.00%
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0)
                ans += prices[i] - prices[i - 1];
        }
        return ans;
    }
}
