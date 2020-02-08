package matrix;

// Source : https://leetcode.com/problems/game-of-life/
// Id     : 289
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-12
// Topic  : Matrix
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 91.85%

import java.util.Arrays;

public class GameOfLife {

    private int[][] initialBoard;

    // 37.84% 1ms 63.71%
    public void gameOfLife(int[][] board) {
        this.initialBoard = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            this.initialBoard[i] = board[i].clone();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = checkLife(board[i][j], i, j);
            }
        }
    }

    private int checkLife(int preState, int i, int j) {
        int neighborCount = getNeighborCount(i, j);
        if (neighborCount < 2 || neighborCount > 3)
            return 0;
        else if (neighborCount == 3)
            return 1;
        else
            return preState;
    }


    private int getNeighborCount(int i, int j) {

        int count = 0;
        int maxRow = initialBoard.length - 1, maxColumn = initialBoard[0].length - 1;

        if (i != 0) {
            count += initialBoard[i - 1][j];
            if (j != 0) {
                count += initialBoard[i - 1][j - 1];
            }
            if (j != maxColumn) {
                count += initialBoard[i - 1][j + 1];
            }

        }

        if (i != maxRow) {
            count += initialBoard[i + 1][j];

            if (j != 0) {
                count += initialBoard[i + 1][j - 1];
            }
            if (j != maxColumn) {
                count += initialBoard[i + 1][j + 1];
            }
        }

        if (j != 0) {
            count += initialBoard[i][j - 1];
        }
        if (j != maxColumn) {
            count += initialBoard[i][j + 1];
        }

        return count;
    }

    /**
     * -1 as for 1 to 0;
     * 2 as for 0 to 1;
     * <p>
     * translate to 0 1 only after life the last life check
     *
     * @param board
     */
    public void gameOfLifeInPlace(int[][] board) {
//    public void gameOfLife(int[][] board) {
        // do in place life check
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = checkLifeInPlace(board, board[i][j], i, j);
            }
        }

        // translate
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2)
                    board[i][j] = 1;
                else if (board[i][j] == -1)
                    board[i][j] = 0;
            }
        }
    }

    private int checkLifeInPlace(int[][] board, int preState, int i, int j) {
        int neighborCount = getNeighborCountForInPlace(board, preState, i, j);
        if (neighborCount < 2 || neighborCount > 3)
            return preState == 0 ? 0 : -1;
        else if (neighborCount == 3)
            return preState == 1 ? 1 : 2;
        else
            return preState;
    }

    //      -1 as for 1 to 0;
    //      2 as for 0 to 1;
    private int getNeighborCountForInPlace(int[][] board, int preState, int i, int j) {
        int count = 0;
        int maxRow = board.length - 1, maxColumn = board[0].length - 1;

        if (i != 0) {
            count += board[i - 1][j] == 1 || board[i - 1][j] == -1 ? 1 : 0;
            if (j != 0) {
                count += board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == -1 ? 1 : 0;
            }
            if (j != maxColumn) {
                count += board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == -1 ? 1 : 0;
            }

        }

        if (i != maxRow) {
            count += board[i + 1][j] == 1 || board[i + 1][j] == -1 ? 1 : 0;
            if (j != 0) {
                count += board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == -1 ? 1 : 0;
            }
            if (j != maxColumn) {
                count += board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == -1 ? 1 : 0;
            }
        }

        if (j != 0) {
            count += board[i][j - 1] == 1 || board[i][j - 1] == -1 ? 1 : 0;
        }
        if (j != maxColumn) {
            count += board[i][j + 1] == 1 || board[i][j + 1] == -1 ? 1 : 0;
        }
        return count;
    }

    // 100.00% 0ms 91.85%
    public void gameOfLifeFaster(int[][] board) {
//    public void gameOfLife(int[][] board) {
        int eachStatus = 0;
        final int [] [] beforeBoard = new int[board.length][];
        for(int i = 0;i < board.length;i++){
            beforeBoard[i] = board[i].clone();
        }
        if (board.length == 1){
            for(int i = 0; i< beforeBoard.length;i++) {
                for (int j = 0; j < beforeBoard[i].length; j++) {
                    if (j == 0 || j ==  beforeBoard[i].length - 1 ){
                        board[i][j] = 0;
                    }else{
                        if (beforeBoard[i][j+1] + beforeBoard[i][j-1] == 2){
                            board[i][j] = 1;
                        }else{
                            board[i][j] = 0;
                        }
                    }
                }
            }
            return;
        }
        for(int i = 0; i< beforeBoard.length;i++){
            for (int j = 0; j<beforeBoard[i].length;j++){
                int status = beforeBoard[i][j];
                // 处理特殊情况 上下左右4个边界问题
                if (beforeBoard.length == 1 && beforeBoard[i].length == 1){
                    board[i][j] = 0;
                    return;
                }
                if (beforeBoard[i].length == 1){
                    if (i ==0 || i == beforeBoard.length -1){
                        board[i][j] = 0;
                    }else{
                        if (beforeBoard[i-1][j] + beforeBoard[i+1][j] == 2){
                            board[i][j] = 1;
                        }else{
                            board[i][j] = 0;
                        }
                    }
                    break;
                }
                if (i == 0){
                    if (j == 0){
                        eachStatus = beforeBoard[i][j+1] + beforeBoard[i+1][j+1] + beforeBoard[i+1][j];
                    }else if (j == beforeBoard[i].length - 1){
                        eachStatus = beforeBoard[i+1][j] + beforeBoard[i][j -1] + beforeBoard[i+1][j -1];
                    }else{
                        eachStatus = beforeBoard[i][j+1] + beforeBoard[i+1][j+1] + beforeBoard[i+1][j] + beforeBoard[i][j -1] + beforeBoard[i+1][j -1];
                    }
                } else
                if (i == beforeBoard.length - 1){
                    if (j == 0){
                        eachStatus = beforeBoard[i][j+1] + beforeBoard[i-1][j+1] + beforeBoard[i-1][j];
                    }else if (j == beforeBoard[i].length - 1){
                        eachStatus = beforeBoard[i-1][j] + beforeBoard[i][j -1] + beforeBoard[i-1][j -1];
                    }else{
                        eachStatus = beforeBoard[i][j+1] + beforeBoard[i-1][j+1] + beforeBoard[i-1][j] + beforeBoard[i][j -1] + beforeBoard[i-1][j -1];
                    }
                } else
                if ((i != 0 || i != beforeBoard.length - 1) && j == 0){
                    eachStatus = beforeBoard[i-1][j] + beforeBoard[i-1][j+1] + beforeBoard[i][j+1] + beforeBoard[i+1][j] + beforeBoard[i+1][j+1];
                } else
                if ((i != 0 || i != beforeBoard.length - 1) && j == beforeBoard[i].length - 1){
                    eachStatus = beforeBoard[i][j-1] + beforeBoard[i-1][j-1] + beforeBoard[i-1][j] + beforeBoard[i + 1][j] + beforeBoard[i + 1][j - 1];
                }else{
                    eachStatus = beforeBoard[i-1][j] + beforeBoard[i-1][j - 1] +beforeBoard[i-1][j +1] + beforeBoard[i][j - 1] + beforeBoard[i][j + 1] +beforeBoard[i+1][j - 1]+beforeBoard[i+1][j] +beforeBoard[i+1][j + 1];
                }
                if (status == 1){
                    if (eachStatus < 2){
                        board[i][j] = 0;
                    }else if (eachStatus == 2 || eachStatus == 3){
                        board[i][j] = 1;
                    }else {
                        board[i][j] = 0;
                    }
                }
                if (status == 0){
                    if (eachStatus == 3){
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GameOfLife g = new GameOfLife();
        g.gameOfLife(new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}});
    }

}
