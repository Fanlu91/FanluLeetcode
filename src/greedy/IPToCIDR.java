package greedy;
// Source : https://leetcode.com/problems/ip-to-cidr/
// Id     : 751
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/14
// Topic  : Greedy
// Level  : Easy+
// Other  :
// Tips   : 题解 https://leetcode-cn.com/problems/ip-to-cidr/solution/ke-neng-shi-mu-qian-wei-yi-yi-ge-yi-dong-de-ti-jie/
// Links  :
// Result : 100.00% 100.00%

import java.util.ArrayList;
import java.util.List;

public class IPToCIDR {

    // 41.89% 23 ms 100.00%
    public List<String> ipToCIDR(String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList();

        while (n > 0) {
            int mask = Math.max(33 - bitLength(Long.lowestOneBit(start)),
                    33 - bitLength(n));
            ans.add(longToIP(start) + "/" + mask);
            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
        }
        return ans;
    }

    private long ipToLong(String ip) {
        long ans = 0;
        for (String x : ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }

    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
                x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }

    private int bitLength(long x) {
        if (x == 0)
            return 1;
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }

    // 100.00% 2 ms 100.00%
    public List<String> ipToCIDR1(String ip, int n) {
//        public List<String> ipToCIDR (String ip,int n){
        int start = toInt(ip);//将ip转化为整数
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            int trailingZeros = Integer.numberOfTrailingZeros(start);
            int mask = 0,bitsInCIDR = 1;
            //计算mask
            while (bitsInCIDR < n && mask < trailingZeros) {
                bitsInCIDR <<= 1;
                mask++;
            }
            if (bitsInCIDR > n) {
                bitsInCIDR >>= 1;
                mask--;
            }

            ans.add(toString(start, 32 - mask));
            n -= bitsInCIDR;
            start += (bitsInCIDR);
        }
        return ans;
    }

    private String toString(int number, int range) {
        final int WORD_SIZE = 8;
        StringBuilder buffer = new StringBuilder();
        for (int i = 3; i >= 0; --i) {
            buffer.append(((number >> (i * WORD_SIZE)) & 255));
            buffer.append(".");
        }
        buffer.setLength(buffer.length() - 1);
        buffer.append("/").append(range);
        return buffer.toString();
    }

    private int toInt(String ip) {
        String[] strs = ip.split("\\.");
        int sum = 0;
        for (String str : strs) {
            sum *= 256;
            sum += Integer.parseInt(str);
        }
        return sum;
    }
}