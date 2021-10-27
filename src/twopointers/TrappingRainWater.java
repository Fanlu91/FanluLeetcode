package twopointers;
// Source : https://leetcode.com/problems/trapping-rain-water/
// Id     : 42
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/24
// Topic  : DP
// Level  : Hard
// Other  : elevation map 高程图；elevation 海拔、标高；
// Tips   :
// Links  :
// Result : 99.95% 19.88%

import java.util.Arrays;

public class TrappingRainWater {
    // 按行求解 O(M*N) greedy
    // TLE
    public int trap(int[] height) {
        if (height.length < 2)
            return 0;
        int res = 0, h = 0;
        for (int i : height) {
            h = Math.max(i, h);
        }
        boolean started = false;
        for (int i = 1; i <= h; i++) {
            int tmp = 0, lastBar = 0;
            for (int j = 0; j < height.length; j++) {
                System.out.println(height[j] + " " + i);
                if (height[j] >= i) {
                    started = true;
                    lastBar = j;
                } else if (started)
                    tmp++;
            }
            tmp -= height.length - 1 - lastBar;
            res += tmp;
        }
        return res;
    }

    // 99.95% 1ms 19.88%
    public int trap1(int[] height) {
//    public int trap(int[] height) {
        if (height.length < 2)
            return 0;
        int[] leftBars = new int[height.length], rightBars = new int[height.length];
        int l = 0, r = height[height.length - 1];
        for (int i = 1; i < height.length - 1; i++) {
            l = Math.max(l, height[i - 1]);
            r = Math.max(r, height[height.length - i]);
            leftBars[i] = l;
            rightBars[height.length - 1 - i] = r;
        }

        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int h = Math.min(leftBars[i], rightBars[i]) - height[i];
            if (h > 0) res += h;
        }
        return res;
    }
}