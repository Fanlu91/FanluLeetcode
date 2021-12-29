package twopointers;

// Source : https://leetcode.com/problems/3sum/
// Id     : 15
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : Medium+
// Other  :
// Tips   :
// Result : 98.79% 91.35%

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    // 98.79% 22 ms 91.35%
    public List<List<Integer>> threeSum1(int[] nums) {
        if (nums.length < 3)
            return new LinkedList<>();
        Arrays.sort(nums);

        List<List<Integer>> ans = new LinkedList<>();
        int i = 0;
        while (nums[i] < 0) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    ans.add(list);
                    l++;
                    while (nums[l] == nums[l - 1]) {
                        if (l == r)
                            break;
                        l++;
                    }
                    r--;
                    while (nums[r] == nums[r + 1]) {
                        if (l == r)
                            break;
                        r--;
                    }
                } else if (nums[i] + nums[l] + nums[r] < 0) {
                    l++;
                } else {
                    r--;
                }
            }
            i++;
            while (i < nums.length - 2 && nums[i - 1] == nums[i])
                i++;
            if (i == nums.length - 1)
                return ans;
        }

        if (i < nums.length - 2 && nums[i] + nums[i + 1] + nums[i + 2] == 0) {
            List<Integer> list = new LinkedList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            ans.add(list);
        }
        return ans;
    }

    /**
     * use Arrays.asList(nums[i],nums[l],nums[r])
     * use ++i instead of what we did above
     * use ; to indicate doing nothing
     */
    public List<List<Integer>> threeSum2(int[] nums) {
//    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return new LinkedList<>();
        Arrays.sort(nums);

        List<List<Integer>> ans = new LinkedList<>();
        int i = 0;
        while (nums[i] < 0) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (nums[++l] == nums[l - 1]) {
                        if (l == r)
                            break;
                    }
                    while (nums[--r] == nums[r + 1]) {
                        if (l == r)
                            break;
                    }
                } else if (nums[i] + nums[l] + nums[r] < 0) {
                    l++;
                } else {
                    r--;
                }
            }
            while (++i < nums.length - 2 && nums[i - 1] == nums[i])
                ;// for ++i to be executed

            if (i == nums.length - 1)
                return ans;
        }

        if (i < nums.length - 2 && nums[i] + nums[i + 1] + nums[i + 2] == 0)
            ans.add(Arrays.asList(0, 0, 0));
        return ans;
    }

    // 更易读易的写法
    // 详细解释请见题解
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return new LinkedList<>();
        Arrays.sort(nums);
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        // 若修改题目，sum不为0，此方法通用
        int sum = 0;

        for (int a = 0; a < nums.length - 2; a++) {
            // nums[a] 大于sum，结束
            if (nums[a] > sum)
                return ans;
            // a需要和上一次枚举的数不相同
            if (a != 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            int target = sum - nums[a];

            // 枚举b和c
            // c对应的指针初始指向数组的最右端
            int c = nums.length - 1;
            for (int b = a + 1; b < nums.length - 1; b++) {
                // 需要和上一次枚举的数不相同
                if (b != a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (b < c && nums[b] + nums[c] > target) {
                    c--;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (b == c) {
                    break;
                }
                if (nums[b] + nums[c] == target) {
                    ans.add(Arrays.asList(nums[a], nums[b], nums[c]));
                }
            }
        }
        return ans;
    }

}
