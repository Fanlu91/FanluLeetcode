package dynamicprogramming;

// Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
// Id     : 714
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-11
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 80.67% 100.00%

public class BestTimeToBuyAndSellStockWithTransactionFee {

    // 12.18% 8 ms 100.00%
    public int maxProfitOrigin(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[] buy = new int[prices.length], sell = new int[prices.length];
        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(buy[i - 1] + prices[i] - fee, sell[i - 1]);
        }

        return sell[prices.length - 1];
    }

    // improve performance
    // 80.67% 5 ms 100.00%
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int preBuy = -prices[0];
        int preSell = 0;

        for (int i = 1; i < prices.length; i++) {
            int buy = Math.max(preBuy, preSell - prices[i]);
            int sell = Math.max(preBuy + prices[i] - fee, preSell);

            preBuy = buy;
            preSell = sell;
        }
        return preSell;
    }
}
