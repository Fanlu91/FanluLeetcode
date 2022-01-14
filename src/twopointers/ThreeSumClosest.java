package twopointers;

// Source : https://leetcode.com/problems/3sum-closest/
// Id     : 16
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : Medium
// Other  :
// Tips   :
// Result : 85.38% 17.73%


import java.util.Arrays;

public class ThreeSumClosest {
    // 5ms
    public int threeSumClosest1(int[] nums, int target) {
//    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1, sum = 0;
            while (l < r) {
                sum = nums[l] + nums[r] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - res))
                    res = sum;
                if (sum > target)
                    r--;
                else if (sum < target)
                    l++;
                else
                    return res;
            }
        }
        return res;
    }

}
