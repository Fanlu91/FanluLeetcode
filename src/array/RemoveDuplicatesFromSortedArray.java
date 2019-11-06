package array;

// Source : https://leetcode.com/problems/remove-duplicates-from-sorted-array/
// Id     : 26
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100% 99.47%

public class RemoveDuplicatesFromSortedArray {
    // 97.75% 1ms 75.00%
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;

        int pointer = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[pointer]) {
                // use pointer to write all distinct value.
                nums[++pointer] = nums[i];
            }
        }
        return pointer + 1;
    }

    // 100% 99.47%
    public int removeDuplicatesImprove(int[] nums) {
//    public int removeDuplicates(int[] nums) {
        int pointer = 0;
        int index = 0;

        while (index < nums.length) {
            if (nums[pointer] == nums[index]) {
                index++;
            } else {
                nums[++pointer] = nums[index++];
            }
        }
        return pointer + 1;
    }
}
