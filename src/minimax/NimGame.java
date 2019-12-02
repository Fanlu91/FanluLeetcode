package minimax;

// Source : https://leetcode.com/problems/nim-game/
// Id     : 292
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-25
// Topic  : Minimax
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class NimGame {
    // 1 win, -1 lose
    int[] mem;

    // MLE
    public boolean canWinNimError(int n) {
        mem = new int[n + 1];
        return winCheck(n);
    }

    private boolean winCheck(int n) {
        if (n <= 3)
            return true;
        if (mem[n] != 0)
            return mem[n] == 1 ? true : false;

        boolean result = !(winCheck(n - 1) && winCheck(n - 2) && winCheck(n - 3));
        mem[n] = result ? 1 : -1;
        return result;
    }

    // Memory Limit Exceeded
    public boolean canWinNimMLE(int n) {
        boolean[] mem = new boolean[n + 1];
        mem[1] = true;
        mem[2] = true;
        mem[3] = true;

        for (int i = 4; i < n + 1; i++) {
            if (!(mem[i - 1] && mem[i - 2] && mem[i - 3]))
                mem[i] = true;
        }
        return mem[n];
    }

    // Time Limit Exceeded
    public boolean canWinNimTLE(int n) {
        if (n < 4)
            return true;

        boolean a = true, b = true, c = true;

        for (int i = 4; i < n + 1; i++) {
            boolean current = a && b && c ? false : true;
            a = b;
            b = c;
            c = current;
        }

        return c;
    }

    // result leetcode only accept
    public boolean canWinNim(int n) {
        if (n <= 0) {
            return false;
        }
        return 0 != n % 4;
    }


}
