package permutationcombination;
// Source : https://leetcode.com/problems/permutation-sequence/
// Id     : 60
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/19
// Topic  : Math
// Level  : Hard
// Other  :
// Tips   : 理解排列原理；看懂题目要求的顺序是什么。
// Links  :
// Result : 57.64% 43.94%


import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    // 57.64% 2 ms 43.94%
    public String getPermutation(int n, int k) {

        /**
         * Permutation n! 种可能
         * 当 k 不比（n - 1）! 大时，第一位不需要变
         * 以此类推，先找出保留不变的位数
         */
        int[] fac = new int[n + 1];
        fac[0] = 1;
        int shift = 0;
        while (k > fac[shift]) {
            shift++;
            fac[shift] = shift * fac[shift - 1];
            if (k < fac[shift])
                break;
        }
        int fixed = n - shift;
        StringBuilder sb = new StringBuilder();
        int x = 1;
        while (fixed - x >= 0) {
            sb.append(x);
            x++;
        }
        // 如果已经构成了等长的字符串，直接返回（处理k=1 的情况）
        if (sb.length() == n)
            return sb.toString();

        List<Integer> remaining = new ArrayList<>(shift);
        for (; x < n + 1; x++)
            remaining.add(x);


        /**
         * 求k的结果即 求偏移 k - 1 （我也是测试过程才发现）
         * 如果k =1 不需要任何变化
         * k = 2 是偏移 1 的变化。
         * 比如 1 2 3
         * k = 1 -》 123
         * k = 2 -》 132
         */
        k--;
        /**
         * 从需要变化的位置开始
         * 通过 k % fac[i] 的方式，计算需要偏移的位数
         */
        int quotient = 0, remainder = 0;
        for (int i = shift - 1; i > 0; i--) {
            quotient = k / fac[i];
            remainder = k % fac[i];
            sb.append(remaining.get(quotient));
            remaining.remove(quotient);
            k = remainder;
        }
        sb.append(remaining.get(0));

        return sb.toString();
    }

    public static void main(String[] args) {
        PermutationSequence p = new PermutationSequence();
        System.out.println(p.getPermutation(3, 1));
    }
}