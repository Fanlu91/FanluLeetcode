package matrix;
// Source : https://leetcode.com/problems/reshape-the-matrix/
// Id     : 985
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-09
// Topic  : Matrix
// Level  : Easy-
// Other  : 托普利兹矩阵，简称为T型矩阵
// Tips   :
// Result : 100.00% 100.00%

public class ReshapeTheMatrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length != r * c)
            return nums;

        int[][] ans = new int[r][c];
        int rowIndex = 0;
        int columnIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (columnIndex == c) {
                    columnIndex = 0;
                    rowIndex++;
                }
                ans[rowIndex][columnIndex] = nums[i][j];
                columnIndex++;
            }
        }
        return ans;
    }
}
