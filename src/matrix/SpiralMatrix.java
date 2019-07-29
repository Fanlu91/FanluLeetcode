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

import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {
    // 0 r , 1 d , 2 l , 3 u
    int direction = 0;

    // 100.00% 99.54%
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new LinkedList<>();
        if (matrix.length == 0)
            return res;

        int row = 0, column = 0, preRow = 0, preColumn = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        boolean justTurned = false;
        int size = matrix.length * matrix[0].length;

        for (int i = 0; i < size; ) {
            if (column == matrix[0].length || column == -1 || row == matrix.length || row == -1 || visited[row][column]) {
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

    public static void main(String[] args) {
        SpiralMatrix s = new SpiralMatrix();
//        s.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        s.spiralOrder(new int[][]{{1}});
    }
}
