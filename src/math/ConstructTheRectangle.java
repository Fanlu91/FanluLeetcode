package math;
// Source : https://leetcode.com/problems/construct-the-rectangle/
// Id     : 492
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/11
// Topic  : Math 
// Level  : Easy
// Other  :
// Tips   :
// Links  : 836
// Result : 100.00% 14.29%

public class ConstructTheRectangle {
    // 16.89% 71ms 14.29%
    public int[] constructRectangle(int area) {
        int l = (int) Math.ceil(Math.sqrt(area));
        while (l < area) {
            if (area % l == 0)
                return new int[]{l, area / l};
            l++;
        }
        return new int[]{area, 1};
    }

    /**
     * the value of sqrt result will be much closer to 1 than area
     *
     * @param area
     * @return
     */
    // 100.00% 0ms
    public int[] constructRectangle1(int area) {
//    public int[] constructRectangle(int area) {
        int sqrt = (int) Math.sqrt(area);
        while (area % sqrt != 0) {
            sqrt--;
        }
        return new int[]{area / sqrt, sqrt};
    }
}