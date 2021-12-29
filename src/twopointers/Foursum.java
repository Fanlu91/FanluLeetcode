package twopointers;

// Source : https://leetcode.com/problems/4sum/
// Id     : 18
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : medium
// Other  :
// Tips   :
// Result : 88.88% 68.14%

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Foursum {

    // 18 ms
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4)
            return new LinkedList<>();
        Arrays.sort(nums);

        List<List<Integer>> ans = new LinkedList<>();
        for (int a = 0; a < nums.length - 3; a++) {
            if (a > 0 && nums[a] == nums[a - 1])
                continue;
            for (int b = a + 1; b < nums.length - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1])
                    continue;
                int tmp = target - nums[a] - nums[b];
                int d = nums.length - 1;
                for (int c = b + 1; c < nums.length - 1; c++) {
                    if (c > b + 1 && nums[c] == nums[c - 1])
                        continue;
                    while (c < d && nums[c] + nums[d] > tmp) {
                        d--;
                    }
                    if (c < d && nums[c] + nums[d] == tmp) {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    }
                }
            }
        }
        return ans;
    }

    // 增加一些剪枝
    // 4ms
    public List<List<Integer>> fourSum2(int[] nums, int target) {
//    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4)
            return new LinkedList<>();
        Arrays.sort(nums);

        List<List<Integer>> ans = new LinkedList<>();
        for (int a = 0; a < nums.length - 3; a++) {
            // 如果这四数之和大于target, 往后算也必大于target
            if ((long) nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target)
                return ans;
            // 下面这四数之和小于target, 则nums[i]小了
            if ((long) nums[a] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                continue;

            if (a > 0 && nums[a] == nums[a - 1])
                continue;
            for (int b = a + 1; b < nums.length - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1])
                    continue;
                int tmp = target - nums[a] - nums[b];
                int d = nums.length - 1;
                for (int c = b + 1; c < nums.length - 1; c++) {
                    if (c > b + 1 && nums[c] == nums[c - 1])
                        continue;
                    while (c < d && nums[c] + nums[d] > tmp) {
                        d--;
                    }
                    if (c < d && nums[c] + nums[d] == tmp) {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    }
                }
            }
        }
        return ans;
    }



    public List<List<Integer>> fourSum3(int[] nums, int target) {
//        public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        int len = nums.length;
        if (nums == null || len < 4) return ans;
        Arrays.sort(nums);

        for (int i = 0; i < len - 3; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 如果这四数之和大于target, 往后算也必大于target
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            // 下面这四数之和小于target, 则nums[i]小了
            if ((long) nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) continue;
            for (int j = i + 1; j < len - 2; j++) {
                // 去重
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                // 如果这四数之和大于target, 往后算也必大于target
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                // 如果这四数之和大于target, 往后算也必大于target
                if ((long) nums[i] + nums[j] + nums[len - 2] + nums[len - 1] < target) continue;
                // 接下来就是熟悉的双指针算法啦
                int L = j + 1;
                int R = len - 1;
                while (L < R) {
                    int sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) L++;
                        while (L < R && nums[R] == nums[R - 1]) R--;
                        L++;
                        R--;
                    } else if (sum < target) {
                        // 小了就往右移
                        L++;
                    } else {
                        // 大了就往左移
                        R--;
                    }
                }
            }
        }
        return ans;
    }
}
