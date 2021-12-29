package hashtable;

// Source : https://leetcode.com/problems/4sum-ii/
// Id     : 454
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : medium
// Other  :
// Tips   :
// Result : 99.35% 53.32%

import java.util.HashMap;
import java.util.Map;

public class FoursumII {

    // 159 ms
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        int ans = 0, n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map1.put(nums1[i] + nums2[j], map1.getOrDefault(nums1[i] + nums2[j], 0) + 1);
                map2.put(nums3[i] + nums4[j], map2.getOrDefault(nums3[i] + nums4[j], 0) + 1);
            }
        }
        for (Integer key : map1.keySet()) {
            if (map2.containsKey(-key)) {
                ans += map1.get(key) * map2.get(-key);
            }
        }
        return ans;
    }

    // 103 ms
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
//    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>(), map2 = new HashMap<>();
        int ans = 0, n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //  map1.put(nums1[i] + nums2[j], map1.getOrDefault(nums1[i] + nums2[j], 0) + 1);
                map.merge(nums1[i] + nums2[j], 1, Integer::sum);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += map.getOrDefault(-nums3[i] - nums4[j], 0);
            }
        }
        return ans;
    }
}
