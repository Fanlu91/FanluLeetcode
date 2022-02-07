package matrix;

// Source : https://leetcode.com/problems/spiral-matrix/
// Id     : 54
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-07-29
// Topic  : Matrix
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 99.54%

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {

    // O(M*N)
    // 0ms
    public List<Integer> spiralOrder(int[][] matrix) {
        int direction = 0;
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;

        int row = 0, column = 0, preRow = 0, preColumn = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        boolean justTurned = false;
        int size = matrix.length * matrix[0].length;

        for (int i = 0; i < size; ) {
            if (column == matrix[0].length
                    || column == -1
                    || row == matrix.length
                    || row == -1
                    || visited[row][column]) {
                row = preRow;
                column = preColumn;
                direction = (direction + 1) % 4;
                justTurned = true;
            }

            if (!justTurned) {
                i++;
                visited[row][column] = true;
                res.add(matrix[row][column]);
                preColumn = column;
                preRow = row;
            }

            if (direction == 0) {
                column++;
            } else if (direction == 1) {
                row++;
            } else if (direction == 2) {
                column--;
            } else {
                row--;
            }
            justTurned = false;
        }
        return res;
    }

    /**
     * 不使用额外的visited
     * <p>
     * 而是不断缩小上下左右边界遍历
     *
     * @param matrix
     * @return
     */
    // 100.00% 93.27%
    public List<Integer> spiralOrderO1(int[][] matrix) {
//    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;

        while (true) {
            for (int i = l; i <= r; i++)
                res.add(matrix[t][i]); // left to right.
            if (++t > b)
                break;
            for (int i = t; i <= b; i++)
                res.add(matrix[i][r]); // top to bottom.
            if (l > --r)
                break;
            for (int i = r; i >= l; i--)
                res.add(matrix[b][i]); // right to left.
            if (t > --b)
                break;
            for (int i = b; i >= t; i--)
                res.add(matrix[i][l]); // bottom to top.
            if (++l > r)
                break;
        }
        return res;
    }

    // 0ms
    public List<Integer> spiralOrder2(int[][] matrix) {
//    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            //上面左到右
            for (int j = left; j <= right; ++j) {
                result.add(matrix[top][j]);
            }
            // 右边上到下
            for (int i = top + 1; i <= bottom; ++i) {
                result.add(matrix[i][right]);
            }
            // 如果上下不为一行，说明还有底，要做右到左
            if (top != bottom) {
                for (int j = right - 1; j >= left; --j) {
                    result.add(matrix[bottom][j]);
                }
            }
            // 如果左右不为一列，说明还有左边，要下到上。
            if (left != right) {
                for (int i = bottom - 1; i > top; --i) {
                    result.add(matrix[i][left]);
                }
            }
            // 转完一圈
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix s = new SpiralMatrix();
        s.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
//        s.spiralOrder(new int[][]{{1}});
    }
}
