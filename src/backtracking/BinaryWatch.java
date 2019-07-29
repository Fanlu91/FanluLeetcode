package backtracking;
// Source : https://leetcode.com/problems/letter-case-permutation/
// Id     : 784
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Easy
// Date   : 2019-07-12
// Other  :
// Tips   :
// Result : 89.19% 100.00%

import java.util.LinkedList;
import java.util.List;

public class BinaryWatch {
    List<String> solution = new LinkedList<>();

    // 89.19% 1ms 100.00%
    public List<String> readBinaryWatch(int num) {
        if (num < 0 || num > 8)
            return new LinkedList<String>();

        int[] a = new int[10];
        backTracking(a, num, 0);
        return solution;
    }

    public void backTracking(int[] array, int num, int index) {

        int[] a = array.clone();
        if (num == 0) {
            addBinaryTimeToString(a);
            return;
        }

        if (index + num == a.length) {
            for (int i = index; i < a.length; i++) {
                a[i] = 1;
            }
            addBinaryTimeToString(a);
            return;
        }
        backTracking(a, num, index + 1);

        a[index] = 1;
        backTracking(a, num - 1, index + 1);

    }

    // minute 0-5 hour 6-9
    public void addBinaryTimeToString(int[] a) {
        int hour = a[9] * 8 + a[8] * 4 + a[7] * 2 + a[6] * 1;
        int minute = a[0] * 1 + a[1] * 2 + a[2] * 4 + a[3] * 8 + a[4] * 16 + a[5] * 32;
        if (hour > 11 || minute > 59)
            return;
        else {
            solution.add(minute < 10 ? hour + ":0" + minute : hour + ":" + minute);
        }
    }


    public static void main(String[] args) {
        BinaryWatch binaryWatch = new BinaryWatch();
        binaryWatch.readBinaryWatch(8);
        System.out.println(binaryWatch.solution.size());

    }
}

