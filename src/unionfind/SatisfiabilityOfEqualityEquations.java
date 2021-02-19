package unionfind;
// Source : https://leetcode.com/problems/satisfiability-of-equality-equations/
// Id     : 990
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Union Find
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 16.67%

public class SatisfiabilityOfEqualityEquations {
    public boolean equationsPossible(String[] equations) {
        UoinFind uoinFind = new UoinFind(26);
        for (String equal : equations) {
            if (equal.charAt(1) == '=')
                uoinFind.union(equal.charAt(0) - 'a', equal.charAt(3) - 'a');
        }

        for (String equal : equations) {
            if (equal.charAt(1) == '!') {
                if (uoinFind.connected(equal.charAt(0) - 'a', equal.charAt(3) - 'a'))
                    return false;
            }
        }
        return true;
    }

    class UoinFind {
        int[] parent;
        int[] size;

        public UoinFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
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
        }

        public int find(int n) {
            while (parent[n] != n) {
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

    public static void main(String[] args) {
        SatisfiabilityOfEqualityEquations s = new SatisfiabilityOfEqualityEquations();
        System.out.println(s.equationsPossible(new String[]{"a==b", "b!=a"}));
    }
}
