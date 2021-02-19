package string;
// Source : https://leetcode.com/problems/isomorphic-strings/
// Id     : 205
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/11
// Topic  : string 
// Level  : Easy-
// Other  :
// Tips   : Isomorphic 同构。
// Links  :
// Result : 81.95% 34.68%

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicStrings {
    // 43.61% 17ms 32.16%
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        if (s.length() == 0)
            return true;
        Map<Character, Character> s2tmap = new HashMap<>(26);
        Set<Character> mapedT = new HashSet<>(26);
        for (int i = 0; i < s.length(); i++) {
            if (s2tmap.containsKey(s.charAt(i))) {
                if (!s2tmap.get(s.charAt(i)).equals(t.charAt(i)))
                    return false;
            } else {
                if (mapedT.contains(t.charAt(i)))
                    return false;
                s2tmap.put(s.charAt(i), t.charAt(i));
                mapedT.add(t.charAt(i));
            }
        }
        return true;
    }

    // 81.95% 7ms 34.68%
    public boolean isIsomorphic1(String s, String t) {
//    public boolean isIsomorphic(String s, String t) {
        int[] sPlacement = new int[128];
        int[] tPlacement = new int[128];

        int NOT_ZERO = 1;
        for (int i = 0; i < s.length(); i++) {
            if (sPlacement[s.charAt(i)] != tPlacement[t.charAt(i)])
                return false;
            sPlacement[s.charAt(i)] = tPlacement[t.charAt(i)] = i + NOT_ZERO;
        }
        return true;
    }
}