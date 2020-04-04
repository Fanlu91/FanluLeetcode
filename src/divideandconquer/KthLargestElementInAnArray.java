package divideandconquer;
// Source : https://leetcode.com/problems/kth-largest-element-in-an-array/
// Id     : 215
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Divide & Conquer, Heap
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
}
