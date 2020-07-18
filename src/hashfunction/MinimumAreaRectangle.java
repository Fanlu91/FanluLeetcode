package hashfunction;
// Source : https://leetcode.com/problems/minimum-area-rectangle/
// Id     : 939
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/11
// Topic  : Hash Function
// Level  : Medium
// Other  :
// Tips   :
// Links  : 223
// Result : 77.35% 100.00%

import java.util.HashSet;
import java.util.Set;

public class MinimumAreaRectangle {
    // 77.35% 157 ms 100.00%
    public int minAreaRect(int[][] points) {
        if (points.length < 4)
            return 0;

        // in order to check if a certain points exists.¬
        Set<Integer> allPointsHash = new HashSet<>(points.length);
        for (int i = 0; i < points.length; i++)
            allPointsHash.add(points[i][0] * 40001 + points[i][1]);

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; ++i)
            for (int j = i + 1; j < points.length; ++j)
                // not in the same x/y axis
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1])
                    // has another two points in order to form a rectangle
                    if (allPointsHash.contains(40001 * points[i][0] + points[j][1])
                            && allPointsHash.contains(40001 * points[j][0] + points[i][1]))
                        ans = Math.min(ans, Math.abs(points[j][0] - points[i][0])
                                * Math.abs(points[j][1] - points[i][1]));

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}