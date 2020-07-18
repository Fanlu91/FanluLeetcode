package math;

// Source : https://leetcode.com/problems/rectangle-overlap/
// Id     : 836
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-11
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   :
// Links  : 223
// Result : 100.00% 50.00%

public class RectangleOverlap {
//    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
//        int x = rec2[0], y = rec2[1];
//        if (x >= rec1[0] && x < rec1[2] && y >= rec1[1] && y < rec1[3])
//            return true;
//        x = rec2[2];
//        y = rec2[3];
//        if (x > rec1[0] && x <= rec1[2] && y > rec1[1] && y <= rec1[3])
//            return true;
//        return false;
//    }

    /**
     * 官方题解写的足够好
     * https://leetcode-cn.com/problems/rectangle-overlap/solution/ju-xing-zhong-die-by-leetcode-solution/
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }


}
