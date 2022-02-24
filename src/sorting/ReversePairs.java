package sorting;
// Source : https://leetcode.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
// Id     : Offer51
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/21
// Topic  : sorting 
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 94.28% 14.67%

public class ReversePairs {

    // TLE
    public int reversePairs(int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            boolean finished = true;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    count++;
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    finished = false;
                }
            }
            if (finished)
                break;
        }
        return count;
    }

    int count = 0;
    // 29ms
    public int reversePairs1(int[] a) {
//    public int reversePairs(int[] a) {
        mergeSort(a, 0, a.length - 1);
        return count;
    }

    private void mergeSort(int[] a, int l, int r) {
        if (l >= r)
            return;
        int m = l + (r - l) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);
        if (a[m] > a[m + 1]) // 可选剪枝
            merge(a, l, m, r);
    }

    private void merge(int[] a, int left, int mid, int right) {
        int i = left, j = mid + 1, k = 0;
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) { // 这里的等于决定了merge是稳定的
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid) {
            tmp[k++] = a[i++];
            count++;
        }
        while (j <= right) {
            tmp[k++] = a[j++];
        }
        for (i = 0; i <= right - left; i++) {
            a[i + left] = tmp[i];
        }
    }

}