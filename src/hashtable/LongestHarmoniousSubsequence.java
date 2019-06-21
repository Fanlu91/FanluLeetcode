package hashtable;

import java.util.*;

// Source : https://leetcode.com/problems/longest-harmonious-subsequence/
// Id     : 594
// Author : Fanlu Hai
// Date   : 2018-06-20
// Topic  : Hashtable
// Level  : Easy+
// Other  :
// Tips   :
// Result : 100.00% 13.99%
public class LongestHarmoniousSubsequence {
    // 5.70% 110 ms 19.33%
    public int findLHSOrigin(int[] nums) {
        if (nums.length < 2)
            return 0;

        Map<Double, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i] - 0.5, map.getOrDefault(nums[i] - 0.5, 0) + 1);
            map.put(nums[i] + 0.5, map.getOrDefault(nums[i] + 0.5, 0) + 1);
            set.add(nums[i]);
        }

        int ans = 0;
        for (Double d : map.keySet()) {
//            System.out.println(d +" "+ map.get(d));
            if (set.contains((int) (d - 0.5)) && set.contains((int) (d + 0.5))) {
                ans = ans < map.get(d) ? map.get(d) : ans;
            }
        }
        return ans;
    }

    //! Memory Limit Exceeded
    public int findLHSMemoryLimitExceeded(int[] nums) {
        if (nums.length < 2)
            return 0;
        int[] array = new int[Integer.MAX_VALUE];
        for (int i = 0; i < nums.length; i++) {
            array[i]++;
        }
        int ans = 0;
        for (int i = 0; i < nums.length - 1; ) {
            if (nums[i] == 0) {
                i++;
                continue;
            }
            if (nums[i + 1] == 0) {
                i += 2;
                continue;
            }
            ans = ans < nums[i] + nums[i + 1] ? nums[i] + nums[i + 1] : ans;
            i++;
        }
        return ans;
    }

    // 58.14% 31 ms 99.00%
    public int findLHSWithHashtable(int[] nums) {
        if (nums.length < 2)
            return 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.containsKey(key - 1))
                ans = ans < map.get(key) + map.get(key - 1) ? map.get(key) + map.get(key - 1) : ans;
            if (map.containsKey(key + 1))
                ans = ans < map.get(key) + map.get(key + 1) ? map.get(key) + map.get(key + 1) : ans;
        }
        return ans;
    }

    /*
     * Learned from leetcode submission, one way to do this is using hash like what I did above,
     * another way usually involves do things with sorted array.
     *
     * */
    // 100.00% 15ms, 13.99%
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int prevCount = 0, ans = 0, count;
        for (int i = 0; i < nums.length; i++) {
            count = 1;
            // if ith is equal to (i-1)th, look back
            if (i > 0 && nums[i] - nums[i - 1] == 1) {
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++; // only count current element, every element will have its own check
                    i++;
                }
                ans = Math.max(ans, count + prevCount);
                prevCount = count;
            } else {
                // look forward
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    count++; // only count current element, every element will have its own check
                    i++;
                }
                prevCount = count;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        LongestHarmoniousSubsequence l = new LongestHarmoniousSubsequence();
        System.out.println(l.findLHS(new int[]{1, 1, 1, 2, 2, 2}));
    }
}
