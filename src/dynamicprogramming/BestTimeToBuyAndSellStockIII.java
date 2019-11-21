package dynamicprogramming;

// Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
// Id     : 123
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-16
// Topic  : Dynamic Programming
// Level  : Hard
// Other  :
// Tips   : Checkout BestTimeToBuyAndSellStockWithCooldown first
// Result : 99.75% 100.00%

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BestTimeToBuyAndSellStockIII {

    /**
     * On every day, we buy the share with the price as low as we can, and sell the share with price as high as we can.
     * For the second transaction, we integrate the profit of first transaction into the cost of the second buy,
     * then the profit of the second sell will be the total profit of two transactions.
     * <p>
     * the same for k transactions.
     * <p>
     * dp[k,i] = max(dp[k,i-1], prices[i]- (prices[j]-dp[k-1,j-1])),j= 0...i
     *
     * @param prices
     * @return
     */
    // 22.08% 4ms 100.00%
    public int maxProfitOrigin(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int totalTrans = 2;
        Map<Integer, int[]> dp = new HashMap<>();
        // dp.0 helps the calculation
        for (int i = 0; i <= totalTrans; i++) {
            dp.put(i, new int[prices.length + 1]);
        }

        for (int i = 1; i <= totalTrans; i++) {
            int min = prices[0];
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min, prices[j] - dp.get(i - 1)[j - 1]);
                dp.get(i)[j] = Math.max(dp.get(i)[j - 1], prices[j] - min);
            }
        }
        return dp.get(totalTrans)[prices.length - 1];
    }

    /**
     * swap for loop,
     * the second dimension (variable i) is only dependent on the previous one (i-1),
     * so we can compact this dimension.
     *
     * @param prices
     * @return
     */
    // 99.75% 100.00%
    public int maxProfitImprove(int[] prices) {
//    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int totalTrans = 2;
        //we need to keep multiple min as the loop changes.
        int[] min = new int[totalTrans + 1];
        Arrays.fill(min, prices[0]);

        // compact based on the situation
        int[] dp = new int[totalTrans + 1];

        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= totalTrans; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }
        return dp[totalTrans];
    }

    public static void main(String[] args) {
        int[] a = {3, 3, 5, 0, 0, 3, 1, 4};
        BestTimeToBuyAndSellStockIII b = new BestTimeToBuyAndSellStockIII();
//        System.out.print(b.maxProfit(a));
    }
}
