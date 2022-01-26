package matrix;
// Source : https://leetcode.com/problems/zero-matrix-lcci/
// Id     : mst01.08
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/20
// Topic  : matrix 
// Level  : Medium-
// Other  :
// Tips   :
// Links  :
// Result :

import java.util.HashSet;
import java.util.Set;

public class ZeroMatrixLCCI {
    // 2 ms
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>(), cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (Object row : rows.toArray()) {
            for (int i = 0; i < matrix[0].length; i++)
                matrix[(int) row][i] = 0;
        }
        for (Object col : cols.toArray()) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][(int) col] = 0;
        }
    }

    public void setZeroes1(int[][] matrix) {
//    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean col0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            if (col0) {
                matrix[i][0] = 0;
            }
        }
    }
}