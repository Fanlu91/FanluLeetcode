package twopointers;

// Source : https://leetcode.com/problems/remove-element/
// Id     : 27
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-06
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class RemoveElement {

    // 100.00% 100.00%
    public int removeElement(int[] nums, int val) {

        int res = nums.length, head = 0, tail = nums.length - 1;

        while (head <= tail) {
            if (nums[head] == val) {
                res--;
                while (nums[tail] == val) {
                    // head = tail && value = val, it means we finished check,
                    // and res has already been reduced above while loop
                    if (tail == head)
                        return res;
                    res--;
                    tail--;
                }
                nums[head] = nums[tail];
                tail--;
            }
            head++;
        }
        return res;
    }

    // practice
    // 0ms
    public int removeElement1(int[] nums, int val) {
//    public int removeElement(int[] nums, int val) {
        if (nums.length == 0)
            return 0;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] == val) {
                if (nums[r] == val) {
                    r--;
                } else {
                    nums[l] = nums[r];
                    r--;
                }
            } else
                l++;
        }
        return l;
    }
}
