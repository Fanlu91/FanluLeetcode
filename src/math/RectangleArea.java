package math;
// Source : https://leetcode.com/problems/rectangle-area/
// Id     : 223
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/11
// Topic  : Math 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 98.16% 25.00%

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int totalArea = (C - A) * (D - B) + (G - E) * (H - F);
        if (!isRectangleOverlap(new int[]{A, B, C, D}, new int[]{E, F, G, H}))
            return totalArea;
        int x1 = Math.max(A, E);
        int y1 = Math.max(B, F);
        int x2 = Math.min(C, G);
        int y2 = Math.min(D, H);
        int overlapArea = (x2 - x1) * (y2 - y1);
        return totalArea - overlapArea;
    }

    private boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }
}