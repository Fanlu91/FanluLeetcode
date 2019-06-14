package stack;

// Source : https://leetcode.com/problems/decode-string/
// Id     : 394
// Author : Fanlu Hai
// Date   : 2018-06-13
// Topic  : Stack,String
// Level  : Medium
// Other  : stack or recursive
// Tips   :
// Result : 100.00% 100.00%

import java.util.*;

public class DecodeString {

    // 5.62% 32 ms  84.38%
    public String decodeStringOrigin(String s) {
        Stack<Character> stack = new Stack<>();
        List<Character> tmpList = new LinkedList<>();

        char[] chars = s.toCharArray();
        char tmpChar;
        String num = "";
        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == ']') {
                // address each ] by decoding and to back to stack before moving forward.
                tmpChar = stack.pop();
                while (tmpChar != '[') {
                    tmpList.add(0, tmpChar);
                    tmpChar = stack.pop();
                }
                // reach [ , try to get the multiply operand
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    num = stack.pop() + num;
                }
                // do the multiply
                for (int j = 0; j < Integer.valueOf(num); j++) {
                    stack.addAll(tmpList);
                }

                tmpList.clear();
                num = "";
            } else {
                stack.push(chars[i]);
            }
        }

        StringBuilder ans = new StringBuilder();
        stack.forEach(k -> {
            ans.append(k);
        });

        return ans.toString();
    }


    /**
     * learned from leetcode submissions.
     * <p>
     * Main problem of my solution is it still store tmp value as character, a lot characters will be revisited
     * that is not very efficient.
     */
    // 100.00% 0ms 100.00%
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack();
        Stack<String> resStack = new Stack();

        int index = 0;
        while (index < s.length()) {

            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = 10 * count + (s.charAt(index) - '0');
                    index++;
                }
                countStack.push(count);
            } else if (s.charAt(index) == '[') {
                // push current res to stack, create a new res to store whatever is behind [
                resStack.push(res);
                res = "";
                index++;
            } else if (s.charAt(index) == ']') {
                // decode res when ] is reached
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                index++;
            } else {
                res = res + s.charAt(index++);
            }
        }
        return res;
    }


    /**
     * Learned from leetcode discussion.
     * Recursive. helper(s) consumes one layer of "[ ]".
     */
    int idx;

    // 100% 100%
    public String decodeStringRecursive(String s) {
        idx = 0;
        return helper(s);
    }

    String helper(String s) {
        StringBuilder ans = new StringBuilder();
        //
        for (; idx < s.length(); idx++) {
            int k = 0;
            char ch = s.charAt(idx);
            if (ch == '[') {
                idx++;
                String str = helper(s);
                while (k > 0) {
                    ans.append(str);
                    --k;
                }
            } else if (ch == ']') {
                // break out the for loop for return
                break;
            } else if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else
                ans.append(ch);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeStringRecursive("2[ab]c3[de]"));
    }
}
