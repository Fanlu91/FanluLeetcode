package array;

// Source : https://leetcode.com/problems/rotate-array/
// Id     : 189
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Array
// Level  : easy
// Other  :
// Tips   :
// Result : 100% 100%%

import java.util.Arrays;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (nums.length < 2)
            return;
        k = k % nums.length;

        int[] tmp = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = tmp[i];
        }
    }
}
