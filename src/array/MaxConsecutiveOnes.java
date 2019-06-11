package array;

// Source : https://leetcode.com/problems/max-consecutive-ones/
// Id     : 485
// Author : Fanlu Hai
// Date   : 2018-06-10
// Topic  : Array
// Level  : Easy-
// Other  :
// Tips   : Do not use for reach with array. It is much slower.
// Result : 99.83% 99.97%
public class MaxConsecutiveOnes {
    // 20.56% 4 ms 93.19%
    public int findMaxConsecutiveOnesOrigin(int[] nums) {
        int max = 0, tmp = 0;
        for (int i : nums) {
            if (i == 1)
                tmp++;
            else {
                if (tmp > max)
                    max = tmp;
                tmp = 0;
            }
        }
        return tmp > max ? tmp : max;
    }

    // 99.83% 2ms 99.97%
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                tmp++;
            } else {
                if (tmp > max)
                    max = tmp;
                tmp = 0;
            }
        }
        return tmp > max ? tmp : max;
    }
}
