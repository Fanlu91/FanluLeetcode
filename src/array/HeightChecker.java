package array;

// Source : https://leetcode.com/problems/height-checker/
// Id     : 1051
// Author : Fanlu Hai
// Date   : 2018-06-04
// Topic  : Array
// Level  : Easy
// Other  : This is not a very nice described question. You can skip it I think.
// Tips   :
// Result : 96.41% 100.00%

import java.util.Arrays;

public class HeightChecker {
    public int heightChecker(int[] heights) {
        int[] copy = heights.clone();
        Arrays.sort(copy);
        int count = 0;
        for (int i = 0; i < copy.length; i++) {
            if (heights[i] != copy[i]) count++;
        }
        return count;
    }
}
