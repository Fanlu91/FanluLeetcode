package bitmanipulation;
// Source : https://leetcode.com/problems/reverse-bits/
// Id     : 190
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/8/12
// Topic  : bitmanipulation 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 92.12%

public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
//            System.out.println(i+" "+ n + " " + (n & 1));
            // n & 1 得到 n 最低位是0还是1
            // 通过左移把最低位挪动到reverse之后应该在的位置
            res |= (n & 1) << (31 - i);
            // 逻辑右移，最左侧补零，这样配合或运算可以将或的值赋给这位
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        ReverseBits reverseBits = new ReverseBits();
        System.out.println(reverseBits.reverseBits(43261596));

    }
}