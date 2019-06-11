package array;

// Source : https://leetcode.com/problems/degree-of-an-array/
// Id     : 697
// Author : Fanlu Hai
// Date   : 2018-06-11
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 54.88%

import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray {

    // 61.95% 21 ms 97.83%
    public int findShortestSubArrayOrigin(int[] nums) {
        Map<Integer, Integer> degreeMap = new HashMap<>();
        Map<Integer, Integer> startIndexMap = new HashMap<>();
        Map<Integer, Integer> endIndexMap = new HashMap<>();
        int arrayDegree = 1;
        int tmpDegree;
        for (int i = 0; i < nums.length; i++) {
            if (degreeMap.containsKey(nums[i])) {
                tmpDegree = degreeMap.get(nums[i]) + 1;
                if (tmpDegree > arrayDegree)
                    arrayDegree = tmpDegree;
                degreeMap.put(nums[i], tmpDegree);
                endIndexMap.put(nums[i], i);
            } else {
                degreeMap.put(nums[i], 1);
                startIndexMap.put(nums[i], i);
            }
        }

        if (endIndexMap.isEmpty())
            return 1;

        int smallestLength = 50000; // according to condition provided.
        int tmpLength = 0;
        for (int key : endIndexMap.keySet()) {
            if (arrayDegree == degreeMap.get(key)) {
//                System.out.println("key " + key + " end " + endIndexMap + " start " + startIndexMap);
                tmpLength = endIndexMap.get(key) - startIndexMap.get(key);
                if (tmpLength < smallestLength)
                    smallestLength = tmpLength;
            }
        }
        return smallestLength + 1;
    }

    // 93.06% 10 ms  94.04%
    public int findShortestSubArrayImproved(int[] nums) {

        HashMap<Integer, int[]> intToFreqInfo = new HashMap<>();

        int maxDegree = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (!intToFreqInfo.containsKey(nums[i])) {
                intToFreqInfo.put(nums[i], new int[]{1, i, i});
            } else {
                int[] freqInfo = intToFreqInfo.get(nums[i]);
                maxDegree = Math.max(maxDegree, ++freqInfo[0]);
                freqInfo[2] = i;
            }
        }

        int smallestLength = 50000;
        for (int[] freqInfo : intToFreqInfo.values()) {
            if (freqInfo[0] == maxDegree) {
                smallestLength = Math.min(smallestLength, freqInfo[2] - freqInfo[1]);
            }
        }

        return smallestLength + 1;
    }

    // learned from leetcode submission
    // 1.use array to replace hashmap
    // 2.update min on the go instead of storing head and compute later. Really nice logic.
    // 100.00% 54.88%
    public int findShortestSubArray(int[] nums) {
        int[] head = new int[50001];
        int[] degree = new int[50001];

        for (int i = 0; i < 50000; i++)
            head[i] = -1;

        int min = nums.length, max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (head[nums[i]] == -1)
                head[nums[i]] = i;

            degree[nums[i]]++;

            if (degree[nums[i]] > max) {
                max = degree[nums[i]];
                // this step is the key
                min = i - head[nums[i]] + 1;

            } else if (degree[nums[i]] == max && min > i - head[nums[i]] + 1)
                min = i - head[nums[i]] + 1;
        }
        return min;
    }

}
