package findpattern;
// Source : https://leetcode.com/problems/rotate-image/
// Id     : 48
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/28
// Topic  : findpattern 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%

public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // size 随着遍历深入会不断减小
        int i = 0, j = 0, size = n - 1;
        while (i < n / 2) {
            // 画图得出
            int i1 = i, j1 = j,
                    i2 = j1, j2 = n - 1 - i1,
                    i3 = j2, j3 = n - 1 - i2,
                    i4 = j3, j4 = n - 1 - i3;
            // System.out.println(size + " "+i1 + " " + j1 + " " + i2 + " " + j2 + " " + i3 + " " + j3 + " " + i4 + " " + j4);
            swap(matrix, i1, j1, i2, j2, i3, j3, i4, j4);
            if (j + 1 >= size) {
                i++;
                j = i;
                size--;
            } else {
                j++;
            }
        }
    }

    private void swap(int[][] a, int i1, int j1, int i2, int j2, int i3, int j3, int i4, int j4) {
        int tmp = a[i1][j1];
        a[i1][j1] = a[i4][j4];
        a[i4][j4] = a[i3][j3];
        a[i3][j3] = a[i2][j2];
        a[i2][j2] = tmp;
    }
}