package backtracking;
// Source : https://leetcode.com/problems/letter-case-permutation/
// Id     : 784
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Easy
// Date   : 2019-07-09
// Other  :
// Tips   :
// Result : 100.00% 99.36%

import java.util.LinkedList;
import java.util.List;

public class LetterCasePermutation {
    List<String> solution = new LinkedList<>();


    public List<String> letterCasePermutation(String S) {
        char[] chars = S.toCharArray();
        backTrack(chars, 0);
        return solution;
    }

    // 43.67% 4 ms 99.47%
    public void backTrackOrigin(char[] chars, int i) {
        if (i == chars.length) {
            solution.add(new String(chars));
            return;
        }

        backTrack(chars, i + 1);
        if (chars[i] - '9' > 0) {
            char[] tmpChars = chars.clone();

            if (chars[i] - 'a' >= 0) { // lower to upper
                tmpChars[i] = (char) (tmpChars[i] - 32);
                backTrack(tmpChars, i + 1);
            } else { // upper to lower
                tmpChars[i] = (char) (tmpChars[i] + 32);
                backTrack(tmpChars, i + 1);
            }
        }
    }

    // it is actually not that big of a difference, the same submission has 3ms run time as well.
    // I think it could not be optimized so I submitted a few times more, 100% 1ms 84% 2ms 63% 3ms all showed.
    // 100.00% 1ms 99.36%
    public void backTrack(char[] chars, int i) {
        if (i == chars.length) {
            solution.add(new String(chars));
            return;
        }

        if (chars[i] > '9') {
            chars[i] = Character.toLowerCase(chars[i]);
            backTrack(chars, i + 1);

            chars[i] = Character.toUpperCase(chars[i]);
            backTrack(chars, i + 1);

        } else {
            backTrack(chars, i + 1);
        }
    }
}
