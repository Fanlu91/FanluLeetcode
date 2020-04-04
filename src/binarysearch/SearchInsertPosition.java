package binarysearch;

// Source : https://leetcode.com/problems/search-insert-position/
// Id     : 35
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.03%

public class SearchInsertPosition {
    // 100.00% 0ms 5.03%
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        int left = 0, right = nums.length - 1, k;
        while (left < right) {
            k = (left + right) / 2;
            if (nums[k] == target)
                return k;

            if (nums[k] < target) {
                left = k + 1;
            } else {
                right = k - 1;
            }
        }
        return nums[left] < target ? left + 1 : left;
    }

    public static void main(String[] args) {
        SearchInsertPosition s = new SearchInsertPosition();
        System.out.println(s.searchInsert(new int[]{1,3}, 4));
    }

}
