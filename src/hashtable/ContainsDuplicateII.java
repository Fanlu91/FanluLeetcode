package hashtable;
// Source : https://leetcode.com/problems/contains-duplicate-ii/
// Id     : 219
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/28
// Topic  : Hashtable
// Level  : Easy-
// Other  :
// Tips   :
// Links  :
// Result : 95.81% 5.72%

import java.util.*;

public class ContainsDuplicateII {
    /**
     * 因为是顺序向后遍历的，我想多了 不需要用list存index
     *
     * @param nums
     * @param k
     * @return
     */
    // 24.07% 18 ms 5.72%
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                List<Integer> list = map.get(nums[i]);
                for (int index : list) {
                    if (Math.abs(index - i) <= k)
                        return true;
                }
                list.add(i);
                map.put(nums[i], list);
            } else {
                map.put(nums[i], new ArrayList<Integer>(Arrays.asList(i)));
            }
        }
        return false;
    }

    // 95.81% 8ms
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
//    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        if (k == 0)
            return false;
        if (n <= 0)
            return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.get(nums[i]) != null && (i - map.get(nums[i])) <= k)
                return true;
            map.put(nums[i], i);
        }
        return false;
    }
}