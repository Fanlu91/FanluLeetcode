package stringmatch;
// Source : https://leetcode.com/problems/substring-with-concatenation-of-all-words/
// Id     : 30
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/16
// Topic  : stringmatch 
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 47.38% 5.00%

import java.util.*;

public class SubstringWithConcatenationOfAllWords {
    // 108 ms
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        for (int i = 0; i < s.length() - words[0].length() * words.length; i++) {
            Map<String, Integer> tmp = new HashMap<>();
            tmp.putAll(map);
            if (ifMatch(s, 0, words[0].length(), tmp))
                res.add(i);
        }

        return res;
    }

    private boolean ifMatch(String s, int start, int wordLen, Map<String, Integer> map) {
        if (map.isEmpty())
            return true;

        String word = s.substring(start, start + wordLen);

        if (map.containsKey(word)) {
            int count = map.get(word);

            if (count == 1)
                map.remove(word);
            else
                map.put(word, --count);
        } else
            return false;
        return ifMatch(s, start + wordLen, wordLen, map);
    }

    // 2ms
    public List<Integer> findSubstring1(String s, String[] words) {
//    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordLen = words.length;
        int totalLen = wordLen * words[0].length();
        int last = s.length() - totalLen;
        if (last < 0)
            return res;

        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.putIfAbsent(word, map.size());
        }
        int[] count = new int[map.size()];
        for (String word : words) {
            count[map.get(word)]++;
        }
        int[] curCount = new int[map.size()];
        for (int i = 0, start; (start = i) < words[0].length(); i++) {
            while (start <= last) {
                int curNum = 0;
                Arrays.fill(curCount, 0);
                for (int end = start + totalLen; end > start; end -= words[0].length()) {
                    String cur = s.substring(end - words[0].length(), end);
                    Integer check = map.get(cur);
                    if (check == null) {
                        start = end;
                        break;
                    }
                    if (++curCount[check] > count[check]) {
                        start = end;
                        break;
                    }
                    if (++curNum == wordLen) {
                        res.add(start);
                        start = end;
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords = new SubstringWithConcatenationOfAllWords();
        substringWithConcatenationOfAllWords.findSubstring("goodgoodbestwordwordgood", new String[]{"word", "good", "best", "good"});
    }
}
