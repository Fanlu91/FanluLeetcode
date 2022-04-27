package binarysearch;

// Source : https://leetcode.com/problems/koko-eating-bananas/
// Id     : 875
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : medium
// Other  :
// Tips   :
// Result : 99.95% 26.56%

public class KokoEatingBananas {

    // 67.13% 11 ms 11 ms
    public int minEatingSpeed(int[] piles, int H) {
        int max = piles[0];
        for (int pile : piles)
            max = Math.max(max, pile);
        if (piles.length == H)
            return max;

        int l = 1, r = max;
        int ans = 0;

        while (l <= r) {
            int m = l + (r - l) / 2, count = 0;
            for (int pile : piles)
//                count += Math.ceil(pile * 1.0 / m);
                count += (pile - 1) / m + 1; // way much more efficient
            if (count > H) {
                l = m + 1;
            } else {
                r = m - 1;
                ans = m; // ans might be smaller
            }
        }

        return ans;
    }

    // practice
    // 6 ms
    public int minEatingSpeed1(int[] piles, int h) {
//    public int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];
        for (int pile : piles)
            max = Math.max(max, pile);
        int n = piles.length;
        if (h == n)
            return max;
        int repeat = h / n;
        int l = 1, r = max / repeat + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (cantFinish(piles, mid, h)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private boolean cantFinish(int[] piles, int k, int h) {
        // System.out.println(res);
        for (int i = 0; i < piles.length; i++) {
            int count = piles[i] % k == 0 ? piles[i] / k : piles[i] / k + 1;
            h -= count;
            if (h < 0)
                return true;
        }
        return false;
    }
}
