package permutationcombination;
// Source : https://leetcode.com/problems/combinations/
// Id     : 77
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/19
// Topic  : Backtracking
// Level  : Medium
// Other  :
// Tips   :
// Links  : Must
// Result : 93.17% 74.09%

import java.util.*;

public class Combinations {
    /*
    搞成了permutation
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> list = new ArrayList<>(n);
        for (int i = 1; i < n + 1; i++) {
            list.add(i);
        }
        backtracking(list, new ArrayList<Integer>(), k, ans);

        return ans;
    }

    private void backtracking(List<Integer> list, List<Integer> tmp, int k, List<List<Integer>> ans) {

        if (tmp.size() == k) {
            ans.add(new ArrayList<>(tmp));
            return;
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            int tmpInt = list.get(i);
            tmp.add(tmpInt);
            list.remove(i);
            backtracking(list, tmp, k, ans);
            list.add(i,tmpInt);
            tmp.remove(tmp.size() - 1);
        }
    }*/

    // 72.41% 3 ms 73.45%
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int startIndex, Deque<Integer> tmp, List<List<Integer>> res) {
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if (tmp.size() + (n + 1 - startIndex) < k) // 已经无法满足由k个元素组成的条件
            return;

        for (int i = startIndex; i <= n; i++) {
            tmp.addLast(i);
            dfs(n, k, i + 1, tmp, res);
            tmp.removeLast();
        }
    }

    // 93.17% 2ms 74.09%
    public List<List<Integer>> combine1(int n, int k) {
//    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int startIndex = 1;
        backTracking(path, result, n, k, startIndex);
        return result;
    }

    private void backTracking(List<Integer> path, List<List<Integer>> result, int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n && (n - i + 1) + path.size() >= k; i++) {
            path.add(i);
            backTracking(path, result, n, k, i + 1);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        System.out.println(c.combine(3, 2));
    }
}