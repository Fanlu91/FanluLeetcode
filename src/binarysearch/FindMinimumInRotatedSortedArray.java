package binarysearch;

// Source : https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// Id     : 153
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : medium
// Other  :
// Tips   :
// Result : 100.00% 29.57%

public class FindMinimumInRotatedSortedArray {
    /**
     * after rotation，all elements on the left side is bigger than all on the right
     * so the minimum value is the **first element on the right side**
     * <p>
     * if num[m] > num[r], it means m is on the left, move l to m + 1 to check [m+1,r];
     * if num[m] < num[r], it means m is on the right side, move r to m to check [m,r],
     * you should not check [m + 1, r] here as m is already on the right side ,you might skip the right element(num[m]).
     *
     * @param nums
     * @return
     */
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
