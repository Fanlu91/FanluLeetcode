package jianzhioffer;
// Source : https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
// Id     : 10062
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/19
// Topic  : jianzhioffer 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 99.98% 100.00%

public class LCOF {
    /**
     * 约瑟夫环;
     * f(n,m) = [f(n-1, m) + m] \% n
     * 当数到最后一个结点不足m个时，需要跳到第一个结点继续数。
     * 每轮都是上一轮被删结点的下一个结点开始数 m 个。
     *
     * n个人删掉的第一个人的编号是(m-1)%n
     *
     * f(n,m)=[(m-1)%n+x+1]%n
     *       =[(m-1)%n%n+(x+1)%n]%n
     *       =[(m-1)%n+(x+1)%n]%n
     *       =(m-1+x+1)%n
     *       =(m+x)%n
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        int pos = 0; // 最终活下来那个人的初始位置
        for(int i = 2; i <= n; i++){
            pos = (pos + m) % i;  // 每次循环右移
        }
        return pos;
    }
}