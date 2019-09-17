package greedy;

// Source : https://leetcode.com/problems/maximum-subarray/
// Id     : 53
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Greedy
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 99.53%

public class MaximumSubarray {
    // 85.40% 1ms 5.16%
    public int maxSubArray(int[] nums) {
        int res = 0, tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (tmp + nums[i] >= 0) {
                tmp += nums[i];
                if (tmp > res)
                    res = tmp;
            } else
                tmp = 0;
        }
        // if no sub array sum is bigger than 0
        if (res == 0 && nums.length > 0) {
            res = nums[0];
            for (int i = 0; i < nums.length; i++) {
                res = Math.max(res, nums[i]);
            }
        }

        return res;
    }

    // 100.00% 0ms 99.53%
    public int maxSubArrayImprove(int[] nums) {
//    public int maxSubArray(int[] nums) {

        int sum = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
