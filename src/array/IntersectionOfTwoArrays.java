package array;

// Source : https://leetcode.com/problems/intersection-of-two-arrays/
// Id     : 349
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100% 50%

import java.util.*;


public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();
        for (int i : nums1)
            set.add(i);
        for (int i : nums2) {
            if (set.contains(i))
                intersection.add(i);
        }
        int[] ans = new int[intersection.size()];
        int i = 0;
        for (int num : intersection) {
            ans[i++] = num;
        }
        return ans;

    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }

    public int[] intersection3(int[] nums1, int[] nums2) {
//    public int[] intersection(int[] nums1, int[] nums2) {
        // 作为临时的交集
        int[] container = nums1.length > nums2.length ?
                new int[nums1.length] : new int[nums2.length];

        boolean[] appeared = new boolean[2020];
        for (int i = 0; i < nums1.length; i++) {
            appeared[nums1[i]] = true;      // 标记nums1的各个元素为出现过
        }

        int len = 0;                          // 定位指针
        for (int i = 0; i < nums2.length; i++) {
            if (appeared[nums2[i]] == true) {
                container[len++] = nums2[i];
                appeared[nums2[i]] = false; //标记其状态为使用过
            }
        }

        int[] resArr = new int[len];          //len-0 == 为交集的元素个数
        for (int i = 0; i < len; i++) {
            resArr[i] = container[i];
        }
        return resArr;
    }
}
