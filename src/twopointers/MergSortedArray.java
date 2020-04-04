package twopointers;

// Source : https://leetcode.com/problems/merge-sorted-array/
// Id     : 88
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : Easy
// Other  : 10m
// Tips   :
// Result : 100.00% 5.24%

public class MergSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length - 1;
        m--;
        n--;

        while (n >= 0) {
            if (m < 0 || nums1[m] < nums2[n]) {
                nums1[index] = nums2[n];
                n--;
            } else {
                nums1[index] = nums1[m];
                m--;
            }
            index--;
        }
    }
}
