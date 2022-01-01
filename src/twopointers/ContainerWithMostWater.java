package twopointers;

// Source : https://leetcode.com/problems/container-with-most-water/
// Id     : 11
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Two Pointers
// Level  : medium-
// Other  :
// Tips   : slant: slope
// Result : 71.02% 7.14%

public class ContainerWithMostWater {
    // 444 ms
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

    // 4ms
    public int maxArea2(int[] height) {
//    public int maxArea(int[] height) {
        int res = 0, left = 0, right = height.length - 1;
        while (left < right) {
            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
            if (height[left] <= height[right])
                left++;
            else
                right--;
        }

        return res;
    }

    // 1ms
    public int maxArea3(int[] height) {
//    public int maxArea(int[] height) {
        int res = 0, h = 0, left = 0, right = height.length - 1;
        while (left < right) {
            h = Math.min(height[left], height[right]);
            res = Math.max(res, (right - left) * h);
            while (height[left] <= h && left < right)
                left++;
            while (height[right] <= h && left < right) {
                right--;
            }
        }
        return res;
    }
}

