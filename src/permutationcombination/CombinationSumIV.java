package permutationcombination;
// Source : https://leetcode.com/problems/combination-sum-iv/
// Id     : 377
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/20
// Topic  : DP
// Level  : Medium-
// Other  :
// Tips   : 比较简单的dp题目
// Links  :
// Result : 99.56% 51.40%

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumIV {
    /**
     * 使用类似 77 的回溯解法 TLE
     * @param nums
     * @param target
     * @return
     */
    // TLE
    public int combinationSum4TLE(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        backtrackingP(nums, target, new LinkedList<Integer>(), ans);
        return ans.size();
    }

    private void backtrackingP(int[] candidates, int target, LinkedList<Integer> tmp, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new LinkedList<>(tmp));
            return;
        }
        for (int candidate : candidates) {
            if (target < candidate)
                continue;
            target -= candidate;
            tmp.add(candidate);
            backtrackingP(candidates, target, tmp, ans);
            tmp.remove(tmp.size() - 1);
            target += candidate;
        }
    }

    // 99.56% 1ms 51.40%
    public int combinationSum4(int[] nums, int target) {
//    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        for (int i : nums) {
            if (i <= target)
                dp[i] = 1;
        }

        for (int i = 0; i < target; i++) {
            for (int num : nums) {
                if (i + num < target + 1)
                    dp[i + num] += dp[i];
            }
        }
        return dp[target];
    }
}