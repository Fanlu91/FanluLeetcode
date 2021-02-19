package permutationcombination;

// Source : https://leetcode.com/problems/permutations-ii/
// Id     : 47
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Medium
// Date   : 2019-07-13
// Other  :
// Tips   : 排序，剪枝
// Links  : Must
// Result : 100.00% 17.50%

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(nums, new ArrayList<>(), visited, ansList);
        return ansList;
    }

    private void backtracking(int[] nums, List<Integer> tmp, boolean[] visited, List<List<Integer>> ansList) {
        if (tmp.size() == nums.length) {
            ansList.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            // nums[i] == nums[i - 1] &&!visited[i - 1] 说明 i - 1 与 i 相同，
            // 同时 i - 1 及其后续场景已经遍历完成，其中包含了 i 及其后续的场景，因此可以剪枝
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            tmp.add(nums[i]);
            backtracking(nums, tmp, visited, ansList);

            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

}
