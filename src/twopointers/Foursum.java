//package twopointers;
//
//// Source : https://leetcode.com/problems/4sum/
//// Id     : 18
//// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
//// Date   : 2020-01-01
//// Topic  : Two Pointers
//// Level  : medium
//// Other  :
//// Tips   :
//// Result :
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//public class Foursum {
//
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        if (nums.length < 4)
//            return new LinkedList<>();
//        Arrays.sort(nums);
//
//        List<List<Integer>> ans = new LinkedList<>();
//        int i = 0;
//        while (nums[i] < target) {
//            int l = i + 1, r = nums.length - 1;
//            while (l < r) {
//                if (nums[i] + nums[l] + nums[r] == target) {
//                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
//                    while (nums[++l] == nums[l - 1]) {
//                        if (l == r)
//                            break;
//                    }
//                    while (nums[--r] == nums[r + 1]) {
//                        if (l == r)
//                            break;
//                    }
//                } else if (nums[i] + nums[l] + nums[r] < 0) {
//                    l++;
//                } else {
//                    r--;
//                }
//            }
//            while (++i < nums.length - 2 && nums[i - 1] == nums[i])
//                ;// for ++i to be executed
//
//            if (i == nums.length - 1)
//                return ans;
//        }
//
//        if (i < nums.length - 2 && nums[i] + nums[i + 1] + nums[i + 2] == 0)
//            ans.add(Arrays.asList(0, 0, 0));
//        return ans;
//    }
//}
