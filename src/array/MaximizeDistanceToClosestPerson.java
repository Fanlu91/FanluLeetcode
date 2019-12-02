package array;

// Source : https://leetcode.com/problems/maximize-distance-to-closest-person/
// Id     : 849
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-26
// Topic  : Minimax
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 91.67%

public class MaximizeDistanceToClosestPerson {

    // 100.00% 1ms 91.67%
    public int maxDistToClosest(int[] seats) {
        int maxEmpty = 0, tmp = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                tmp++;
                maxEmpty = Math.max(maxEmpty, tmp);
            } else
                tmp = 0;
        }
        maxEmpty = (maxEmpty + 1) / 2;

        int i = 0;
        tmp = 0;
        while (seats[i] == 0) {
            tmp++;
            i++;
        }
        maxEmpty = Math.max(maxEmpty, tmp);

        i = seats.length - 1;
        tmp = 0;

        while (seats[i] == 0) {
            tmp++;
            i--;
        }
        maxEmpty = Math.max(maxEmpty, tmp);

        return maxEmpty;
    }
}
