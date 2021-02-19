package permutationcombination;
// Source : https://leetcode.com/problems/zi-fu-chuan-de-pai-lie-lcof/
// Id     : lcof38
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/19
// Topic  : Backtracking
// Level  : Medium
// Other  :
// Tips   : 和47几乎一样
// Links  : 47
// Result : 99.28% 49.61%

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringPermutation {

    // 91.26% 9 ms  56.98%
    public String[] permutation(String s) {
        List<String> ans = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        char[] sArray = s.toCharArray();
        Arrays.sort(sArray);

        backtracking(sArray, new StringBuilder(), visited, ans);
//        return ans.toArray(new String[ans.size()]);
        return ans.toArray(new String[0]); // intrinsified by jvm, performance will be better.
    }

    private void backtracking(char[] sArray, StringBuilder sb, boolean[] visited, List<String> ans) {
        if (sb.length() == sArray.length) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < sArray.length; i++) {
            if (visited[i])
                continue;

            if (i != 0 && sArray[i] == sArray[i - 1] && !visited[i - 1])
                continue;

            visited[i] = true;
            sb.append(sArray[i]);
            backtracking(sArray, sb, visited, ans);

            visited[i] = false;
            sb.setLength(Math.max(sb.length() - 1, 0));
        }
    }


    /**
     * in place
     *
     * @param s
     * @return
     */
    // 99.28% 5 ms 49.61%
    public String[] permutation1(String s) {
//    public String[] permutation(String s) {
        List<String> ans = new LinkedList<>();
        char[] sArray = s.toCharArray();
        helper(sArray, 0, ans);
        return ans.toArray(new String[0]);
    }

    void helper(char[] sArray, int index, List<String> ans) {
        if (index == sArray.length - 1) {
            ans.add(String.valueOf(sArray));
            return;
        }

        for (int i = index; i < sArray.length; i++) {
            // 剪枝
            boolean flag = true;
            for (int j = index; j < i; j++) {
                if (sArray[j] == sArray[i]) { // 靠前数字已经覆盖了靠后数字的排列
                    flag = false;
                    break;
                }
            }

            if (flag) {
                swap(sArray, i, index);
                helper(sArray, index + 1, ans);
                swap(sArray, i, index);
            }
        }
    }

    void swap(char[] arr, int left, int right) {
        char tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}