package bitmanipulation;

import java.util.Arrays;

// Source : https://leetcode.com/problems/single-number-ii/
// Id     : 137
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-08-02
// Topic  : Bit Manipulation
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%
public class SingleNumberII {
    // 5.04% 10 ms 14.29%
    public int singleNumber(int[] nums) {
//        Long distinctSum = Long.valueOf(Arrays.stream(nums).distinct().sum());
//        Long sum = Long.valueOf(Arrays.stream(nums).sum());
        Long distinctSum = Arrays.stream(nums).distinct().mapToLong(num -> Long.valueOf(num)).sum();
        Long sum = Arrays.stream(nums).mapToLong(num -> Long.valueOf(num)).sum();

        // long 2147483647 casted to int -1
        //        return (int) (distinctSum * 3 - sum) / 2;
        return Math.toIntExact((distinctSum * 3 - sum) / 2);
    }

    public int singleNumber0(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                counts[i] += num & 1;
                num >>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % 3;
        }
        return res;
    }


    public int singleNumber1(int[] nums) {
        int one = 0, two = 0, three;
        for (int num : nums) {
            // two的相应的位等于1，表示该位出现2次
            two |= (one & num);
            // one的相应的位等于1，表示该位出现1次
            one ^= num;
            // three的相应的位等于1，表示该位出现3次
            three = (one & two);
            // 如果相应的位出现3次，则该位重置为0
            two &= ~three;
            one &= ~three;
        }
        return one;
    }

    public int singleNumber2(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

}
