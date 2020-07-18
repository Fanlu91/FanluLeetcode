package geometry;
// Source : https://leetcode.com/problems/surface-area-of-3d-shapes/
// Id     : 892
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/12
// Topic  : Math 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 77.35% 100.00%

public class SurfaceAreaOf3DShapes {

    public int surfaceArea(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        //统计所有的立方体数量
        int blockCount = 0;
        //统计有多少个面被其他面盖住，那么就在所有的立方体的表面积上减去被盖住的面数×2（因为盖住一个面需要另一个面来盖，所以会损失2个面）；
        int coveredAreaCount = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                blockCount += grid[i][j];
                //这个是统计当前格子中因为堆叠而盖住了几个面
                coveredAreaCount += grid[i][j] > 1 ? grid[i][j] - 1 : 0;
                if (i > 0) {
                    //看看上一行同一列盖住了多少个面
                    coveredAreaCount += Math.min(grid[i - 1][j], grid[i][j]);
                }
                if (j > 0) {
                    //看看同一行前一列盖住了几个面
                    coveredAreaCount += Math.min(grid[i][j - 1], grid[i][j]);
                }
            }
        }
        return blockCount * 6 - coveredAreaCount * 2;
    }

}