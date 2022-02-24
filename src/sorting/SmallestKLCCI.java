package sorting;
// Source : https://leetcode.com/problems/smallest-k-lcci/
// Id     : mst17.14
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/19
// Topic  : sorting
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 97.82% 11.40%

import java.util.Arrays;

public class SmallestKLCCI {
    // 2 ms
    public int[] smallestK(int[] arr, int k) {
        if (k >= arr.length)
            return arr;
        if (k == 0)
            return new int[0];
        if (k == 1) {
            int res = arr[0];
            for (int i = 1; i < arr.length; i++) {
                res = Math.min(res, arr[i]);
            }
            return new int[]{res};
        }
        quickSort(arr, 0, arr.length - 1, k);
        return Arrays.copyOf(arr, k);
    }

    public void quickSort(int[] nums, int p, int r, int k) {
        int pivot = partition(nums, p, r);
        int count = pivot - p + 1;
        if (count == k)
            return;
        if (count > k)
            quickSort(nums, p, pivot - 1, k);
        else
            quickSort(nums, pivot + 1, r, k - count);
    }

    private int partition(int[] nums, int p, int r) {
        int l = p - 1;
        for (int i = p; i < r; i++) {
            if (nums[i] < nums[r]) { // 决定是升序还是降序
                l++;
                swap(nums, l, i);
            }
        }
        swap(nums, l + 1, r);
        return l + 1;
    }

    private int partition2(int[] nums, int p, int r) {
        int i = p - 1, j = p;
        while (j < r) {
            if (nums[j] < nums[r]) {
                i++;
                swap(nums, j, i);
            }
            j++;
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
        // System.out.println(Arrays.toString(nums));
    }

    public int[] smallestK1(int[] arr, int k) {
//    public int[] smallestK(int[] arr, int k) {
        partion(arr, 0, arr.length - 1, k);
        return Arrays.copyOf(arr, k);
    }

    private void partion(int[] arr, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int pivot = arr[left];
        int start = left;
        int end = right;
        while (start < end) {
            while (start < end && arr[end] >= pivot) end--;
            arr[start] = arr[end];
            while (start < end && arr[start] <= pivot) start++;
            arr[end] = arr[start];
        }
        arr[start] = pivot;
        int num = start - left + 1;
        if (num == k) {
            return;
        } else if (num < k) {
            partion(arr, start + 1, right, k - num);
        } else {
            partion(arr, left, start - 1, k);
        }
    }
}