package array;

import java.util.LinkedList;
import java.util.Queue;

// Source : https://leetcode.com/problems/move-zeroes/
// Id     : 283
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-10
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 94.20%

public class MoveZeroes {
    //40.88% 1 ms 99.93%
    public void moveZeroesOrigin(int[] nums) {
        Queue<Integer> zeros = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (zeros.isEmpty())
                    continue;
                else {
                    nums[zeros.poll()] = nums[i];
                    nums[i] = 0;
                    zeros.add(i);
                }
            } else {
                zeros.add(i);
            }
        }
    }

    //100.00% 0ms 94.20%
    public void moveZeroes(int[] nums) {

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
