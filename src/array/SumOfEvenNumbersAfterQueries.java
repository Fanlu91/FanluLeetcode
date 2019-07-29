package array;

// Source : https://leetcode.com/problems/sum-of-even-numbers-after-queries/
// Id     : 985
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-06
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 99.93% 86.35%

public class SumOfEvenNumbersAfterQueries {

    // 97.77% 4 ms 84.90%
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] ans = new int[queries.length];
        int oldValue;

        A[queries[0][1]] += queries[0][0];
        for (int num : A) {
            if ((num & 0x1) == 0) {
                ans[0] += num;
            }
        }

        for (int i = 1; i < queries.length; i++) {
            oldValue = A[queries[i][1]];
            A[queries[i][1]] += queries[i][0];

            if ((oldValue & 0x1) == 1 && (A[queries[i][1]] & 0x1) == 1) {
                ans[i] = ans[i - 1];
                continue;
            }
            if ((oldValue & 0x1) == 1 && (A[queries[i][1]] & 0x1) == 0) {
                ans[i] = ans[i - 1] + A[queries[i][1]];
                continue;
            }
            if ((oldValue & 0x1) == 0 && (A[queries[i][1]] & 0x1) == 0) {
                ans[i] = ans[i - 1] - oldValue + A[queries[i][1]];
                continue;
            }
            if ((oldValue & 0x1) == 0 && (A[queries[i][1]] & 0x1) == 1) {
                ans[i] = ans[i - 1] - oldValue;
            }
        }
        return ans;
    }

    // 22.07% 1028 ms 99.81%
    public int[] sumEvenAfterQueriesOrigin(int[] A, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            A[queries[i][1]] += queries[i][0];
            for (int num : A) {
                if ((num & 0x1) == 0) {
                    ans[i] += num;
                }
            }
        }
        return ans;
    }

    // learned from leetcode submission.
    // the difference compare with my approach is the for loop, logic is the same.
    // 99.93% 3ms 86.35%
    public int[] sumEvenAfterQueriesImproved(int[] A, int[][] queries) {
        int sum = 0;
        int[] result = new int[queries.length];
        for (int i = 0; i < A.length; i++) {
            sum += (((A[i] + 1) & 1) * A[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            sum -= (((A[queries[i][1]] + 1) & 1) * A[queries[i][1]]);

            A[queries[i][1]] = (A[queries[i][1]] + queries[i][0]);
            sum += (((A[queries[i][1]] + 1) & 1) * A[queries[i][1]]);

            result[i] = sum;
        }
        return result;
    }
}
