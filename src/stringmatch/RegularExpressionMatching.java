//package stringmatch;
//// Source : https://leetcode.com/problems/regular-expression-matching/
//// Id     : 10
//// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
//// Date   : 2022/1/6
//// Topic  : stringmatch
//// Level  : Hard
//// Other  :
//// Tips   :
//// Links  :
//// Result :
//
//public class RegularExpressionMatching {
//    public boolean isMatch(String s, String p) {
//        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
//        dp[0][0] = true;
//        for (int i = 1; i < s.length() + 1; i++) {
//            for (int j = 1; j < p.length() + 1; j++) {
//                if (s.charAt(i) == p.charAt(j))
//                    dp[i][j] = dp[i - 1][j - 1];
//                else if (p.charAt(j) == '.') {
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else if (p.charAt(j) == '*') {
//
//                } else {
//
//                }
//            }
//        }
//
//        return dp[s.length()][p.length()];
//    }
//}