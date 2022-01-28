package pureprogramming;
// Source : https://leetcode.com/problems/tic-tac-toe-lcci/
// Id     : mst16.04
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/28
// Topic  : pureprogramming 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 75.80% 5.19%

public class TicTacToeLCCI {
    // 3ms
    public String tictactoe(String[] board) {
        int n = board.length;
        boolean[] emptyCol = new boolean[n];
        for (String s : board) {
            char tmp = s.charAt(0);
            if (tmp == ' ') {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ')
                        emptyCol[i] = true;
                }
            } else {
                boolean winning = true;
                for (int i = 1; i < s.length(); i++) {
                    if (s.charAt(i) != tmp) {
                        winning = false;
                        if (s.charAt(i) == ' ')
                            emptyCol[i] = true;
                    }
                }
                if (winning)
                    return Character.toString(tmp);
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (!emptyCol[i]) {
                char tmp = board[0].charAt(i);
                boolean winning = true;
                for (int j = 1; j < board.length; j++) {
                    if (board[j].charAt(i) != tmp) {
                        winning = false;
                        break;
                    }
                }
                if (winning)
                    return Character.toString(tmp);
            }
        }

        if (board[0].charAt(0) != ' ') {
            char tmp = board[0].charAt(0);
            int i = 1;
            boolean winning = true;
            while (i != n) {
                if (board[i].charAt(i) != tmp) {
                    winning = false;
                    break;
                }
                i++;
            }
            if (winning)
                return Character.toString(tmp);
        }

        if (board[0].charAt(n - 1) != ' ') {
            char tmp = board[0].charAt(n - 1);
            int i = 1;
            boolean winning = true;
            while (i != n) {
                if (board[i].charAt(n - 1 - i) != tmp) {
                    winning = false;
                    break;
                }
                i++;
            }
            if (winning)
                return Character.toString(tmp);
        }

        boolean noDraw = false;
        for (boolean b : emptyCol) {
            noDraw |= b;
        }
        return noDraw ? "Pending" : "Draw";
    }
}