package bitmanipulation;

// Source : https://leetcode.com/problems/single-number/
// Id     : 136
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-08-02
// Topic  : Bit Manipulation
// Level  : Easy
// Other  :
// Tips   : 100% 100%
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
