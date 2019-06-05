package array;

// Source : https://leetcode.com/problems/find-common-characters/
// Id     : 1002
// Author : Fanlu Hai
// Date   : 2018-06-05
// Topic  : Array
// Other  :
// Tips   :
// Result : 100.00%  98.16%

import java.util.ArrayList;
import java.util.List;

public class FindCommonCharacters {
    // 83.23% 5 ms 99.62%
    public List<String> commonCharsOrigin(String[] A) {

        int[] result = new int[26];
        char[] cList = A[0].toCharArray();
        for (char c : cList) {
            result[c - 'a']++;
        }

        int[] tmpResult;
        char[] tmpCList;

        for (int i = 1; i < A.length; i++) {
            tmpResult = result.clone();
            tmpCList = A[i].toCharArray();
            for (char c : tmpCList) {
                tmpResult[c - 'a'] = tmpResult[c - 'a'] - 1;
            }
            // check the difference and update result accordingly
            for (int j = 0; j < 26; j++) {
                // if result[j] == 0, it means exact match
                // else if tmpResult[j] >0, it means it has less char, we need to reduce result accordingly
                if (result[j] != 0) {
                    if (tmpResult[j] > 0)
                        result[j] = result[j] - tmpResult[j];
                }
            }
        }

  /*      for (int x : result) {
            System.out.print(x + ",");
        }*/

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (result[i] != 0) {
                for (int j = 0; j < result[i]; j++) {
                    ans.add((char) ('a' + i) + "");
                }
            }
        }
        return ans;

    }

    // 88.46% 4 ms 99.68%
    public List<String> commonCharsSlightlyBetter(String[] A) {

        int[] result = new int[26];

        for (char c : A[0].toCharArray()) {
            result[c - 'a']++;
        }

        int[] tmpResult;

        for (int i = 1; i < A.length; i++) {
            tmpResult = result.clone();

            for (char c : A[i].toCharArray()) {
                tmpResult[c - 'a']--;
            }
            // check the difference and update result accordingly
            for (int j = 0; j < 26; j++) {
                // if result[j] == 0, it means exact match
                // if tmpResult[j] >0, it means it has less char, we need to reduce result[] accordingly
                if (result[j] != 0 && tmpResult[j] > 0) {
                    result[j] -= tmpResult[j];
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (result[i] != 0) {
                for (int j = 0; j < result[i]; j++) {
                    ans.add((char) ('a' + i) + "");
                }
            }
        }
        return ans;

    }

    //
/*
    public List<String> commonChars(String[] A) {

        int[] result = new int[26];

        for (char c : A[0].toCharArray()) {
            result[c - 'a']++;
        }

        int[] tmpResult;

        for (int i = 1; i < A.length; i++) {
            tmpResult = result.clone();

            for (char c : A[i].toCharArray()) {
                tmpResult[c - 'a']--;
            }
            // check the difference and update result accordingly
            for (int j = 0; j < 26; j++) {
                // if result[j] == 0, it means exact match
                // if tmpResult[j] >0, it means it has less char, we need to reduce result[] accordingly
                if (result[j] != 0 && tmpResult[j] > 0) {
                    result[j] -= tmpResult[j];
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (result[i] != 0) {
                for (int j = 0; j < result[i]; j++) {
                    ans.add((char) ('a' + i) + "");
                }
            }
        }
        return ans;

    }
*/


    int[] countMap = new int[26];

    // learned from leetcode submission
    // instead of compare using 0, it starts with Integre.MAX to simplify the problems.
    // 100.00% 1ms 98.16%
    public List<String> commonChars(String[] A) {
        for (int i = 0; i < 26; i++)
            countMap[i] = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            updateMap(A[i]);
        }

        List<String> ansLs = new ArrayList<>();

        for (int num = 0; num < countMap.length; num++) {
            int length = countMap[num];
            if (length == Integer.MAX_VALUE)
                length = 0;

            for (int i = 0; i < length; i++)
                ansLs.add(Character.toString((char) (num + 'a')));
        }
        return ansLs;
    }

    private void updateMap(String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++)
            countMap[i] = Math.min(countMap[i], count[i]);
    }

    public static void main(String[] args) {
        FindCommonCharacters f = new FindCommonCharacters();
        System.out.println(f.commonChars(new String[]{"abcc", "acc"}).toString());
    }
}
