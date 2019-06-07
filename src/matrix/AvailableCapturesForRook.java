package matrix;
// Source : https://leetcode.com/problems/available-captures-for-rook/
// Id     : 999
// Author : Fanlu Hai
// Date   : 2018-06-05
// Topic  : Matrix
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 99.95%

public class AvailableCapturesForRook {

    public int numRookCaptures(char[][] board) {
        int rowR = 0, columnR = 0, count = 0;
        boolean found = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'R') {
                    rowR = i;
                    columnR = j;
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
        if (!found)
            return 0;

        if (columnR != 0) {
            for (int i = columnR - 1; i >= 0; i--) {
                if (board[rowR][i] == 'B') {
                    break;
                }
                if (board[rowR][i] == 'p') {
                    count++;
                    break;
                }
            }
        }


        if (columnR != board.length - 1) {
            for (int i = columnR + 1; i < board.length; i++) {
                if (board[rowR][i] == 'b') {
                    break;
                }
                if (board[rowR][i] == 'p') {
                    count++;
                    break;
                }
            }

        }

        if (rowR != 0) {
            for (int i = rowR - 1; i >= 0; i--) {
                if (board[i][columnR] == 'b') {
                    break;
                }
                if (board[i][columnR] == 'p') {
                    count++;
                    break;
                }
            }

        }
        if (rowR != board.length - 1) {
            for (int i = rowR + 1; i < board.length; i++) {
                if (board[i][columnR] == 'b') {
                    break;
                }
                if (board[i][columnR] == 'p') {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
