package array;
// Source : https://leetcode.com/problems/squares-of-a-sorted-array/
// Id     : 977
// Author : Fanlu Hai
// Date   : 2018-06-04
// Topic  : Array
// Other  :
// Tips   :
// Result : 100.00% 96.27%

public class SquaresOfASortedArray {

    public int[] sortedSquares(int[] A) {
        if (A == null)
            return A;

        int head = 0, tail = A.length - 1;
        int s1 = A[head] * A[head], s2 = A[tail] * A[tail];

        int[] result = new int[A.length];
        int rIndex = tail;

        while (rIndex > -1) {
            if (s1 > s2) {
                result[rIndex] = s1;
                head++;
                s1 = A[head] * A[head];
            } else {
                result[rIndex] = s2;
                // mind the corner case
                if (tail == 0)
                    break;
                tail--;
                s2 = A[tail] * A[tail];
            }
            rIndex--;
        }
        return result;

    }
}
