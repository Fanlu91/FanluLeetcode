package math;

// Source : https://leetcode.com/problems/happy-number/
// Id     : 202
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-10
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   :
// Result : 47.92% 8.33%

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HappyNumber {
    Set<Integer> set = new HashSet<>();

    public boolean isHappy(int n) {
        if (n == 1)
            return true;
        if (set.contains(n))
            return false;
        List<Integer> list = new LinkedList<>();
        while (n > 9) {
            list.add(n % 10);
            n /= 10;
        }
        list.add(n);

        int sum = 0;
        for (int num : list)
            sum += num * num;
        set.add(n);

        return isHappy(sum);
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy1(int n) {
//    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}
