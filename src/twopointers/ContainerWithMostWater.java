package twopointers;

// Source : https://leetcode.com/problems/container-with-most-water/
// Id     : 11
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : medium
// Other  :
// Tips   : slant: slope
// Result : 71.02% 7.14%

public class ContainerWithMostWater {
    // 17.56% 444 ms 24.28%
    public int maxArea(int[] height) {
        int res = 0, area = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                area = Math.min(height[i], height[j]) * (j - i);
                res = Math.max(res, area);
            }
        }
        return res;
    }

    // 71.02% 4 ms 7.14%
    public int maxArea1(int[] height) {
//    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, res = 0, area = 0;

        while (left < right) {
            area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, area);
            if (height[left] < height[right])
                left++;
            else // 大于或等于的情况都可以
                right++;
        }
        return res;
    }
}
