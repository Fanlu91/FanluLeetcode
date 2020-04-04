package array;

// Source : https://leetcode.com/problems/distribute-candies-to-people/
// Id     : 1103
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Array
// Level  : medium
// Other  :
// Tips   :
// Result : 8.10% 5.27%

public class DistributeCandiesToPeople {
    // 8.10% 3ms 5.27%
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int i = 0;
        while (candies != 0) {
            ans[i % num_people] += Math.min(candies, i + 1);
            candies -= Math.min(candies, i + 1);
            i += 1;
        }
        return ans;
    }
}
