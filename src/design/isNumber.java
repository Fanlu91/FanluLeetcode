package design;
// Source : https://leetcode.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
// Id     : Offer20
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/19
// Topic  : math 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 66.54% 58.76%

public class isNumber {
    /**
     * 1-10 . + - e 之外都不合法
     * + - 前不能有除e之外的字符
     * e 之后不能有.
     * e/. 都只能出现一次
     * e的前一位不能是. + -
     * "0012e+05"  is ok
     * <p>
     * <p>
     * 最后一位不能是非数字
     * 数字两边可以有空格，但是中间插空格不行
     * <p>
     * 3. true 这个不敢苟同
     * "46.e3" true 这个也不敢苟同
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        boolean hasE = false, hasD = false, hasN = false;
        char pre = 'n'; // none

        for (char c : s.trim().toLowerCase().toCharArray()) {
            switch (c) {
                case 'e':
                    if (!hasN) return false;
                    if (hasE) return false;
                    hasE = true;
                    if (pre == '+' || pre == '-')
                        return false;
                    break;
                case '.':
                    if (hasD || hasE) return false;
                    hasD = true;
                    break;
                case '+': // merge with '-'
                case '-':
                    if (pre != 'n' && pre != 'e')
                        return false;
                    break;
                default:
                    hasN = true;
                    if (!Character.isDigit(c))
                        return false;
            }
            pre = c;
        }
        if (pre == '.' && hasN) // 3. 这个不敢苟同
            return true;
        return Character.isDigit(pre);
    }
}