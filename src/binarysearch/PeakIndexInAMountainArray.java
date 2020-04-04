package binarysearch;

// Source : https://leetcode.com/problems/peak-index-in-a-mountain-array/
// Id     : 852
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.16%

public class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] A) {
        int l = 0, r = A.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (A[m] > A[m - 1] && A[m] > A[m + 1])
                return m;
            if (A[m] < A[m - 1]) {
                r = m;
            } else { // A[m]<A[m+1]
                l = m;
            }
        }
        return 0;
    }

    public int peakIndexInMountainArray2(int[] A) {
        int l = 0, r = A.length - 1;

        while (l < r) {
            int mi = (l + r) / 2;
            if (A[mi] < A[mi + 1]) {
                l = mi + 1;
            } else {
                r = mi;
            }
        }
        return l;
    }
}
