package greedy;

// Source : https://leetcode.com/problems/is-subsequence/
// Id     : 392
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Greedy
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class IsSubsequence {

    // 64.66% 16 ms 100.00%
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        if (t.length() < s.length())
            return false;
        int j = 0;

        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (j == s.length())
                    return true;
            }
        }
        return false;
    }


    /**
     * size of the byte variable is 8 bit while the size of the char variable is 16 bit.
     *
     * @param s
     * @param t
     * @return
     */
    // 100.00% 0ms 100.00%
    public boolean isSubsequenceImprove(String s, String t) {
//    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        if (t.length() < s.length())
            return false;

        int index = -1;
        for (char c : s.toCharArray()) {
            // indexOf is also O(n) to my understanding, but it uses byte instead of char
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    // not sure why this is not more efficient
    //  76.17% 11 ms 100.00%
    public boolean isSubsequenceExp(String s, String t) {
//    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        if (t.length() < s.length())
            return false;

        byte[] tb = t.getBytes();
        byte[] sb = s.getBytes();

        int j = 0;
        for (int i = 0; i < tb.length; i++) {
            if (tb[i] == sb[j]) {
                j++;
                if (j == s.length())
                    return true;
            }
        }
        return false;
    }
}
