package backtracking;
// Source : https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// Id     : 17
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/11
// Topic  : backtracking
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 84.17%

import java.util.LinkedList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    // 0ms
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits.length() == 0)
            return res;
        String[] map = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        backtracking(digits, 0, map, res, new StringBuilder());
        return res;
    }

    private void backtracking(String digits, int i, String[] map, List<String> res, StringBuilder stringBuilder) {
        if (i == digits.length()) {
            res.add(stringBuilder.toString());
            return;
        }

        int num = Character.getNumericValue(digits.charAt(i));
        for (char c : map[num].toCharArray()) {
            stringBuilder.append(c);
            backtracking(digits, i + 1, map, res, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}