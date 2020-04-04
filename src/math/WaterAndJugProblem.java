package math;

// Source : https://leetcode.com/problems/water-and-jug-problem/
// Id     : 365
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-11-05
// Topic  : Math, BFS
// Level  : Medium +
// Other  :
// Tips   :
// Result : 100.00% 13.93%

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WaterAndJugProblem {

    public boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            /**
             * The add() method of Set in Java is used to add a specific element into a Set collection.
             * The function adds the element only if the specified element is not already present in the set
             * else the function return False if the element is already present in the Set.
             */
            if (tmp + x <= x + y && set.add(tmp + x)) {
                queue.offer(tmp + x);
            }
            if (tmp + y <= x + y && set.add(tmp + y)) {
                queue.offer(tmp + y);
            }
            if (tmp - x >= 0 && set.add(tmp - x)) {
                queue.offer(tmp - x);
            }
            if (tmp - y >= 0 && set.add(tmp - y)) {
                queue.offer(tmp - y);
            }
            if (set.contains(z)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Bézout's identity.
     * If you don't know, now you know.
     * It is highly unlikely you can figure this out the first time.
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater2(int x, int y, int z) {
        //public boolean canMeasureWater(int x, int y, int z) {
        if (z > y + x)
            return false;
        // assume x is not smaller
        if (x < y)
            return canMeasureWater(y, x, z);

        if (y == 0)
            return z == 0 || z == x;
        return z % gcb(x, y) == 0;
    }

    // m >= n
    private int gcb(int m, int n) {
        if (m % n == 0) {// 若余数为0,返回最大公约数
            return n;
        } else { // 否则,进行递归,把n赋给m,把余数赋给n
            return gcb(n, m % n);
        }
    }
}

