package string;
// Source : https://leetcode.com/problems/reverse-string-ii/
// Id     : 541
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/22
// Topic  : String
// Level  : Easy
// Other  :
// Tips   : 简单题目，要注意每一句话设置的条件，处理好细节。
// Links  :
// Result : 100.00% 25.50%

public class ReverseStringII {
    public String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        int p1 = 0, mid = k - 1;
        while (mid <= array.length - 1) {
            swap(array, p1, mid);
            mid += 2 * k;
            p1 += 2 * k;
        }
        if (p1 < array.length - 1)
            swap(array, p1, array.length - 1);
        return new String(array);

    }

    public void swap(char[] array, int left, int right) {
        while (left < right) {
            char tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }

}