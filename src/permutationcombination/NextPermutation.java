package permutationcombination;
// Source : https://leetcode.com/problems/next-permutation/
// Id     : 31
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/20
// Topic  : Greedy
// Level  : Medium
// Other  :
// Tips   : 字典序排列（全排列）的下一个元素; 理解字典顺序；没解出来。
// Links  : Must
// Result : 100% 66%


import java.util.Arrays;

public class NextPermutation {
    public void swap(int[] nums, int i, int j) {
        int m = nums[i];
        nums[i] = nums[j];
        nums[j] = m;
    }

    public void nextPermutation(int[] nums) {
        if (nums == null)
            return;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                Arrays.sort(nums, i, nums.length);
                for (int j = i; j < nums.length; j++) {
                    if (nums[i - 1] < nums[j]) {
                        swap(nums, j, i - 1);
                        return;
                    }
                }
            }
            if (i == 1) {
                Arrays.sort(nums);
                return;
            }
        }
    }

    public void nextPermutation1(int[] nums) {
//    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }


    public void reverse(int[] nums, int i) {
        int left = i, right = nums.length;
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}