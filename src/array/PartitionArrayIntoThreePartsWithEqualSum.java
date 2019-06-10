package array;

// Source : https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
// Id     : 1013
// Author : Fanlu Hai
// Date   : 2018-06-10
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 90.69%
public class PartitionArrayIntoThreePartsWithEqualSum {

    //62.22% 2 ms 95.87%
    public boolean canThreePartsEqualSumOrigin(int[] A) {
        int firstTail = -1;
        int lastHead = -1;

        int[] sumLast = new int[A.length];
        sumLast[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i > 1; i--) {
            sumLast[i] = sumLast[i + 1] + A[i];
        }
        int total = sumLast[2] + A[0] + A[1];
        if (total % 3 != 0)
            return false;

        double mean = total / 3;


        int sum = 0;
        for (int i = 0; i < A.length - 2; i++) {
            sum += A[i];
            if (sum == mean) {
                firstTail = i;
                break;
            }
        }

        for (int i = A.length - 1; i > 1; i--) {
            if (sumLast[i] == mean) {
                lastHead = i;
                break;
            }
        }
        System.out.println(mean);
        System.out.println(firstTail);
        System.out.println(lastHead);
        if (firstTail > 0 && firstTail < lastHead)
            return true;
        return false;

    }

    // learned from leecode submission.
    // I think my solution will have better performance if the result sets are more positive
    // 100.00% 1ms 90.69%
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++)
            sum += A[i];

        if (sum % 3 != 0)
            return false;

        int sumPart = 0, count = 0, hit = sum / 3;
        for (int i = 0; i < A.length; i++) {
            sumPart += A[i];
            if (sumPart == hit) {
                sumPart = 0;
                count++;
            }
        }

        if (count == 3)
            return true;
        else
            return false;
    }

}
