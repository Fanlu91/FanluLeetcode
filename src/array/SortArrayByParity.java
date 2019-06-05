package array;
// Source : https://leetcode.com/problems/sort-array-by-parity/
// Id     : 905
// Author : Fanlu Hai
// Date   : 2018-04-15
// Topic  : Array
// Result : 100.00% 96.91%


class SortArrayByParity {

    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length - 1;
        int tmp;

        while (j > i) {
            // do swap
            if (A[i] % 2 == 1 && A[j] % 2 == 0) {
                tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            if (A[i] % 2 == 0) {
                i++;
                //in order to make sure a count is performed
                continue;
            } else if (A[j] % 2 == 1) {
                j--;
                //in order to make sure a count is performed
                continue;
            }
        }
        return A;
    }
}