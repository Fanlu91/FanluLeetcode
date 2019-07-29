package array;
// Source : https://leetcode.com/problems/fibonacci-number/
// Id     : 509
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-04
// Topic  : Array
// Level  : Easy-
// Other  :
// Tips   : Fibonacci sequence
// Result : 100.00% 50.46%

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {

    // 29.11% 9 ms 50.55% 32.1 MB
    public int fibSlow(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;
        return fibSlow(N - 2) + fibSlow(N - 1);
    }

    Map<Integer, Integer> known = new HashMap<>();
    int result;

    // 100.00% 50.46%
    public int fib(int N) {
        known.put(1, 1);
        known.put(0, 0);
        return fibR(N);
    }

    public int fibR(int N) {
        if (known.containsKey(N))
            return known.get(N);
        else {
            result = fibR(N - 2) + fib(N - 1);
            known.put(N, result);
            return result;
        }
    }


}
