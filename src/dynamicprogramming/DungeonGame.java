//package dynamicprogramming;
//
//// Source : https://leetcode.com/problems/dungeon-game/
//// Id     : 174
//// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
//// Date   : 2019-09-11
//// Topic  : Dynamic Programming
//// Level  : Hard
//// Other  :
//// Tips   :
//// Result : check 64 first
//
//public class DungeonGame {
//    public int calculateMinimumHP(int[][] dungeon) {
//        int m = dungeon.length - 1, n = dungeon[0].length - 1;
//        int[][] dp = new int[m + 1][n + 1];
//        // when you arrive you must have at least 1
//        dp[m][n] = dungeon[m][n] < 0 ? 1 - dungeon[m][n] : 1;
//
//        for (int i = m; i >= 0; i--) {
////            dp[i][n] + dungeon[i][n] >=  dp[i + 1][n]
////            dp[i][n]  >=  dp[i + 1][n] - dungeon[i][n]
//
////            dp[i][n] = Math.max(1, dp[i + 1][n] - dungeon[i][n]);
//            dp[i][n] = Math.max(1, dp[i + 1][n] - dungeon[i][n]);
//        }
//        for (int i = n; i >= 0; i--) {
//            dp[m][i] = dp[0][i - 1] - dungeon[0][i];
//        }
//
//
//        for (int i = 1; i < dungeon.length; i++) {
//            for (int j = 1; j < dungeon[0].length; j++) {
//
//                int up = dp[i - 1][j];
//                int left = dp[i][j - 1];
//
//                dp[i][j] = Math.max(left, up) + dungeon[i][j];
//            }
//        }
//        return dp[dungeon.length - 1][dp[0].length - 1] + 1
//    }
//}
