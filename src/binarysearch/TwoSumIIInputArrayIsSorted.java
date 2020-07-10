package binarysearch;
// Source : https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// Id     : 167
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-11
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

    // 6.53% 261 ms 6.67%
    public int[] twoSum0(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i])
                    return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }

    /**
     * two pointers
     *
     * @param nums
     * @param target
     * @return
     */
    // 96.09% 1ms 6.67%
    public int[] twoSum1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target)
                return new int[]{left + 1, right + 1};
            else if (nums[left] + nums[right] < target)
                left++;
            else
                right--;
        }
        return null;
    }

    /**
     * binary search
     *
     * @param nums
     * @param target
     * @return
     */
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

