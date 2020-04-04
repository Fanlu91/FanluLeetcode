package hashtable;

// Source : https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
// Id     : 1160
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Hashtable
// Level  : Easy
// Other  :
// Tips   :
// Result : 84.18% 5.08%

import java.util.Arrays;

public class FindWordsThatCanBeFormedbyCharacters {
    // 84.18% 7ms 5.08%
    public int countCharacters(String[] words, String chars) {
        int ans = 0;
        int[] table = new int[26];
        for (char c : chars.toCharArray()) {
            table[c - 'a']++;
        }

        for (String word : words) {
            int[] tmp = Arrays.copyOf(table, 26);
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (tmp[c - 'a'] == 0) {
                    flag = false;
                    break;
                }
                tmp[c - 'a']--;
            }
            if (flag)
                ans += word.length();
        }
        return ans;
    }
}
