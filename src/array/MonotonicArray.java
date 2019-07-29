package array;
// Source : https://leetcode.com/problems/monotonic-array/
// Id     : 896
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-10
// Topic  : Array
// Level  : Easy-
// Other  :
// Tips   :
// Result : 100.00% 96.10%

public class MonotonicArray {
    // 35.89% 3 ms 58.02%
    public boolean isMonotonicOrigin(int[] A) {
        if (A.length < 2)
            return true;

        boolean increase = true, decrease = true;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1])
                continue;
            if (A[i] > A[i - 1]) {
                increase &= true;
                decrease &= false;
            } else {
                increase &= false;
                decrease &= true;
            }
        }

        if (increase || decrease)
            return true;
        else
            return false;
    }

    //82.90% 2 ms 93.46% 49.4 MB
    public boolean isMonotonicImproved(int[] A) {
        if (A.length < 2)
            return true;

        boolean increase = true, decrease = true;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1])
                continue;
            if (A[i] > A[i - 1]) {
                decrease = false;
            } else {
                increase = false;
            }

            if (!increase && !decrease)
                return false;
        }

        return true;
    }

    // leetcode fastest solution, which is actually the most direct solution.. I thought it will not be good enough.
    // 100.00% 1ms 96.10%
    public boolean isMonotonic(int[] A) {
        return increasing(A) || decresing(A);
    }

    public boolean increasing(int[] A) {

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean decresing(int[] A) {

        for (int i = 0; i < A.length - 1; i++)
            if (A[i] < A[i + 1]) {
                return false;
            }
        return true;

    }
}
