package array;

import java.util.*;

// Source : https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// Id     : 448
// Author : Fanlu Hai
// Date   : 2018-06-10
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   : ArrayList<T> list = new ArrayList<>(aSet);
// Result : 99.93% 61.97%
public class FindAllNumbersDisappearedInAnArray {

    // 11.92%  27.11%
    public List<Integer> findDisappearedNumbersTooSlow(int[] nums) {
        Set<Integer> ans = new HashSet<>();
        for (int i = 1; i < nums.length; i++) {
            ans.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            ans.remove(nums[i]);
        }
        return new LinkedList<>(ans);

    }

    //99.93% 4 ms 61.97%
    public List<Integer> findDisappearedNumbers(int[] nums) {
        boolean[] used = new boolean[nums.length + 1];

        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            used[nums[i]] = true;
        }

        for (int i = 1; i < used.length; i++) {
            if (!used[i]) {
                list.add(i);
            }
        }
        return list;

    }
}
