package binarysearch;

// Source : https://leetcode.com/problems/koko-eating-bananas/
// Id     : 875
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : medium
// Other  :
// Tips   :
// Result : 67.13% 5.14%


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
            if (count > H){
                l = m + 1;
            }else {
                r = m - 1;
                ans = m; // ans might be smaller
            }
        }

        return ans;
    }
}
