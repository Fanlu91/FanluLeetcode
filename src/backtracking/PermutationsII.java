package backtracking;

// Source : https://leetcode.com/problems/permutations-ii/
// Id     : 47
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Medium
// Date   : 2019-07-13
// Other  :
// Tips   : 排序，剪枝
// Result : 100.00% 17.50%

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {
    List<List<Integer>> ansList = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(nums, new ArrayList<>(), visited);
        return ansList;
    }

    private void backtracking(int[] nums, List<Integer> tmp, boolean[] visited) {
        if (tmp.size() == nums.length) {
            ansList.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            // !visited[i - 1] 说明已经遍历完被撤销了状态记录
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            tmp.add(nums[i]);
            backtracking(nums, tmp, visited);

            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

}
