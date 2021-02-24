package permutationcombination;
// Source : https://leetcode.com/problems/combination-sum-ii/
// Id     : 40
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/20
// Topic  : combination
// Level  : Medium
// Other  :
// Tips   :
// Links  : 39
// Result : 40.55% 53.43%


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumII {

    // 40.55% 5ms 75.91%
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        Arrays.sort(candidates);
        combination(candidates, target, 0, false, ans, new LinkedList<>());
        return ans;
    }

    private void combination(int[] candidates, int target, int start, boolean pre, List<List<Integer>> res, List<Integer> path) {
        if (target == 0) {
            res.add(new LinkedList<Integer>(path));
            return;
        }
        if (start == candidates.length)
            return;
//        System.out.println(path + " t " + target + " s " + candidates[start]);
        combination(candidates, target, start + 1, false, res, path);
        /**
         * 假设有两个相同的数
         * 1 1
         * 1 0
         * 0 1 // 下面为了剪掉这种情况
         * 0 0
         */
        if (start != 0 && !pre && candidates[start] == candidates[start - 1])
            return;
        if (target - candidates[start] >= 0) {
            path.add(candidates[start]);
            combination(candidates, target - candidates[start], start + 1, true, res, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumII c = new CombinationSumII();
        System.out.println(c.combinationSum2(new int[]{1, 1, 2, 3}, 3));
    }
}