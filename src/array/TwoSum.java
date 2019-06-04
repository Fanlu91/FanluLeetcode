package array;

import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/two-sum/
// Id     : 1
// Author : Fanlu Hai
// Date   : 2018-06-04
// Topic  : Array
// Other  :
// Tips   :
// Result : 99.23%  99.75%

public class TwoSum {

    int tmp;

    // 25.49% 99.68%
    public int[] twoSumSlow(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (match(nums, target - nums[i])) {
                if (tmp != i)
                    return new int[]{i, tmp};
            }
        }
        return null;
    }

    public boolean match(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                this.tmp = i;
                return true;
            }
        }
        return false;
    }

    // 25.83%  99.69%
    public int[] twoSumSlightlyImproved(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == target - nums[i] && j != i)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    // 99.23%  99.75%
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
