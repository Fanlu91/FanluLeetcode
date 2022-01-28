package findpattern;
// Source : https://leetcode.com/problems/one-away-lcci/
// Id     : mst01.05
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/28
// Topic  : findpattern 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 5.04%

public class OneAwayLCCI {
    // 0ms
    public boolean oneEditAway(String first, String second) {
        if (second.length() > first.length())
            return oneEditAway(second, first);
        if (first.length() - second.length() > 1)
            return false;
        int edit = 1;
        int i = 0, j = 0;
        if (first.length() == second.length()) {
            while (i < first.length()) {
                if (first.charAt(i) != second.charAt(j)) {
                    edit--;
                }
                i++;
                j++;
            }
        } else {
            while (i < first.length()) {
                // System.out.println(i+" "+j);
                if (j == second.length())
                    return edit == 1 ? true : false;
                if (first.charAt(i) == second.charAt(j)) {
                    i++;
                    j++;
                } else {
                    if (i != first.length() - 1 && first.charAt(i + 1) == second.charAt(j)) { // 插入/删除字符继续）
                        edit--;
                        i++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return edit < 0 ? false : true;
    }
}