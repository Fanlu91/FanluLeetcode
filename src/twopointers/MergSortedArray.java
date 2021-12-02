package twopointers;

// Source : https://leetcode.com/problems/merge-sorted-array/
// Id     : 88
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : Easy-
// Other  :
// Tips   :
// Result : 100.00% 73.09%

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

    // rewrite
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (m > 0 && nums1[m - 1] > nums2[i]) {
                nums1[index] = nums1[m - 1];
                index--;
                m--;
            }
            nums1[index] = nums2[i];
            index--;
        }

    }
}
