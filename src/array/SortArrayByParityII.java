package array;
// Source : https://leetcode.com/problems/sort-array-by-parity-ii/
// Id     : 922
// Author : Fanlu Hai
// Date   : 2018-04-15
// Topic  : Array
// Result : 99.72% 99.43%


class SortArrayByParityII {

    //60.45% 3 ms 99.32%  40.4 MB
    public int[] sortArrayByParityIIOrigin(int[] A) {
        int i = 0;
        int j = 1;
        // below code hasn't take full advantage of half odd half even situation
        // if do count before swap which can be improved under above condition
        // see improved code in the next function
        while (true) {
            if (i >= A.length || j >= A.length) {
                return A;
            }
            if (A[i] % 2 == 1 && A[j] % 2 == 0) {
                swapNumInArray(i, j, A);
            }
            if (A[i] % 2 == 0) {
                i += 2;
                //in order to make sure a count is performed
                continue;
            }
            if (A[j] % 2 == 1) {
                j += 2;
                //in order to make sure a count is performed
                continue;
            }
        }
    }

    public void swapNumInArray(int firstIndex, int secondIndex, int[] array) {
        int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }

    // 99.72% 99.43%
    public int[] sortArrayByParityII(int[] A) {
        int i = 0;
        int j = 1;
        int tmp;
        while (i < A.length) {
            if ((A[i] & 0x1) == 1) {
                while ((A[j] & 0x1) == 1) {
                    j += 2;
                }
                tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
            i += 2;
        }
        return A;
    }
}