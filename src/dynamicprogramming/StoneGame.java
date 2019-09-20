package dynamicprogramming;

// Source : https://leetcode.com/problems/stone-game/
// Id     : 877
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-18
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class StoneGame {


    /**
     * explained below:
     * https://leetcode.com/problems/stone-game/discuss/384891
     *
     * @param piles
     * @return
     */
    // 51.82% 5 ms 84.85%
    public boolean stoneGame(int[] piles) {

        int sum = 0;
        for (int i : piles) {
            sum += i;
        }
        int[][] mem = new int[piles.length][piles.length];
        return 2 * findMax(0, piles.length - 1, piles, mem) >= sum;
    }

    private int findMax(int left, int right, int[] piles, int[][] mem) {
        if (left < 0 || right < 0 || left > right)
            return 0;
        if (mem[left][right] != 0)
            return mem[left][right];
        if (left == right) {
            mem[left][right] = piles[left];
            return piles[left];
        }

        int max = Math.max(piles[left] + Math.min(findMax(left + 2, right, piles, mem), findMax(left + 1, right - 1, piles, mem)),
                piles[right] + Math.min(findMax(left + 1, right - 1, piles, mem), findMax(left, right - 2, piles, mem)));
        mem[left][right] = max;
        return max;
    }


    /**
     * Just return true
     * Alex is first to pick pile.
     * piles.length is even, and this lead to an interesting fact:
     * Alex can always pick odd piles or always pick even piles!
     * <p>
     * In the description, we know that sum(piles) is odd.
     * If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
     * If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.
     */
    // 100.00% 100.00%
    public boolean stoneGameDirectAnswer(int[] piles) {
//    public boolean stoneGame(int[] piles) {
        return true;
    }

    public static void main(String[] args) {
        StoneGame s = new StoneGame();
        System.out.println(s.stoneGame(new int[]{7, 7, 12, 16, 41, 48, 41, 48, 11, 9, 34, 2, 44, 30, 27, 12, 11, 39, 31, 8, 23, 11, 47, 25, 15, 23, 4, 17, 11, 50, 16, 50, 38, 34, 48, 27, 16, 24, 22, 48, 50, 10, 26, 27, 9, 43, 13, 42, 46, 24}));
    }
}
