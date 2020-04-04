package array;
// Source : https://leetcode.com/problems/minimum-increment-to-make-array-unique/
// Id     : 945
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Array
// Other  : n
// Tips   :
// Level  : Easy
// Result : 97.81% 86.27%

import java.util.HashSet;
import java.util.Set;

public class MinimumIncrementToMakeArrayUnique {
    // ETL
    public int minIncrementForUniqueETL(int[] A) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int num : A) {
            while (!set.add(num)) {
                ans++;
                num++;
            }
        }
        return ans;
    }

    // 97.81% 5ms 86.27%
    public int minIncrementForUnique(int[] A) {
        int[] state = new int[50000];
        for (int num : A)
            state[num]++;

        int ans = 0;
        for (int i = 0; i < 50000; i++) {
            if (state[i] > 1) {
                ans += state[i] - 1;
                state[i + 1] += state[i] - 1;
            }
        }
        return ans;
    }
}
