package permutationcombination;

import java.util.LinkedList;
import java.util.List;

// Source : https://leetcode.com/problems/permutations/
// Id     : 46
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Medium
// Date   : 2019-07-13
// Other  :
// Tips   : All the integers of nums are unique.
// Links  : Must
// Result : 96.75% 5.14%
public class Permutations {

    // 77.46% 2ms 5.02%

    /**
     *
     * 关键点在回溯
     * 回溯的重点在于记录和回退
     *
     * visited 记录的是对每一个分支，剩余的字符还有哪些。
     *
     * 假设为unique可以方便理解，如果有重复需要在此基础上做剪枝（47）
     * 即同样字符 只需要在靠上一级做完全遍历，靠下的会被包含，因此可以据此做剪枝。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        backtracking(nums, new LinkedList<>(), visited, ans);
        return ans;
    }

    private void backtracking(int[] nums, List<Integer> tmp, boolean[] visited, List<List<Integer>> ansList) {
        if (tmp.size() == nums.length) {
            ansList.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            tmp.add(nums[i]);
            backtracking(nums, tmp, visited, ansList);

            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }


    /**
     * All the integers of nums are unique.
     *
     * in place
     *
     * @param nums
     * @return
     */
    // 96.75% 1ms 5.14%
    public List<List<Integer>> permute1(int[] nums) {
//    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        dfs(nums, ans, 0);
        return ans;
    }

    public void dfs(int[] nums, List<List<Integer>> ans, int level) {
        if (level == nums.length - 1) {
            List<Integer> one = new LinkedList<>();
            for (int num : nums) {
                one.add(num);
            }
            ans.add(one);
            return;
        }
        for (int i = level; i < nums.length; i++) {
            swap(nums, i, level);
            dfs(nums, ans, level + 1);
            swap(nums, i, level);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int m = nums[i];
        nums[i] = nums[j];
        nums[j] = m;
    }

}
