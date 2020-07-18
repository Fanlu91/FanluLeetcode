package array;

// Source : https://leetcode.com/problems/pascals-triangle-ii/
// Id     : 119
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-01
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   : 如果知道杨辉三角的数学计算公式可以计算的更快
// Result : 86.51% 5.88%

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {

    // 86.51% 1ms 5.88%
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0)
            return new ArrayList<>();

        List<Integer> list = new ArrayList<>(1);
        list.add(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            List<Integer> newList = getNextLevel(list);
            list = newList;
        }
        return list;
    }

    private List<Integer> getNextLevel(List<Integer> list) {
        List<Integer> newList = new ArrayList<>(list.size() + 1);
        newList.add(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            newList.add(list.get(i - 1) + list.get(i));
        }
        newList.add(list.get(list.size() - 1));
        return newList;
    }

    //
    public List<Integer> getRow1(int rowIndex) {
//    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>(rowIndex);
        for (int i = 0; i <= rowIndex / 2; i++) {
            ans.add(Combination(rowIndex, i));
        }
        for (int i = rowIndex / 2 + 1; i <= rowIndex; i++) {
            ans.add(ans.get(rowIndex - i));
        }
        return ans;
    }

    private int Combination(int N, int k) {
        if (k > N / 2) {
            return Combination(N, N - k);
        }
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (N - k + i) / i;
            //res *= (N - k + i) / i;这么写会出错
        }
        return (int) res;
    }
}
