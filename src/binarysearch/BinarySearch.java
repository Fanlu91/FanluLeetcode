package binarysearch;

// Source : https://leetcode.com/problems/binary-search/
// Id     : 704
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.09%

public class BinarySearch {
    public int search(int[] num, int target) {
        int l = 0, r = num.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (num[mid] == target)
                return mid;
            if (num[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
