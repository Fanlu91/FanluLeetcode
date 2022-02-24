package heap;
// Source : https://leetcode.com/problems/kth-largest-element-in-an-array/
// Id     : 215
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Heap
// Level  : Medium
// Other  :
// Tips   :
// Result : 99.87% 5.15%

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    // 61.49% 6ms 5.15%

    /**
     * heap
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

//        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
       /* PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (x < y) return 1;
                if (x > y) return -1;
                return 0;
            }
        });*/
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k)
                queue.poll();
        }
        return queue.peek();
    }

    /**
     * quick select (quick sort)
     * you can use BFPR to push even harder.
     *
     * @param nums
     * @param k
     * @return
     */
    // 99.87% 1ms 5.15%
    public int findKthLargestQuickSelect(int[] nums, int k) {
//    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, k);
    }

    private int quickSort(int[] nums, int l, int r, int k) {
        if (l >= r)
            return nums[l];
        int i = l - 1, j = r + 1, pivot = nums[l + r >> 1];
        while (i < j) {
            do i++; while (nums[i] > pivot);
            do j--; while (nums[j] < pivot);
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        if (j >= k - 1)
            return quickSort(nums, l, j, k);
        return quickSort(nums, j + 1, r, k);
    }


    // heap
    // 4 ms
    public int findKthLargest2(int[] nums, int k) {
//    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1)
            return nums[0];
        if (k == 1) {
            int res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                res = Math.max(res, nums[i]);
            }
            return res;
        }

        // 建立k个元素的小顶堆 i 2*i+1 2*i+2 (i+1)/2
        // 自下而上堆化
        heapify(nums, k);
        // 将nums中剩余元素插入堆中
        for (int i = k; i < nums.length; i++) {
            // 自上而下堆化
            if (nums[i] > nums[0]) {
                nums[0] = nums[i];
                heapify(nums, k);
            }
        }

        // 返回堆顶元素
        return nums[0];
    }

    private void heapify(int[] nums, int k) {
        for (int i = k / 2 - 1; i >= 0; i--) {
            int smaller = -1;
            if (2 * i + 1 == k - 1) { // 只有左子树的情况
                smaller = 2 * i + 1;
            } else {
                smaller = nums[2 * i + 1] < nums[2 * i + 2] ? 2 * i + 1 : 2 * i + 2;
            }
            if (nums[i] > nums[smaller])
                swap(nums, i, smaller);
            // System.out.println(i + " " + Arrays.toString(nums));
        }
    }


    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    // 1 ms
    // quick sort practice
    public int findKthLargest3(int[] nums, int k) {
//    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1)
            return nums[0];
        if (k == 1) {
            int res = nums[0];
            for (int i = 1; i < nums.length; i++)
                res = Math.max(res, nums[i]);
            return res;
        }
        return quickSortK(nums, 0, nums.length - 1, k);
    }

    private int quickSortK(int[] nums, int p, int r, int k) {
        if (p > r) return -1;
        int q = partition(nums, p, r);
        int largeCount = 1 + q - p;
        if (largeCount == k) {
            return nums[q];
        } else if (largeCount < k) {
            return quickSort(nums, q + 1, r, k - largeCount);
        } else {
            return quickSort(nums, p, q - 1, k);
        }
    }

    private int partition(int[] a, int p, int r) {
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (a[j] > a[r]) { // > 降序
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }
}
