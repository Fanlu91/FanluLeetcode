package stringmatch;

// Source : https://leetcode.com/problems/bold-words-in-string/
// Id     : 758
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-12
// Topic  : String Match
// Level  : Easy +
// Other  : Same with 616
// Tips   :
// Result : 96.18% 100.00%


public class BoldWordsInString {
    // 7.63% 65 ms 100.00%
    public String boldWords(String[] words, String S) {
        boolean[] boldCharIndex = new boolean[S.length()];
        for (String word : words) {
            for (int i = 0; i < S.length() - word.length() + 1; i++) {
                boolean match = true;
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(j) != S.charAt(i + j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int j = 0; j < word.length(); j++)
                        boldCharIndex[i + j] = true;
                    match = false;
                }
            }
        }
        return getBoldWords(S, boldCharIndex);
    }

    private String getBoldWords(String s, boolean[] boldCharIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean started = false;
        for (int i = 0; i < s.length(); i++) {
            if (started) {
                if (boldCharIndex[i] != true) {
                    stringBuilder.append("</b>");
                    started = false;
                }
                stringBuilder.append(s.charAt(i));
            } else {
                if (boldCharIndex[i] == true) {
                    stringBuilder.append("<b>");
                    started = true;
                }
                stringBuilder.append(s.charAt(i));
            }
        }
        if (started)
            stringBuilder.append("</b>");

        return stringBuilder.toString();
    }

    /**
     * used String.index which is more efficient
     *
     * @param words
     * @param S
     * @return
     */
    // 96.18% 6ms
    public String boldWords1(String[] words, String S) {
//    public String boldWords(String[] words, String S) {
        boolean[] isBold = new boolean[S.length()];
        for (String word : words) {
            int n = S.indexOf(word, 0);
            while (n != -1) {
                for (int i = n; i < n + word.length(); i++)
                    isBold[i] = true;
                n = S.indexOf(word, n + 1);
            }
        }
        return getBoldWords(S, isBold);
    }
}
