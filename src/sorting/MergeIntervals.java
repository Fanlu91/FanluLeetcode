package sorting;
// Source : https://leetcode.com/problems/merge-intervals/
// Id     : 56
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/13
// Topic  : Sorting
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 92.20% 55.70%

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals {
    // 63.05% 8ms 65.95%
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new LinkedList<>();
        if (intervals == null || intervals.length == 0)
            return res.toArray(new int[0][]);
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return res.toArray(new int[0][]);
    }

    // 92.20% 7ms 55.70%

    /**
     * 解法更清晰一些。
     * 假设 第一个区间的起始位置 ≤ 第二个区间的起始位置
     * 重合的情况抽象则有3种
     *
     * @param intervals
     * @return
     */
    public int[][] merge1(int[][] intervals) {
//    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[][] res = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) { // 通过排序，第一个区间的起始位置 ≤ 第二个区间的起始位置
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }


    // 6ms
    // practice
    public int[][] merge2(int[][] intervals) {
//    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 1)
            return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                if (intervals[i][1] > intervals[i + 1][1])
                    intervals[i + 1][1] = intervals[i][1];
                n--;
            }
        }

        int[][] res = new int[n][2];
        int j = 0;
        for (int i = 0; i < intervals.length; i++) {
            res[j][0] = intervals[i][0];
            while (i < intervals.length - 1 && intervals[i][1] >= intervals[i + 1][0])
                i++;
            res[j][1] = intervals[i][1];
            j++;
        }
        return res;
    }
}