package binarysearch;

// Source : https://leetcode.com/problems/median-of-two-sorted-arrays
// Id     : 4
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021-01-01
// Topic  : Binary Search
// Level  : Hard
// Other  :
// Tips   :
// Result : 92.12% 84.77%

public class MedianOfTwoSortedArrays {
    // 2 ms
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A); // 保证 A.length <= B.length
        }
        int aL = 0, aR = A.length;
        while (aL <= aR) {
            // aM 的左边和 bM 的左边组合成「左半部分」，他们的右边分别组合成「右半部分」。
            // bM 并不用二分计算，而是通过aMid的位置推倒出来，这样就将问题简化为一个值的二分查找。
            // aM + bM = (A.length + B.length + 1) / 2
            int aM = (aL + aR) / 2, bM = (A.length + B.length + 1) / 2 - aM;
            if (bM != 0 && aM != A.length && B[bM - 1] > A[aM]) { // aM 需要增大，右移
                aL = aM + 1;
            } else if (aM != 0 && bM != B.length && A[aM - 1] > B[bM]) { // aM 需要减小，左移
                aR = aM - 1;
            } else { // 达到要求 B[bM - 1] <= A[aM] && A[aM - 1] <= B[bM]，将边界条件列出来单独考虑
                int left = 0;
                if (aM == 0) {
                    left = B[bM - 1];
                } else if (bM == 0) {
                    left = A[aM - 1];
                } else {
                    left = Math.max(A[aM - 1], B[bM - 1]);
                }
                if ((A.length + B.length) % 2 == 1) {
                    return left;
                } // 奇数的话不需要考虑右半部分

                int right = 0;
                if (aM == A.length) {
                    right = B[bM];
                } else if (bM == B.length) {
                    right = A[aM];
                } else {
                    right = Math.min(B[bM], A[aM]);
                }

                return (left + right) / 2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }

    // to do
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int s1 = 0;
        int s2 = 0;
        int curr = 0;
        int t1 = 0;
        int t2 = 0;
        double result = 0;
        while (curr++ <= (nums1.length + nums2.length) / 2) {
            t1 = t2;
            if (nums1.length - 1 < s1) {
                t2 = nums2[s2++];
            } else if (nums2.length - 1 < s2) {
                t2 = nums1[s1++];
            } else if (nums1[s1] < nums2[s2]) {
                t2 = nums1[s1++];
            } else {
                t2 = nums2[s2++];
            }
        }
        if ((nums1.length + nums2.length) % 2 == 1) {
            result = t2;
        } else {
            result = (t1 + t2) / 2.0;
        }
        return result;
    }


    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        medianOfTwoSortedArrays.findMedianSortedArrays(new int[]{2}, new int[]{});
    }
}


