package orderedmap;
// Source : https://leetcode.com/problems/contains-duplicate-iii/
// Id     : 220
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/28
// Topic  : Ordered Map
// Level  : Medium
// Other  :
// Tips   :
// Links  : 219
// Result : 90.80% 5.28%

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ContainsDuplicateIII {

    // 36.80% 23 ms 88.38%
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer minBiggerNum = treeSet.ceiling(nums[i]); // 返回在这个集合中大于或者等于给定元素的最小元素
            if (minBiggerNum != null && minBiggerNum <= nums[i] + t)
                return true;

            // Find the predecessor of current element
            Integer maxSmallerNum = treeSet.floor(nums[i]); // 返回在这个集合中小于或者等于给定元素的最大元素
            if (maxSmallerNum != null && nums[i] <= maxSmallerNum + t)
                return true;

            treeSet.add(nums[i]);
            if (treeSet.size() > k) {
                treeSet.remove(nums[i - k]);
            }
        }
        return false;
    }


    /**
     * bucket sort like approach
     * @param nums
     * @param k
     * @param t
     * @return
     */
    // 90.80% 12 ms 5.28%
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
//    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0)
            return false;
        Map<Long, Long> bucket = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket is empty, each bucket may contain at most one element
            if (bucket.containsKey(m))
                return true;
            // check the nei***or buckets for almost duplicate
            if (bucket.containsKey(m - 1) && Math.abs(nums[i] - bucket.get(m - 1)) < w)
                return true;
            if (bucket.containsKey(m + 1) && Math.abs(nums[i] - bucket.get(m + 1)) < w)
                return true;
            // now bucket is empty and no almost duplicate in neighbour buckets
            bucket.put(m, (long) nums[i]);
            if (i >= k)
                bucket.remove(getID(nums[i - k], w));
        }
        return false;
    }

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0`  but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

}
