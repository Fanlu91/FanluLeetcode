package twopointers;

// Source : https://leetcode.com/problems/remove-duplicates-from-sorted-array/
// Id     : 26
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100% 99.47%

import java.util.HashSet;

public class RemoveDuplicatesFromSortedArray {
    // 97.75% 1ms 75.00%
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;

        new HashSet<>().toArray(new Integer[]{});

        int pointer = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[pointer]) {
                // use pointer to write all distinct value.
                nums[++pointer] = nums[i];
            }
        }
        return pointer + 1;
    }

    // best
    // 100% 99.47%
    public int removeDuplicatesImprove(int[] nums) {
//    public int removeDuplicates(int[] nums) {
        int res = 0;
        int i = 0;

        while (i < nums.length) {
            if (nums[res] != nums[i]) {
                // 最关键的思路在下面两行，先增加res，再移动
                res++;
                nums[res] = nums[i];
            }
            i++;
        }
        return res + 1;
    }

    // practice
    public int removeDuplicates2(int[] nums) {
//    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int right = 1, left = 0;
        for (; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
        }
        return left + 1;
    }

    // practice
    public int removeDuplicates3(int[] nums) {
//    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int res = 1;
        int i = 1;
        while (i < nums.length) {
            if (nums[i] != nums[i - 1]) {
                nums[res] = nums[i];
                res++;
            }
            i++;
        }
        return res;
    }
}
