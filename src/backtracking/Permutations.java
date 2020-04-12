package backtracking;

import java.util.LinkedList;
import java.util.List;

// Source : https://leetcode.com/problems/permutations/
// Id     : 46
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Medium
// Date   : 2019-07-13
// Other  :
// Tips   :
// Result : 77.46% 5.02%
public class Permutations {
    List<List<Integer>> ansList = new LinkedList<>();

    // 77.46% 2ms 5.02%
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        backtracking(nums, new LinkedList<>(), visited);
        return ansList;
    }

    private void backtracking(int[] nums, List<Integer> tmp, boolean[] visited) {
        if (tmp.size() == nums.length) {
            ansList.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            tmp.add(nums[i]);
            backtracking(nums, tmp, visited);

            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

}
