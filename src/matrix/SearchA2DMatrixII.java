package matrix;
// Source : https://leetcode.com/problems/search-a-2d-matrix-ii/
// Id     : 240
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/12/29
// Topic  : matrix 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 97.85% 17.32%

public class SearchA2DMatrixII {
    // 二分查找在二维空间并不好直接展开
//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix.length == 0 || matrix[0].length == 0)
//            return false;
//        int row = matrix.length, col = matrix[0].length;
//        int top = 0, bottom = row - 1, left = 0, right = col - 1;
//        int midRow = (top + bottom) / 2, midCol = (left + right) / 2;
//        while (top <= bottom && left <= right) {
//            if (target == matrix[midRow][midCol])
//                return true;
//
//            else
//        }
//        return false;
//    }

    // 从右上角开始搜索
    // 5 ms
    public boolean searchMatrix1(int[][] matrix, int target) {
//    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 && matrix[0].length == 0)
            return false;
        int i = 0, j = matrix[0].length - 1;  //矩阵右上角
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] < target)
                i++;  //排除一行
            else
                j--;  //排除一列
        }
        return false;
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
//    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = matrix[mid / col][mid % col];
            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}