package permutationcombination;
// Source : https://leetcode.com/problems/combination-sum/
// Id     : 39
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/20
// Topic  : combination
// Level  : Medium
// Other  : 区分 组合 和 排列
// Tips   :
// Links  : Must
// Result : 77.47% 86.63%

import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    // 77.47% 3 ms 86.63%
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        backtrackingC(candidates, target, 0, new LinkedList<Integer>(), ans);
        return ans;
    }

    private void backtrackingC(int[] candidates, int target, int startIndex, LinkedList<Integer> tmp, List<List<Integer>> ans) {
        System.out.println(target + " " + tmp);
        if (target == 0) {
            ans.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (target < candidates[i])
                continue;
            target -= candidates[i];
            tmp.add(candidates[i]);

            backtrackingC(candidates, target, i, tmp, ans);
            tmp.remove(tmp.size() - 1);
            target += candidates[i];
        }
    }

    // this will give you permutation
    private void backtrackingP(int[] candidates, int target, LinkedList<Integer> tmp, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new LinkedList<>(tmp));
            return;
        }

        for (int candidate : candidates) {
            if (target < candidate)
                continue;
            target -= candidate;
            tmp.add(candidate);
            backtrackingP(candidates, target, tmp, ans);
            tmp.remove(tmp.size() - 1);
            target += candidate;
        }
    }


    /**
     * 不用for 循环
     * <p>
     * 针对每一位，采取 选择/不选择 两种不同策略，靠着向后backtracking来覆盖靠后index的结果
     * 效率区别不大
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        combination(candidates, target, 0, res, new LinkedList<>());
        return res;
    }

    private void combination(int[] candidates, int target, int start, List<List<Integer>> res, List<Integer> path) {
        if (start == candidates.length) return;
        if (target == 0) {
            res.add(new LinkedList<Integer>(path));
            return;
        }
        combination(candidates, target, start + 1, res, path);
        if (target - candidates[start] >= 0) {
            path.add(candidates[start]);
            combination(candidates, target - candidates[start], start, res, path);
            path.remove(path.size() - 1);
        }

    }

}