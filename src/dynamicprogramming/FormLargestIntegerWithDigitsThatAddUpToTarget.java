package dynamicprogramming;

// Source : https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/
// Id     : 1449
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-06-09
// Topic  : Dynamic Programming
// Level  : Hard
// Other  :
// Tips   :
// Result : 100.00% 100.00%

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FormLargestIntegerWithDigitsThatAddUpToTarget {

    // 51.88% 100.00%
    public String largestNumber(int[] cost, int target) {
        // For the same cost, store the bigger number.
        Map<Integer, Integer> costMap = new HashMap<>();
        for (int i = cost.length - 1; i >= 0; i--) {
            if (!costMap.containsKey(cost[i])) {
                costMap.put(cost[i], i + 1);
            }
        }

        //dp[i]表示cost为i时最大数字
        String[] dp = new String[target + 1];
        dp[0] = "";
        for (int i = 1; i <= target; i++) {
            for (int num : costMap.keySet()) {
                if (num <= i && dp[i - num] != null) {
                    String b = dp[i - num] + costMap.get(num);
                    dp[i] = compare(dp[i], b);
                }
            }
        }
        return dp[target] == null ? "0" : dp[target];

    }

    public String compare(String a, String b) {
        if (a == null) return b;
        if (a.length() > b.length()) return a;
        if (a.length() == b.length())
            if (a.compareTo(b) > 0)
                return a;
        return b;
    }

    /**
     * 本质上来讲是两个问题，
     * 1. 给定数值，得出组合方式。
     * 2. 组合方式中，取最长，并且倒叙排列的那个组组成字符串。
     * @param cost
     * @param target
     * @return
     */
    //100.00% 100.00%
    public String largestNumber2(int[] cost, int target) {
//    public String largestNumber(int[] cost, int target) {
        // 组成dp[target]的最大数字个数
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int num : cost) {
            for (int i = num; i <= target; i++) {
                if (dp[i - num] != -1 
                        && (dp[i] == -1 || dp[i] < dp[i - num] + 1)) {
                    dp[i] = dp[i - num] + 1;
                }
            }
        }
        if (dp[target] == -1) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 8; i >= 0; i--) {
            while (target - cost[i] >= 0 && dp[target - cost[i]] + 1 == dp[target]) {
                sb.append(i + 1);
                target -= cost[i];
            }
        }
        return sb.toString();
    }
}
