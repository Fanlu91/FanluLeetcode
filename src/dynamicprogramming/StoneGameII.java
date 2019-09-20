package dynamicprogramming;

// Source : https://leetcode.com/problems/stone-game-ii/
// Id     : 1140
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-18
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class StoneGameII {

    private int[] sums; // the sum from piles[i] to the end
    private int[][] mem;

    public int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0)
            return 0;
        sums = new int[piles.length];
        sums[piles.length - 1] = piles[piles.length - 1];
        for (int i = piles.length - 2; i >= 0; i--) {
            sums[i] = sums[i + 1] + piles[i]; //the sum from piles[i] to the end
        }

        if (piles.length < 64)
            mem = new int[piles.length][16]; // M won't be bigger(for example 64) as piles length <= 100;
        else
            mem = new int[piles.length][32]; // M won't be bigger(for example 64) as piles length <= 100;

        return recursive(piles, 0, 1);
    }

    private int recursive(int[] piles, int i, int m) {
        if (i == piles.length)
            return 0;
        if (piles.length - i <= m * 2)
            return sums[i];
        System.out.println(i + " " + m);
        if (mem[i][m] != 0)
            return mem[i][m];

        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= 2 * m; j++) {
            min = Math.min(min, recursive(piles, i + j, Math.max(m, j)));
        }

        mem[i][m] = sums[i] - min;
        return mem[i][m];
    }

    public static void main(String[] args) {
        StoneGameII s = new StoneGameII();
        System.out.println(s.stoneGameII(new int[]{6333, 2390, 2813, 5576, 4242, 8335, 9539, 9166, 608, 8764, 1590, 5323, 9810, 8633, 5356, 2267, 824, 3285, 7509, 5872, 6243, 9436, 5711, 6159, 5831, 8165, 1483, 355, 8783, 7876, 9255, 8644, 2378, 9626, 9451, 3495, 1581, 9788, 2257, 4653, 2128, 6682, 451, 9993, 8004, 4440, 5011, 2860, 1829, 9101, 348, 6896, 1513, 9138, 8089, 1083, 1432, 7604, 4418, 9991, 9219, 7001, 7522, 3684, 1883}));
    }
}
