package array;

import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/two-sum/
// Id     : 1
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-04
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 99.23%  99.75%

public class TwoSum {

    // 62 ms
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && j != i)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    // 2ms
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // 1ms
    // didn't see much of a difference, prefer the one above
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int ln0 = nums[left];
            int rn0 = nums[right];
            int ln1 = target - ln0;
            int rn1 = target - rn0;
            if (map.containsKey(ln1)) {
                return new int[]{left, map.get(ln1)};
            } else {
                map.put(ln0, left++);
            }
            if (map.containsKey(rn1)) {
                return new int[]{right, map.get(rn1)};
            } else {
                map.put(rn0, right--);
            }
        }
        return new int[]{};
    }
}
