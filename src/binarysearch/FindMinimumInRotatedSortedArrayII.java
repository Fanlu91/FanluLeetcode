package binarysearch;

// Source : https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// Id     : 154
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : Hard
// Other  :
// Tips   :
// Result : 100.00% 29.57%

public class FindMinimumInRotatedSortedArrayII {

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }
}
