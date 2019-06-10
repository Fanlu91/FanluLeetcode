package array;

// Source : https://leetcode.com/problems/fair-candy-swap/
// Id     : 888
// Author : Fanlu Hai
// Date   : 2018-06-10
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 98.74%
public class FairCandySwap {

    //25.90% 116 ms 93.87%
    public int[] fairCandySwapOrigin(int[] A, int[] B) {

        int diff = 0;
        for (int a : A)
            diff += a;
        for (int b : B)
            diff -= b;

        diff /= 2;

        for (int a : A) {
            for (int b : B) {
                if ((a - b) == diff)
                    return new int[]{a, b};
            }
        }
        return null;
    }

    //learned from leetcode submission
    // instead of using double for loops, using hash like function to store A / B array's result,
    // then check using hash, reduce O(n2) to O(n)
    // we can also use set+set.contains to do this which will be more adaptable but a bit slower in this case.
    // 100.00% 98.74%
    public int[] fairCandySwap(int[] A, int[] B) {

        int diff = 0;
        boolean[] hasA = new boolean[10001];
        for (int a : A) {
            diff += a;
            hasA[a] = true;
        }
        for (int b : B)
            diff -= b;

        diff /= 2;


        for (int b : B) {
            if (b + diff > 0 && b + diff <= 100000 && hasA[b + diff])
                return new int[]{b + diff, b};
        }

        return null;
    }
}
