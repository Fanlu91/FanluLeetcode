package twopointers.slidingwindow;

// Source : https://leetcode.com/problems/sliding-window-maximum/
// Id     : 239
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-03
// Topic  : Sliding Window
// Level  : Hard
// Other  :
// Tips   :
// Result : 39.53% 6.67%

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    // TLE
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1)
            return nums;
        int turn = nums.length - k + 1;
        int[] res = new int[turn];
        for (int i = 0; i < turn; i++) {
            res[i] = getMax(nums, i, k);
        }
        return res;
    }

    private int getMax(int[] nums, int left, int k) {
        int max = nums[left];
        for (int i = 0; i < k - 1; i++) {
            left++;
            max = Math.max(max, nums[left]);
        }
        return max;
    }

    // 39.53% 18 ms 6.67%
    public int[] maxSlidingWindow1(int[] nums, int k) {
//    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1)
            return nums;
        int[] res = new int[nums.length - k + 1];

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // 从尾部开始移除比新加入元素小的元素
            while (!queue.isEmpty() && nums[queue.getLast()] < nums[i])
                queue.removeLast();
            // 将新加入元素添加到双端队列的尾部
            queue.addLast(i);
            // 如过窗口外的元素仍然在双端队列中，将其移除
            if (queue.getFirst() == i - k)
                queue.removeFirst();
            // 将头部元素即当前最大元素对应的数字放入结果数组
            if (i - k + 1 >= 0)
                res[i - k + 1] = nums[queue.getFirst()];
        }

        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        slidingWindowMaximum.maxSlidingWindow1(new int[]{7, 2, 4}, 2);
    }
}
