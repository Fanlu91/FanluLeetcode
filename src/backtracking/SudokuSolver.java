package backtracking;

// Source : https://leetcode.com/problems/sudoku-solver/
// Id     : 37
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Hard
// Date   : 2020-01-15
// Other  :
// Tips   :
// Result : 97.00% 94.25%

public class SudokuSolver {
    int redoCount = 0;

    // 90.95% 4ms 74.92%
    public void solveSudoku(char[][] board) {

        boolean rowState[][] = new boolean[9][10];
        boolean columnState[][] = new boolean[9][10];
        boolean boxState[][] = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '0';
                    rowState[i][index] = true;
                    columnState[j][index] = true;
                    /**
                     * Box state represented like below
                     * 0 1 2
                     * 3 4 5
                     * 6 7 8
                     */
                    boxState[(i / 3) * 3 + j / 3][index] = true;
                }
            }
        }
        recursivePlaceNumber(board, rowState, columnState, boxState, 0, 0);
    }

    private boolean recursivePlaceNumber(char[][] board, boolean[][] rowState, boolean[][] columnState, boolean[][] boxState, int row, int column) {
        if (column == 9) {
            column = 0;
            row++;
            if (row == 9) {
                // game complete
                return true;
            }
        }

        if (board[row][column] != '.') {
            return recursivePlaceNumber(board, rowState, columnState, boxState, row, column + 1);
        } else {
            for (int i = 1; i < 10; i++) {
                if (rowState[row][i] || columnState[column][i] || boxState[(row / 3) * 3 + column / 3][i]) {
                    continue;
                } else {
                    placeNumber(board, rowState, columnState, boxState, row, column, i);
                    if (recursivePlaceNumber(board, rowState, columnState, boxState, row, column + 1)) {
                        return true;
                    }
                    undoNumberPlacement(board, rowState, columnState, boxState, row, column, i);
                }
            }
        }
        // failed to get an answer
        return false;
    }

    private void placeNumber(char[][] board, boolean[][] rowState, boolean[][] columnState, boolean[][] boxState, int row, int column, int i) {
        rowState[row][i] = true;
        columnState[column][i] = true;
        boxState[(row / 3) * 3 + column / 3][i] = true;
        board[row][column] = (char) ('0' + i);
    }

    private void undoNumberPlacement(char[][] board, boolean[][] rowState, boolean[][] columnState, boolean[][] boxState, int row, int column, int i) {
        rowState[row][i] = false;
        columnState[column][i] = false;
        boxState[(row / 3) * 3 + column / 3][i] = false;
        board[row][column] = '.';

        redoCount++;
    }

    /**
     * start from the most possible place
     * @param board
     */
    // 97.00% 2ms 94.25%
    public void solveSudoku2(char[][] board) {
//    public void solveSudoku(char[][] board) {

        boolean rowState[][] = new boolean[9][10];
        boolean columnState[][] = new boolean[9][10];
        boolean boxState[][] = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '0';
                    rowState[i][index] = true;
                    columnState[j][index] = true;
                    /**
                     * Box state represented like below
                     * 0 1 2
                     * 3 4 5
                     * 6 7 8
                     */
                    boxState[(i / 3) * 3 + j / 3][index] = true;
                }
            }
        }
        int[] coordinate = getMaxPossibleCoordinate(board, rowState, columnState, boxState);

        recursivePlaceNumber2(board, rowState, columnState, boxState, coordinate[0], coordinate[1]);
    }

    private int[] getMaxPossibleCoordinate(char[][] board, boolean[][] rowState, boolean[][] columnState, boolean[][] boxState) {
        int x = -1, y = -1, minCount = 9;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                int tmpCount = 9;
                for (int k = 0; k < 9; k++) {
                    if (rowState[i][k] || columnState[j][k] || boxState[(i / 3) * 3 + j / 3][k])
                        tmpCount--;
                }
                if (tmpCount == 1)
                    return new int[]{i, j};

                if (minCount > tmpCount) {
                    minCount = tmpCount;
                    x = i;
                    y = j;
                }
            }
        }
        return new int[]{x, y};
    }

    private boolean recursivePlaceNumber2(char[][] board, boolean[][] rowState, boolean[][] columnState, boolean[][] boxState, int row, int column) {
        if (row == -1 && column == -1)
            return true;
        if (board[row][column] != '.')
            return false;

        for (int i = 1; i < 10; i++) {
            if (rowState[row][i] || columnState[column][i] || boxState[(row / 3) * 3 + column / 3][i]) {
                continue;
            } else {
                placeNumber(board, rowState, columnState, boxState, row, column, i);
                int[] coordinate = getMaxPossibleCoordinate(board, rowState, columnState, boxState);
                if (recursivePlaceNumber(board, rowState, columnState, boxState, coordinate[0], coordinate[1])) {
                    return true;
                }
                undoNumberPlacement(board, rowState, columnState, boxState, row, column, i);
            }
        }

        // failed to get an answer
        return false;
    }

    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver();
        s.solveSudoku(new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}});
//        s.solveSudoku(new char[][]{{'5','3','4','6','7','8','9','1','2'},{'6','7','2','1','9','5','3','4','8'},{'1','9','8','3','4','2','5','6','7'},{'8','5','9','7','6','1','4','2','3'},{'4','2','6','8','5','3','7','9','1'},{'7','1','3','9','2','4','8','5','6'},{'9','6','1','5','3','7','2','8','4'},{'2','8','7','4','1','9','6','3','5'},{'3','4','5','2','8','6','1','7','9'}});
//        s.solveSudoku(new char[][]{{'5', '3', '.', '.', '7', '8', '9', '1', '2'}, {'6', '7', '2', '1', '9', '5', '3', '4', '8'}, {'1', '9', '8', '3', '4', '2', '5', '6', '7'}, {'8', '5', '9', '7', '6', '1', '4', '2', '3'}, {'4', '2', '6', '8', '5', '3', '7', '9', '1'}, {'7', '1', '3', '9', '2', '4', '8', '5', '6'}, {'9', '6', '1', '5', '3', '7', '2', '8', '4'}, {'2', '8', '7', '4', '1', '9', '6', '3', '5'}, {'3', '4', '5', '2', '8', '6', '1', '7', '9'}});
        System.out.println(s.redoCount);
        s.redoCount =0;
        s.solveSudoku2(new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}});
        System.out.println(s.redoCount);
    }
}
