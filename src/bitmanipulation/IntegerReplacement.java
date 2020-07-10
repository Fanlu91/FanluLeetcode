package bitmanipulation;

import java.util.HashMap;
import java.util.Map;

// Source : https://leetcode.com/problems/integer-replacement/
// Id     : 397
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-02
// Topic  : Bit Manipulation
// Level  : Medium
// Other  :
// Tips   : 100% 8.00%
public class IntegerReplacement {

    // 25.85% 8 ms 16.67%
    public int integerReplacement0(int n) {
        if (n == 1)
            return 0;
        if (n == Integer.MAX_VALUE)
            return 32;
        if (n % 2 == 0)
            return integerReplacement(n / 2) + 1;
        else
            return Math.min(integerReplacement(n + 1), integerReplacement(n - 1)) + 1;
    }

    //100.00% 0ms 16.67%
    public int integerReplacement(int n) {
        if (n == 1)
            return 0;
        if (n == Integer.MAX_VALUE)
            return 32;
        int count = 0;
        while (n > 3) {
            if (n % 2 == 0)
                n >>= 1;
            else if ((n + 1) % 4 == 0)
                n += 1;
            else
                n -= 1;
            count++;
        }

        // n ==3 or n==2
        return n == 3 ? count + 2 : count + 1;

    }

    public int integerReplacement1(int n) {
        if (n == 1)
            return 0;
        if (n == Integer.MAX_VALUE)
            return 32;
        int count = 0;
        while (n > 3) {
            if ((n & 1) == 0) //判断是 0 或 1
                n >>= 1;
            else if ((n & 2) == 0) // 已经确定最后一位是1，判断是01 还是11
                n -= 1;
            else
                n += 1;
            count++;
        }

        // n ==3 or n==2
        return n == 3 ? count + 2 : count + 1;
    }
}
