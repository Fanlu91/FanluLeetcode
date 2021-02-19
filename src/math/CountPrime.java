package math;
// Source : https://leetcode.com/problems/count-primes/
// Id     : 204
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/11
// Topic  : math 
// Level  : Easy+
// Other  :
// Tips   : 最小的质数是2；求质数的一般方法时间复杂度是sqrt{n}；与质数相反的合数 Composite number；任何一个合数(非质数)，都可以以唯一的形式被写成有限个质数的乘积，即分解质因数。;厄拉多塞筛法（埃氏筛）、线性筛此类优化解在短时间内是很难想到的。
// Links  :
// Result : 80% 80%

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountPrime {
    // 时间复杂度为 O(sqrt{n})
    // 对正整数 n ，如果用 2 到 √n 之间(包含边界)的所有整数去除，均无法整除，则 n 为质数。
    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 13.00% 699 ms 84.63%
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    //! 不符合题意，题目要求比n小的质数，下面的逻辑带上了n，同时因为使用递归在方法内没法只做一次n-1。
    public int countPrimesWrong(int n) {
        if (n < 2)
            return 0;
        return isPrime(n) ? countPrimesWrong(n - 1) + 1 : countPrimesWrong(n - 1);
    }

    /**
     * Eratosthenes 厄拉多塞筛法（埃氏筛）
     * <p>
     * 1.如果 x 是质数，那么x的倍数 2x,3x… 一定不是质数
     * 2.且从最小的质数2开始算起，非质数（合数 Composite number）一定是前面质数的倍数
     * <p>
     * 进一步推导出 线性筛
     * - 不仅当 x 为质数时才进行排除（排除 x*x），而是对每个整数 x 都进行，标记质数集合中的数与 x 相乘的数
     * - 每个合数只被标记一次
     * <p>
     * 任何一个合数(非质数)，都可以以唯一的形式被写成有限个质数的乘积，即分解质因数。
     * 因此，标记 n 以内所有数，并从小到大
     * - 记录质数出现次数
     * - 排除构成的合数
     * 最终就能解决问题。
     *
     * @param n
     * @return
     */
    public int countPrimes1(int n) {
//    public int countPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }

            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0)
                    break;
            }
        }
        return primes.size();
    }


    public static void main(String[] args) {
        CountPrime c = new CountPrime();
        System.out.println(c.countPrimes(2));
    }
}