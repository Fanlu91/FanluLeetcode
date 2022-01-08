package palindromic;

// Source : https://leetcode.com/problems/longest-palindromic-subsequence/
// Id     : 516
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-25
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 100% 100%

public class LongestPalindromicSubsequence {

    // 70.45%  22 ms 5.55%
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()]; // max count from index i to j
        for (int i = s.length() - 1; i >= 0; i--) { // left pointer moves from the end to start
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) { // right pointer moves from left to the end
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][s.length() - 1];
    }

    // 96.74% 13ms 5.55%
    public int longestPalindromeSubseqImprove(String s) {
//    public int longestPalindromeSubseq(String s) {
        int[][] mem = new int[s.length()][s.length()];
        return dp(s, 0, s.length() - 1, mem);
    }

    private int dp(String s, int i, int j, int[][] mem) {
        if (i == j)
            return 1;
        if (i > j)
            return 0;
        if (mem[i][j] != 0)
            return mem[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            mem[i][j] = 2 + dp(s, i + 1, j - 1, mem);
            return mem[i][j];
        }
        mem[i][j] = Math.max(dp(s, i + 1, j, mem), dp(s, i, j - 1, mem));
        return mem[i][j];
    }

    /**
     * Reduce dp table dimension to improve performance
     * <p>
     * for each i iteration we will store all max at dp[j]
     * <p>
     * the next
     *
     * @param s
     * @return
     */
    // 100.00% 6ms 100.00%
    public int longestPalindromeSubseqBetter(String s) {
//    public int longestPalindromeSubseq(String s) {
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        int max = 0;

        for (int i = 0; i < dp.length; ++i) {
            dp[i] = 1;

            int maxSoFar = 0;
            for (int j = i - 1; j >= 0; --j) {
                int prev = dp[j];
                if (str[i] == str[j]) {
                    dp[j] = maxSoFar + 2;
                }
                maxSoFar = Math.max(prev, maxSoFar);
            }
        }

        for (int i : dp)
            max = Math.max(max, i);
        return max;
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence l = new LongestPalindromicSubsequence();
        System.out.println(l.longestPalindromeSubseqBetter("aabaa"));
//        System.out.println(l.longestPalindromeSubseq("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"));
    }
}
