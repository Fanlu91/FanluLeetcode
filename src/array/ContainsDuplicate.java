package array;

// Source : https://leetcode.com/problems/contains-duplicate/
// Id     : 217
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-11
// Topic  : Array
// Level  : Easy
// Other  : This problem doesn't have sufficient test cases
// Tips   :
// Result : 99.06% 65.20%

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    // 51.04% 9 ms 46.05%
    public boolean containsDuplicateOrigin(int[] nums) {
        boolean ans = false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true;
            else
                set.add(nums[i]);
        }
        return ans;
    }


    // 99.06% 3ms 65.20%
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2)
            return false;

        Arrays.sort(nums);
        int i = 1;
        int j = 0;
        while (i < nums.length) {
            if (nums[j] == nums[i])
                return true;
            else
                j++;
            nums[j] = nums[i];
            i++;
        }
        return false;
    }

    //
    public boolean containsDuplicateWrong(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j > -1; j--) {
                if (nums[i] > nums[j]) {//????
                    break;
                } else if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;

    }


    public static void main(String[] args) {
        ContainsDuplicate c = new ContainsDuplicate();
        int[] test = {1, 2, 3, 1};
        int[] test1 = {3, 2, 1, 3};
//        System.out.println(c.containsDuplicate(test));
        System.out.println(c.containsDuplicate(test1));
    }
}
