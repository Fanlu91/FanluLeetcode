package array;
// Source : https://leetcode.com/problems/array-partition-i/
// Id     : 561
// Author : Fanlu Hai
// Date   : 2018-06-04
// Topic  : Array
// Other  : bucket sort can be very efficient
// Tips   :
// Result : 100.00% 97.20%

import java.util.Arrays;

public class ArrayPartitionI {
    // 92.03% 14 ms 99.71%
    public int arrayPairSumSlow(int[] nums) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += copy[i];
            i++;
        }
        return result;
    }

    // 92.03% 100.00%
    public int arrayPairSumSlightyImprove(int[] nums) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int result = 0, i = 0;
        while (true) {
            result += copy[i];
            i += 2;
            if (i == nums.length)
                return result;
        }
    }


    // learned from leetcode submission
    // if length of nums is not very large, this is a much more efficient solution.
    // take advantage of bucket sort.
    // 100.00% 3 ms 97.20%
    public int arrayPairSum(int[] nums) {

        //Get the smallest and largest elements
        int smallest = nums[0];
        int largest = nums[0];
        for (int n : nums) {
            if (n < smallest) {
                smallest = n;
            }
            if (n > largest) {
                largest = n;
            }
        }

        //construct a count table
        int[] countsTable = new int[largest - smallest + 1];
        for (int n : nums) {
            countsTable[n - smallest]++;
        }

        //go through the count table, alternatively summing and skipping elements
        int sum = 0;
        boolean localMin = true;
        for (int i = 0; i < countsTable.length; i++) {
            if (countsTable[i] > 0) {
                if (localMin) {
                    sum += i + smallest;
                    localMin = false;
                } else {
                    localMin = true;
                }
                if (countsTable[i] > 1) {
                    countsTable[i--]--;
                }
            }
        }

        //return the sum
        return sum;
    }
}
