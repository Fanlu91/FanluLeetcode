package dynamicprogramming;

// Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
// Id     : 309
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-11
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   : For calculation purpose, You have to buy at least once when possible, even if the result is below 0
// Result : 100.00% 100.00%

public class BestTimeToBuyAndSellStockWithCooldown {

    /**
     * On any i-th day, we can buy, sell or cooldown
     * <p>
     * buy[i]: The maximum profit can be made if the first i days end with buy or wait.
     * E.g "buy, sell, buy" or "buy, cooldown, cooldown"
     * sell[i]: The maximum profit can be made if the first i days end with sell or wait.
     * E.g "buy, sell, buy, sell" or "buy, sell, cooldown, cooldown"
     * price: prices[i], which is the stock price of the i-th day
     * <p>
     * ###sell[i] >= cooldown[i] >= buy[i]###
     * <p>
     * sell[i] : Maximum profit which end with selling on day i or end with selling on a day before i
     * and takes rest until the day i since then.
     * buy[i] : Maximum profit which end with buying on day i or end with buying on a day before i
     * and takes rest until the day i since then.
     * ###You have to buy at least once when possible, even if the result is below 0 ###
     * <p>
     * To calculate sell[i]:
     * If we sell on the i-th day, the maximum profit is buy[i - 1] + price, because we have to buy before we can sell.
     * If we cooldown on the i-th day, the maximum profit is same as sell[i - 1] since we did not do anything on the i-th day.
     * So sell[i] is the larger one of (buy[i - 1] + price, sell[i - 1])
     * <p>
     * To calculate buy[i]:
     * If we buy on the i-th day, the maximum profit is sell[i - 2] - price, because on the (i-1)th day we can only cooldown.
     * If we cooldown on the i-th day, the maximum profit is same as buy[i - 1] since we did not do anything on the i-th day.
     * So buy[i] is the larger one of (sell[i - 2] - price, buy[i - 1])
     *
     * @param prices
     * @return
     */
    // 82.52%  22.22%
    public int maxProfitOrigin(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int[] buy = new int[prices.length], sell = new int[prices.length];
        buy[0] = -prices[0];
        sell[0] = 0;

        // I do this because sell[i-2]
        buy[1] = Math.max((0 - prices[1]), buy[0]);
        sell[1] = Math.max((buy[0] + prices[1]), sell[0]);

        for (int i = 2; i < prices.length; i++) {
            buy[i] = Math.max((sell[i - 2] - prices[i]), buy[i - 1]);
            sell[i] = Math.max((buy[i - 1] + prices[i]), sell[i - 1]);
        }

        return sell[prices.length - 1];
    }

    /**
     * improve performance by replacing arrays with variables.
     *
     * @param prices
     * @return
     */
    // 100.00%  100.00%
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int b0 = -prices[0];
        int b1 = b0;

        int s0 = 0;
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b0 + prices[i]);

            s2 = s1;
            s1 = s0;
            b1 = b0;
        }
        return s0;
    }


    /**
     * after trying to solve III, got below idea
     * status function
     * dp[i]=max(dp[i-1],price[i]- price[j] + dp[j-1]),j = 0...i-1
     * <p>
     * in order to get the right j, we do this
     * dp[i]=max(dp[i-1],price[i]- (price[j]-dp[j-1])),j = 0...i-1
     *
     * @param prices
     * @return
     */
    // 82.13% 1ms 100.00%
    public int maxProfitStatefunction(int[] prices) {
//    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int[] dp = new int[prices.length + 1];
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            // after a sell, the buy price is no longer valid, how can we make sure we do not sell at a wrong time?
            // for example later price is lower than the previous one plus profit
            // so we define min as follow
            min = Math.min(min, prices[i] - dp[i - 1]);
            dp[i + 1] = Math.max(dp[i], prices[i] - min);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCooldown b = new BestTimeToBuyAndSellStockWithCooldown();
        int[] a = {1, 2, 3, 0, 2, 4, 7, 2, 4};
        System.out.println(b.maxProfitStatefunction(a));
    }
}
