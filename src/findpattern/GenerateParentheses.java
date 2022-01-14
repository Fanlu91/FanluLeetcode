package findpattern;
// Source : https://leetcode.com/problems/
// Id     : 
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/12
// Topic  : backtracking 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 68.18%

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenerateParentheses {
    // 0ms
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        backtracking(n, n, res, new StringBuilder());
        return res;
    }

    private void backtracking(int l, int r, List<String> res, StringBuilder stringBuilder) {
        if (l == 0 && r == 0) {
            res.add(stringBuilder.toString());
            return;
        }

        if (l > r) {
            return;
        }

        if (l > 0) {
            stringBuilder.append('(');
            backtracking(l - 1, r, res, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (r > 0) {
            stringBuilder.append(')');
            backtracking(l, r - 1, res, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    // 9ms
    public List<String> generateParenthesis1(int n) {
//    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if (n == 0) {
            res.add("");
            return res;
        }
        for (int i = 0; i < n; i++) {
            int j = n - i - 1;
            List<String> iRes = generateParenthesis(i);
            List<String> jRes = generateParenthesis(j);
            for (String s1 : iRes) {
                for (String s2 : jRes) {
                    res.add(s1 + "(" + s2 + ")");
                }
            }
        }
        return res;
    }

    // 8ms
    public List<String> generateParenthesis2(int n) {
//     public List<String> generateParenthesis(int n) {
        Map<Integer, List<String>> mem = new HashMap<>();
        return dp(n, mem);
    }

    private List<String> dp(int n, Map<Integer, List<String>> mem) {
        if (mem.containsKey(n))
            return mem.get(n);
        List<String> res = new LinkedList<>();
        if (n == 0) {
            res.add("");
            return res;
        }
        for (int i = 0; i < n; i++) {
            int j = n - i - 1;
            List<String> iRes = dp(i, mem);
            List<String> jRes = dp(j, mem);
            for (String s1 : iRes) {
                for (String s2 : jRes) {
                    res.add(s1 + "(" + s2 + ")");
                }
            }
        }
        mem.put(n, res);
        return res;
    }


    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(generateParentheses.generateParenthesis(3));
    }
}