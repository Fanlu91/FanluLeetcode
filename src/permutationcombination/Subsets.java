package permutationcombination;
// Source : https://leetcode.com/problems/subsets/
// Id     : 78
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/20
// Topic  : combination
// Level  : Medium
// Other  :
// Tips   :
// Links  : Must
// Result : 82.14% 5.24%

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        combination(nums, 0, res, new LinkedList<>());
        return res;
    }

    private void combination(int[] nums, int start, List<List<Integer>> ans, List<Integer> path) {
        ans.add(new LinkedList<Integer>(path));
        if (start > nums.length)
            return;
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            combination(nums, i + 1, ans, path);
            path.remove(path.size() - 1);
        }
    }


    public List<List<Integer>> subsets1(int[] nums) {
//    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    private void backtracking(int start, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (start == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[start]);
        backtracking(start + 1, nums, path, ans);
        path.remove(path.size() - 1);
        backtracking(start + 1, nums, path, ans);
    }
}