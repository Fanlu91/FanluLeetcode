package geometry;
// Source : https://leetcode.com/problems/circle-and-rectangle-overlapping/
// Id     : 1401
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/11
// Topic  : Geometry
// Level  : Medium
// Other  :
// Tips   :
// Links  : 836
// Result : 100.00% 100.00%

public class CircleAndRectangleOverlapping {
    /**
     * find the distance between the closest point in this rectangle to the center of the circle
     *
     * @param radius
     * @param x_center
     * @param y_center
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int dx, dy;
        if (x1 > x_center) {
            dx = x1 - x_center;
        } else {
            if (x2 < x_center)
                dx = x_center - x2;
            else
                dx = 0;
        }

        if (y1 > y_center) {
            dy = y1 - y_center;
        } else {
            if (y2 < y_center)
                dy = y_center - y2;
            else
                dy = 0;
        }
        return dx * dx + dy * dy <= radius * radius;

    }
}