package bitmanipulation;

import java.util.Arrays;

// Source : https://leetcode.com/problems/single-number-iii/
// Id     : 260
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-08-02
// Topic  : Bit Manipulation
// Level  : Medium
// Other  :
// Tips   : 100% 16.67%
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        // 找到异或结果中第一位不是 0 的，用它来对所有数字进行分组
        // 这样两个结果数字会在不同组
        // 同时所有相同数组会在一组
        int h = 1;
        while ((ret & h) == 0)
            h <<= 1;
        int[] ans = new int[2];
        for (int num : nums) {
            if ((h & num) == 0)
                ans[0] ^= num;
            else
                ans[1] ^= num;
        }
        return ans;
    }

}
