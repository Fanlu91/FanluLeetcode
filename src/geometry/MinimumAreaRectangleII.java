package geometry;
// Source : https://leetcode.com/problems/minimum-area-rectangle-ii/
// Id     : 963
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/11
// Topic  : Geometry
// Level  : Medium+
// Other  :
// Tips   :
// Links  : 939
// Result : 52.63% 100.00%

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class MinimumAreaRectangleII {
    public double minAreaFreeRect(int[][] points) {
        Point[] pointArray = new Point[points.length];
        Set<Point> pointSet = new HashSet();

        for (int i = 0; i < points.length; ++i) {
            pointArray[i] = new Point(points[i][0], points[i][1]);
            pointSet.add(pointArray[i]);
        }

        double ans = Double.MAX_VALUE;

        for (int i = 0; i < points.length; ++i) {
            Point p1 = pointArray[i];
            for (int j = 0; j < points.length; ++j) {
                if (j != i) {
                    Point p2 = pointArray[j];
                    for (int k = j + 1; k < points.length; ++k) {
                        if (k != i) {
                            Point p3 = pointArray[k];

                            // 判断组成四边形的另外一个点在不在
                            // x3-x1 = x4- x 2
                            Point p4 = new Point(p2.x + p3.x - p1.x, p2.y + p3.y - p1.y);
                            if (pointSet.contains(p4)) {
                                // 判定平行四边形的某一个角的度数是否为 90 度
                                // 判定方法是计算向量 (p2 - p1) 与 (p3 - p1) 的点积是否为0。
                                if ((p2.x - p1.x) * (p3.x - p1.x) + (p2.y - p1.y) * (p3.y - p1.y) == 0) {
                                    double area = p1.distance(p2) * p1.distance(p3);
                                    if (area < ans)
                                        ans = area;
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans < Double.MAX_VALUE ? ans : 0;
    }

}