package array;

// Source : https://leetcode.com/problems/transpose-matrix
// Id     : 867
// Author : Fanlu Hai
// Date   : 2018-06-05
// Topic  : Array
// Other  : too simple to be on leetcode I assume
// Tips   :
// Result : 100.00% 99.81%

public class TransposeMatrix {
    public int[][] transpose(int[][] A) {
        int[][] ans = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                ans[j][i] = A[i][j];
            }
        }
        return ans;
    }
}
