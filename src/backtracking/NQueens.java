package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Source : https://leetcode.com/problems/n-queens/
// Id     : 51
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Hard-
// Date   : 2019-07-09
// Other  :
// Tips   :
// Result : 100% 100%

public class NQueens {
    List<List<String>> resultList = new LinkedList<>();
    int puzzleSize = 0;

    // 39.52% 5 ms 98.03%
    public List<List<String>> solveNQueensOrigin(int n) {
        puzzleSize = n;

        for (int i = 0; i < n; i++) {
            List<String> result = new ArrayList<>(n);
            result.add(constructString(i));

            placeQueens(result, 1);
        }
        return resultList;
    }

    // place queen to row according to current result(contains row-1 strings).
    public void placeQueens(List<String> result, int row) {
        if (row == puzzleSize)
            resultList.add(result);

        for (int column = 0; column < puzzleSize; column++) {
            if (isOK(result, row, column)) {
                List<String> tmp = new ArrayList<>();
                tmp.addAll(result);
                tmp.add(constructString(column));
                placeQueens(tmp, row + 1);
            }
        }
    }

    public boolean isOK(List<String> list, int row, int column) {
        int leftup = column - 1, rightup = column + 1;

        // 从当前行的上一行开始，逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            // 第 i 行的 column 列有棋子吗？
            if (list.get(i).charAt(column) == 'Q')
                return false;
            if (leftup >= 0) { // 考察左上对角线：第 i 行 leftup 列有棋子吗？
                if (list.get(i).charAt(leftup) == 'Q')
                    return false;
            }
            if (rightup < puzzleSize) { // 考察右上对角线：第 i 行 rightup 列有棋子吗？
                if (list.get(i).charAt(rightup) == 'Q')
                    return false;
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    private String constructString(int indexOfQ) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < indexOfQ; j++) {
            builder.append(".");
        }
        builder.append("Q");
        for (int j = indexOfQ + 1; j < puzzleSize; j++) {
            builder.append(".");
        }
        return builder.toString();
    }


    /***
     * try to improve by using List of int[] to define the puzzle, only convert to string when return.
     *
     */
    // 74.14% 3 ms 100.00%
    public List<List<String>> solveNQueensImprove(int n) {
        puzzleSize = n;

        for (int i = 0; i < n; i++) {
            int[] result = new int[n];
            result[0] = i;
            placeQueens(result, 1);
        }
        return resultList;
    }

    public void placeQueens(int[] result, int row) {
        if (row == puzzleSize) {
            resultList.add(constructStringList(result));
        }
        for (int column = 0; column < puzzleSize; column++) {
            if (isOK(result, row, column)) {
                int[] tmp = result.clone();
                tmp[row] = column;
                placeQueens(tmp, row + 1);
            }
        }
    }

    public boolean isOK(int[] result, int row, int column) {
//        System.out.println(row + " " + column);
        int upleft = column - 1, upright = column + 1;

        for (int i = row - 1; i >= 0; --i) {
            if (result[i] == column)
                return false;
            if (upleft >= 0)
                if (result[i] == upleft)
                    return false;
            if (upright < puzzleSize)
                if (result[i] == upright)
                    return false;
            upleft--;
            upright++;
        }

        return true;
    }

    private List<String> constructStringList(int[] result) {
        List<String> tmp = new LinkedList<>();
        for (int i = 0; i < result.length; i++) {
            tmp.add(constructString(result[i]));
        }
        return tmp;
    }


    /***
     * Fastest solution learned from leetcode submission.
     * Besides diagonal tracking,
     * the recursion is pretty neat
     */
    private List<List<String>> solutions;
    private char[][] nQueens;
    private boolean[] columnUsed;
    private boolean[] diagonalUsed1;
    private boolean[] diagonalUsed2;
    private int n;

    // 100.00% 1ms 100.00%
    public List<List<String>> solveNQueens(int n) {
        solutions = new ArrayList<>();
        nQueens = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(nQueens[i], '.');
        }
        columnUsed = new boolean[n];
        diagonalUsed1 = new boolean[2 * n - 1];
        diagonalUsed2 = new boolean[2 * n - 1];
        this.n = n;
        backtracking(0);
        return solutions;
    }

    private void backtracking(int row) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (char[] chars : nQueens) {
                list.add(new String(chars));
            }
            solutions.add(list);
            return;
        }

        for (int column = 0; column < n; column++) {

            int diagonal1Index = row + column;
            int diagonal2Index = n - 1 - (row - column);

            if (columnUsed[column] || diagonalUsed1[diagonal1Index] || diagonalUsed2[diagonal2Index]) {
                continue;
            }
            nQueens[row][column] = 'Q';
            columnUsed[column] = diagonalUsed1[diagonal1Index] = diagonalUsed2[diagonal2Index] = true;

            backtracking(row + 1);
            columnUsed[column] = diagonalUsed1[diagonal1Index] = diagonalUsed2[diagonal2Index] = false;
            nQueens[row][column] = '.';
        }
    }


    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.solveNQueens(4);
        System.out.println(nQueens.resultList.size());
//        nQueens.puzzleSize=3;
//        nQueens.constructString(1);
    }
}
