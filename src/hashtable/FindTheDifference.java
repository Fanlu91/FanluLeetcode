package hashtable;

// Source : https://leetcode.com/problems/find-the-difference/
// Id     : 389
// Author : Fanlu Hai
// Date   : 2018-06-15
// Topic  : Hashtable
// Level  : Easy
// Other  :
// Tips   :
// Result : 99.37% 19.48%
public class FindTheDifference {
    //99.37% 8.14%
    public char findTheDifferenceOrigin(String s, String t) {
        int[] count = new int[26];
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        for (int i = 0; i < s1.length; i++) {
            count[t1[i] - 'a']++;
            count[s1[i] - 'a']--;
        }
        System.out.println(count.toString());
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0)
                return (char) ('a' + i);
        }
        return t1[t1.length - 1];
    }

    // 99.37% 19.48%
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[t.charAt(i) - 'a']++;
            count[s.charAt(i) - 'a']--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1)
                return (char) ('a' + i);
        }
        return t.charAt(t.length() - 1);
    }
}
