package binarysearch;
// Source : https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// Id     : 167
// Author : Fanlu Hai
// Date   : 2018-06-11
// Topic  : Binary Search
// Level  : Easy
// Other  :
// Tips   : If there's a SORTED array, binary-search should come to your mind immediately
// Result : 100.00% 63.39%

import java.util.HashMap;
import java.util.Map;

public class TwoSumIIInputArrayIsSorted {

    // 28.02% 63.47%
    public int[] twoSumFromOld(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]) + 1, i + 1}; // one based
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // 100.00% 63.39%
    public int[] twoSum(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (true) {
            int mid = start + (end - start) / 2;
            if (nums[start] + nums[end] == target) {
                return new int[]{start + 1, end + 1};
            } else if (nums[start] + nums[end] < target) {
                start = nums[mid] + nums[end] < target ? mid : start + 1;
            } else {
                end = nums[mid] + nums[start] > target ? mid : end - 1;
            }
        }
    }


}

