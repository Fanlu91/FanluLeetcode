package bitmanipulation;

// Source : https://leetcode.com/problems/sum-of-two-integers/
// Id     : 371
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-02
// Topic  : Bit Manipulation
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100% 8.00%
public class SumOfTwoIntegers {
    /**
     * 二进制每位相加就相当于各位做异或操作 a1
     * 各位进行与操作并左移一位得到进位值  b1
     * <p>
     * 重复直到 进位值bn = 0
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            int temp = a ^ b;

            a = temp;
            b = carry;
        }
        return a;
    }
}
