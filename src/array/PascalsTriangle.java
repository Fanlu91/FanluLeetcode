package array;

// Source : https://leetcode.com/problems/pascals-triangle/
// Id     : 118
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-01
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 77.33% 9.09%

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    // 77.33% 1ms 9.09%
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0)
            return  new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>(numRows);
        List<Integer> list = new ArrayList<>(1);
        list.add(1);
        ans.add(list);
        for (int i = 1; i < numRows; i++) {
            List<Integer> newList = getNextLevel(list);
            ans.add(newList);
            list = newList;
        }
        return ans;
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
}
