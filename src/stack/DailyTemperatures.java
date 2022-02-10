package stack;
// Source : https://leetcode.com/problems/daily-temperatures/
// Id     : 739
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/10
// Topic  : stack 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 91.69% 5.03%

import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
    // 1303 ms
    public int[] dailyTemperatures(int[] temperatures) {
        int d = 1, n = temperatures.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int a = temperatures[i];
            while ((i + d) < n) {
                if (temperatures[i + d] > a) {
                    res[i] = d;
                    break;
                }
                d++;
            }
            if (i + d == n)
                res[i] = 0;
            d = 1;
        }
        return res;
    }

    //22 ms
    public int[] dailyTemperatures1(int[] temperatures) {
//    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int a = temperatures[i];
            while (!stack.isEmpty() && a > temperatures[stack.peek()]) {
                int val = stack.pop();
                res[val] = i - val;
            }
            stack.push(i);
        }
        return res;
    }
}