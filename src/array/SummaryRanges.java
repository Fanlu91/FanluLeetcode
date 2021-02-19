package array;
// Source : https://leetcode.com/problems/summary-ranges/
// Id     : 228
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/11
// Topic  : array 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 8.88% 26.69%

import java.util.LinkedList;
import java.util.List;

public class SummaryRanges {
    // 8.88% 10 ms 26.69%
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new LinkedList<>();
        if (nums.length == 0)
            return ans;
        String tmp = "" + nums[0];
        int tmpSize = 1;
        if (nums.length == 1) {
            ans.add(tmp);
            return ans;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                tmpSize++;
            } else {
                if (tmpSize == 1)
                    ans.add(tmp);
                else
                    ans.add(tmp + "->" + nums[i - 1]);
                tmpSize = 1;
                tmp = "" + nums[i];
            }
        }

        if (tmpSize == 1)
            ans.add(tmp);
        else
            ans.add(tmp + "->" + nums[nums.length - 1]);

        return ans;
    }


    /**
     * union find
     */
//    public List<String> summaryRanges(int[] nums) {
//        List<String> ans = new LinkedList<>();
//
//    }
//
//    class UoinFind {
//        int[] parent;
//        int[] size;
//        int count;
//
//        public UoinFind(int n) {
//            this.parent = new int[n];
//            this.size = new int[n];
//            this.count = n;
//            for (int i = 0; i < n; i++) {
//                parent[i] = i;
//                size[i] = 1;
//            }
//        }
//
//        public void union(int p, int q) {
//            int rootP = find(p);
//            int rootQ = find(q);
//            if (rootP == rootQ)
//                return;
//            if (size[rootP] > size[rootQ]) {
//                parent[rootQ] = rootP;
//                size[rootP] += size[rootQ];
//            } else {
//                parent[rootP] = rootQ;
//                size[rootQ] += size[p];
//            }
//            count--;
//        }
//
//        public int find(int n) {
//            while (parent[n] != n) {
//                // 进行路径压缩
//                parent[n] = parent[parent[n]];
//                n = parent[n];
//            }
//            return n;
//        }
//
//        public boolean connected(int p, int q) {
//            int rootP = find(p);
//            int rootQ = find(q);
//            return rootP == rootQ;
//        }
//
//    }
}