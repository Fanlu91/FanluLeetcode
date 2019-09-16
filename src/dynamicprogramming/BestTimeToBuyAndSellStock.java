package dynamicprogramming;

// Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// Id     : 121
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-03
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class BestTimeToBuyAndSellStock {
    // 10.12% 98.23%
    public int maxProfitOrigin(int[] prices) {
        int res = 0;
        for (int i = prices.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (prices[i] - prices[j] > res)
                    res = prices[i] - prices[j];
            }
        }
        return res;
    }

    // 100.00% 100.00%
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            max = Math.max(prices[i] - min, max);
            min = Math.min(min, prices[i]);
        }

        return max;
    }
}
