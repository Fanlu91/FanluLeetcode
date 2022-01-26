package math;
// Source : https://leetcode.com/problems/diving-board-lcci/
// Id     : mst16.11
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/22
// Topic  : math 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%

import java.util.HashSet;
import java.util.Set;

public class DivingBoardLCCI {

    //TLE
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0)
            return new int[0];
        Set<Integer> list = new HashSet<>();
        dfs(shorter, longer, 0, k, list);

        int[] res = new int[list.size()];
        int i = 0;
        for (int x : list)
            res[i++] = x;
        return res;
    }

    private void dfs(int s, int l, int sum, int k, Set<Integer> set) {
        if (k == 0) {
            set.add(sum);
            return;
        }
        dfs(s, l, sum + s, k - 1, set);
        dfs(s, l, sum + l, k - 1, set);
    }

    // 1ms
    public int[] divingBoard1(int shorter, int longer, int k) {
//    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0)
            return new int[0];
        if (shorter == longer)
            return new int[]{longer * k};
        int[] res = new int[k + 1];

        for (int i = 0; i <= k; i++) {
            res[k - i] = k * longer + i * (shorter - longer);
        }
        return res;
    }
}