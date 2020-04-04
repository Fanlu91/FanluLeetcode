package binarysearch;

// Source : https://leetcode.com/problems/binary-search/
// Id     : 704
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.09%

public class BinarySearch {
    public int search(int[] nums, int target) {
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r / 2);

            if (nums[m] == target)
                return m;
            if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
