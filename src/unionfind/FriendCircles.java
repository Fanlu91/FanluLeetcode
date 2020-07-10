package unionfind;

// Source : https://leetcode.com/problems/friend-circles/
// Id     : 547
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Union Find
// Level  : medium
// Other  :
// Tips   :
// Result : 99.93% 96.00%

public class FriendCircles {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UoinFind uoinFind = new UoinFind(n);
        for (int i = 0; i < n; i++) {
            // check i with i+1 ... n-1
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1)
                    uoinFind.union(i, j);
            }
        }
        return uoinFind.count;
    }

    class UoinFind {
        int[] parent;
        int[] size;
        int count;

        public UoinFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[p];
            }
            count--;
        }

        public int find(int n) {
            while (parent[n] != n) {
                // 进行路径压缩
                parent[n] = parent[parent[n]];
                n = parent[n];
            }
            return n;
        }

        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

    }
}
