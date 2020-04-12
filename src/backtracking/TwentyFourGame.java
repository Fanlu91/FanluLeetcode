package backtracking;

// Source : https://leetcode.com/problems/24-game/
// Id     : 679
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-04-11
// Topic  : Backtracking
// Level  : Hard
// Other  :
// Tips   :
// Result : 93.33% 14.29%

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwentyFourGame {
    List<List<Double>> permutationList = new ArrayList<>();

    // 9ms at best
    public boolean judgePoint24(int[] nums) {
        permuteUnique(nums);
        for (List<Double> permutation : permutationList) {
            if (compute(permutation))
                return true;
        }
        return false;
    }

    private boolean compute(List<Double> nums) {
        if (nums.size() == 1)
            return Math.abs(nums.get(0) - 24) <= 1e-6;

        for (int i = 0; i < nums.size() - 1; i++) {
            // compute possible result from + - * /
            List<Double> tmpResultList = new ArrayList<>();
            tmpResultList.add(nums.get(i) + nums.get(i + 1));
            tmpResultList.add(nums.get(i) - nums.get(i + 1));
            tmpResultList.add(nums.get(i) * nums.get(i + 1));
            if (nums.get(i + 1) != 0) {
                tmpResultList.add(nums.get(i) / nums.get(i + 1));
            }

            // replace nums[i] and nums[i+1] with the result
            // continue with the new list
            for (Double newNum : tmpResultList) {
                List<Double> newList = new ArrayList<>(nums);
                newList.set(i, newNum);
                newList.remove(i + 1);
                if (compute(newList))
                    return true;
            }
        }
        return false;
    }


    public List<List<Double>> permuteUnique(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(nums, new ArrayList<>(), visited);
        return permutationList;
    }

    private void backtracking(int[] nums, List<Double> tmp, boolean[] visited) {
        if (tmp.size() == nums.length) {
            permutationList.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            // !visited[i - 1] 说明已经遍历完被撤销了状态记录
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            tmp.add((double) nums[i]);
            backtracking(nums, tmp, visited);

            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }


    /**
     * learned from leetcode submission
     */
    //93.33% 1ms 14.29%
    public boolean judgePoint24Faster(int[] nums) {
//    public boolean judgePoint24(int[] nums) {
        double[] nums1 = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
        }
        return helper(nums1, 4);
    }

    private boolean helper(double[] nums, int n) {
        if (n == 1)
            return Math.abs(nums[0] - 24) <= 1e-6;

        double newNums[] = new double[4];

        for (int left = 0; left < n - 1; left++) {
            for (int right = left + 1; right < n; right++) {
                int m = 0;
                // put all other num at the beginning first
                for (int k = 0; k < n; k++) {
                    if (k != left && k != right)
                        newNums[m++] = nums[k];
                }

                // put newNum in after + = * /
                newNums[m] = nums[left] + nums[right];
                if (helper(newNums, m + 1))
                    return true;

                newNums[m] = nums[left] - nums[right];
                if (helper(newNums, m + 1))
                    return true;
                newNums[m] = nums[right] - nums[left];
                if (helper(newNums, m + 1))
                    return true;

                newNums[m] = nums[left] * nums[right];
                if (helper(newNums, m + 1))
                    return true;

                if (nums[right] != 0) {
                    newNums[m] = nums[left] / nums[right];
                    if (helper(newNums, m + 1))
                        return true;
                }
                if (nums[left] != 0) {
                    newNums[m] = nums[right] / nums[left];
                    if (helper(newNums, m + 1))
                        return true;
                }
            }
        }
        return false;
    }
}
