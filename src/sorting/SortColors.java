package sorting;
// Source : https://leetcode.com/problems/sort-colors/
// Id     : 75
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/17
// Topic  : sorting 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result :

public class SortColors {

    // 9ms
    public void sortColors(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            if (nums[l] != 2) {
                l++;
                continue;
            }
            if (nums[r] == 2) {
                r--;
                continue;
            }
            swap(nums, l, r);
            l++;
            r--;
        }

        l = 0;
        // 处理 r 位于 2 的区间未移动的情况，此种情况一般是l一直移动到了r的位置，因此 r-- 即可解决
        if (nums[r] == 2)
            r--;

        while (l < r) {
            if (nums[l] == 0) {
                l++;
                continue;
            }

            if (nums[r] == 1) {
                r--;
                continue;
            }

            swap(nums, l, r);
            l++;
            r--;
        }
    }

    // 0 ms
    public void sortColors1(int[] nums) {
//    public void sortColors(int[] nums) {
        int r = 0;
        int w = 0;
        int b = nums.length - 1;
        while (w <= b) {
            if (nums[w] == 0) {
                swap(nums, r, w);
                r++;
                if (r >= w) {
                    w++;
                }
            } else if (nums[w] == 1) {
                w++;
            } else if (nums[w] == 2) {
                swap(nums, b, w);
                b--;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}