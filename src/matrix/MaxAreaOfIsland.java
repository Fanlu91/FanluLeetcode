package matrix;

// Source : https://leetcode.com/problems/max-area-of-island/
// Id     : 695
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Matrix, Back tracking
// Level  : Medium
// Other  :
// Tips   :
// Result : 82.11% 91.14%

public class MaxAreaOfIsland {

    // 82.11% 3ms 91.14%
    public int maxAreaOfIsland(int[][] grid) {
//        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int tmp = checkIslandSize(grid, i, j);
                    ans = Math.max(ans, tmp);
                }
            }
        }
        return ans;
    }

    private int checkIslandSize(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int tmp = 1;
        if (i != 0 && grid[i - 1][j] == 1)
            tmp += checkIslandSize(grid, i - 1, j);
        if (i != grid.length - 1 && grid[i + 1][j] == 1)
            tmp += checkIslandSize(grid, i + 1, j);
        if (j != 0 && grid[i][j - 1] == 1)
            tmp += checkIslandSize(grid, i, j - 1);
        if (j != grid[0].length - 1 && grid[i][j + 1] == 1)
            tmp += checkIslandSize(grid, i, j + 1);
        return tmp;
    }
}


