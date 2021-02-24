package permutationcombination;
// Source : https://leetcode.com/problems/combination-sum-iii/
// Id     : 216
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/20
// Topic  : Backtracking
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 46.43% 42.73%

import java.util.LinkedList;
import java.util.List;

public class CombinationSumIII {
    // 42.73% 1ms
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new LinkedList<>();
        backtracking(k, n, 1, new LinkedList<>(), ans);
        return ans;
    }

    private void backtracking(int k, int n, int start, LinkedList<Integer> tmp, List<List<Integer>> ans) {
        if (tmp.size() == k & n == 0) {
            ans.add(new LinkedList<>(tmp));
            return;
        }
        if (n < start || tmp.size() > k)
            return;
        for (int i = start; i < 10; i++) {
            tmp.add(i);
            backtracking(k, n - i, i + 1, tmp, ans);
            tmp.remove(tmp.size() - 1);
        }
    }
}