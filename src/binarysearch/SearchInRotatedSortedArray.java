package binarysearch;
// Source : https://leetcode.com/problems/search-in-rotated-sorted-array/
// Id     : 33
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/24
// Topic  : binarysearch 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 29.84%

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid = -1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[l] <= nums[mid]) {//left side sorted
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {// rigth side sorted
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}