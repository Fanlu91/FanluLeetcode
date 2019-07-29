package matrix;

// Source : https://leetcode.com/problems/toeplitz-matrix/
// Id     : 766
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-07
// Topic  : Matrix
// Level  : Easy
// Other  : 托普利兹矩阵，简称为T型矩阵
// Tips   :
// Result : 100.00% 73.60%

public class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (!checkDiagonal(matrix, i, 0))
                return false;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (!checkDiagonal(matrix, 0, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonal(int[][] matrix, int m, int n) {
        while ((m != matrix.length - 1) && (n != matrix[0].length - 1)) {
            if (matrix[m][n] != matrix[m + 1][n + 1])
                return false;
            m++;
            n++;
        }
        return true;
    }
}
