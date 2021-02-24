package permutationcombination;
// Source : https://leetcode.com/problems/subsets-ii/
// Id     : 90
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/20
// Topic  : combination
// Level  : Medium
// Other  :
// Tips   :
// Links  : 39,78
// Result : 100.00% 24.48%

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(0, nums, false, new ArrayList<>(), ans);
        return ans;
    }

    private void backtracking(int start, int[] nums, boolean pre, List<Integer> path, List<List<Integer>> ans) {
        if (start == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        /**
         * 11
         * 10
         * 01 排除此类重复情况
         * 00
         */
        if (pre || start == 0 || nums[start - 1] != nums[start]) {
            path.add(nums[start]);
            backtracking(start + 1, nums, true, path, ans);
            path.remove(path.size() - 1);
        }
        backtracking(start + 1, nums, false, path, ans);
    }
}