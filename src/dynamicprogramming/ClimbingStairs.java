package dynamicprogramming;

// Source : https://leetcode.com/problems/climbing-stairs/
// Id     : 70
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-08-02
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.26%

public class ClimbingStairs {

    //! TLE
    /*
    Map<Integer, Integer> stepCount = new HashMap<>();

    public ClimbingStairs() {
        stepCount.put(1, 1);
        stepCount.put(2, 2);
    }

    public int climbStairs(int n) {
        if (stepCount.containsKey(n))
            return stepCount.get(n);
        return climbStairs(n - 1) + climbStairs(n - 2);
    }*/

    int[] count;

    // 100.00% 5.06%
    public int climbStairs1(int n) {
        if (n == 1)
            return 1;

        count = new int[n + 1];
        count[1] = 1;
        count[2] = 2;
        return dp(n);
    }

    public int dp(int n) {
        if (count[n] != 0)
            return count[n];
        else {
            int tmp = dp(n - 1) + dp(n - 2);
            count[n] = tmp;
            return tmp;
        }
    }

    /**
     * compact storage
     *
     * @param n
     * @return
     */
    // 100.00% 5.26%
    public int climbStairs(int n) {
        if (n <= 1) {
            return n;
        }

        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        ClimbingStairs c = new ClimbingStairs();
        System.out.println(c.climbStairs(47));
    }
}
