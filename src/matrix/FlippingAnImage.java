package matrix;
// Source : https://leetcode.com/problems/flipping-an-image/
// Id     : 832
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-04
// Topic  : Matrix
// Other  :
// Tips   : int a[][] = new int[3][4]; //3行 4列
//          int lenY = a.length; // 3
// Result : 100.00% 99.95%

public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] A) {

        for (int i = 0; i < A.length; i++) {

            int head = 0, tail = A[0].length - 1;
            while (tail > head) {
                if (A[i][tail] == A[i][head]) {
                    A[i][tail] ^= 1;
                    A[i][head] ^= 1;
                }
                tail--;
                head++;
            }
            if (tail == head)
                A[i][tail] ^= 1;
        }
        return A;
    }
}
