package stack;
// Source : https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
// Id     : 1047
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/9
// Topic  : stack 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 16.48%

import java.util.Deque;
import java.util.LinkedList;

public class RemoveAllAdjacentDuplicatesInString {
    // 19 ms
    public String removeDuplicates(String s) {
        Deque<Character> stack = new LinkedList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || arr[i] != stack.peek())
                stack.push(arr[i]);
            else
                stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pollLast());
        return sb.toString();
    }

    // 3ms
    public String removeDuplicates1(String S) {
//    public String removeDuplicates(String S) {
        char[] s = S.toCharArray();
        int i = -1;
        for (char c : s) {
            if (i == -1) {
                s[++i] = c;
                continue;
            }
            if (s[i] == c) {
                i--;
            } else {
                s[++i] = c;
            }
        }
        return String.valueOf(s, 0, i + 1);
    }
}