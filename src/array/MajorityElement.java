package array;

import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/majority-element/
// Id     : 169
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-24
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   : boyer moore voting algorithm;
//          take full advantage of the situation to get the best solution.
// Result : 100.00% 50.94%

public class MajorityElement {
    // straight forward way is to store appearance of number in a hashmap
    // 16.69% 16 ms 99.07% 39.1 MB
    public int majorityElementSlow(int[] nums) {
//        System.out.println(nums.length / 2 + 1);
        Map<Integer, Integer> counter = new HashMap<>();

        if (nums.length == 1)
            return nums[0];

        for (int num : nums) {
            if (counter.containsKey(num)) {
                if (counter.get(num) == nums.length / 2)
                    return num;
                else
                    counter.put(num, counter.get(num) + 1);
            } else
                counter.put(num, 1);
        }

        return -1;
    }

    // boyer moore voting algorithm
    // 100.00% 1ms 50.94% 41.5 MB
    public int majorityElement(int[] nums) {
        int count = 0;
        int major = nums[0];

        for (int num : nums) {
            if (num == major)
                count++;
            else if (count == 0)
                major = num;
            else
                count--;
        }
        return major;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 1};
        MajorityElement m = new MajorityElement();
        System.out.println(m.majorityElement(array));
    }

}
