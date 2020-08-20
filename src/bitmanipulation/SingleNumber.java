package bitmanipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/single-number/
// Id     : 136
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-08-02
// Topic  : Bit Manipulation
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%

public class SingleNumber {
    // 10.44% 13 ms 5.71%
    public int singleNumber0(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        for (int num : map.keySet())
            if (map.get(num) == 1)
                return num;
        return 0;
    }

    // 20.17% 9 ms 7.14%
    public int singleNumber1(int[] nums) {
        int distinctSum = Arrays.stream(nums).distinct().sum();
        int sum = Arrays.stream(nums).sum();
        return distinctSum * 2 - sum;
    }

    // 100% 100%
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
