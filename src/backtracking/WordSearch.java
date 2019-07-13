package backtracking;
// Source : https://leetcode.com/problems/word-search/
// Id     : 79
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Backtracking
// Level  : Medium
// Date   : 2018-07-13
// Other  :
// Tips   : 1. clone works fine for normal array, but it only does shallow copy which means for multidimensional array it will not work as expected.
//          2. think before using extra space(introduce additional data structure)
// Result : 99.91% 100.00%

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WordSearch {

    char[][] board;
    boolean found;

    // 9.55% 65 ms 8.69%
    public boolean existOriginal(char[][] board, String word) {
        boolean[][] visited;
        this.board = board;

        LinkedList<Character> wordQueue = new LinkedList<>();
        for (int i = 1; i < word.length(); i++) {
            wordQueue.add(word.charAt(i));
        }
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == word.charAt(0)) {
                    visited = new boolean[board.length][board[0].length];
                    backTrackingOriginal((Queue<Character>) wordQueue.clone(), row, column, visited);
                    if (found)
                        return true;
                }
            }
        }
        return found;
    }

    public void backTrackingOriginal(Queue<Character> wordQueue, int row, int column, boolean[][] visited) {
        if (found == true)
            return;
        visited[row][column] = true;

        if (wordQueue.isEmpty()) {
            found = true;
            return;
        }
        char c = wordQueue.poll();
        boolean[][] tmp;
        // up
        if (row - 1 >= 0 && board[row - 1][column] == c && !visited[row - 1][column]) {
            tmp = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                tmp[i] = Arrays.copyOf(visited[i], visited[i].length);
            }
            backTrackingOriginal(new LinkedList<>(wordQueue), row - 1, column, tmp);
        }
        // down
        if (row + 1 < board.length && board[row + 1][column] == c && !visited[row + 1][column]) {
            tmp = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                tmp[i] = Arrays.copyOf(visited[i], visited[i].length);
            }
            backTrackingOriginal(new LinkedList<>(wordQueue), row + 1, column, tmp);
        }
        // left
        if (column - 1 >= 0 && board[row][column - 1] == c && !visited[row][column - 1]) {
            tmp = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                tmp[i] = Arrays.copyOf(visited[i], visited[i].length);
            }
            backTrackingOriginal(new LinkedList<>(wordQueue), row, column - 1, tmp);
        }
        // right
        if (column + 1 < board[0].length && board[row][column + 1] == c && !visited[row][column + 1]) {
            tmp = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                tmp[i] = Arrays.copyOf(visited[i], visited[i].length);
            }
            backTrackingOriginal(new LinkedList<>(wordQueue), row, column + 1, tmp);
        }
    }

    /**
     * Try to improve my solution.
     * <p>
     * One thing I noticed is there are many paths wasted if found is already true.
     * <p>
     * However, below attempt seems to be not very efficient at all.
     * The problem is lambda used to copy the visited array which is two dimensional.
     * <p>
     * Learned how to copy a two dimensional array in one line though, even it is not helpful here.
     */

    // 5.61% 494 ms ... lol
    public boolean existWorse(char[][] board, String word) {
        boolean[][] visited;
        this.board = board;

        LinkedList<Character> wordQueue = new LinkedList<>();
        for (int i = 1; i < word.length(); i++) {
            wordQueue.add(word.charAt(i));
        }
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == word.charAt(0)) {
                    visited = new boolean[board.length][board[0].length];
                    if (backTrackingWorse((Queue<Character>) wordQueue.clone(), word.charAt(0), row, column, visited))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean backTrackingWorse(Queue<Character> wordQueue, char charToCheck, int row, int column, boolean[][] visited) {

        if (row < 0 || column < 0 || row == board.length || column == board[0].length)
            return false;

        if (board[row][column] != charToCheck || visited[row][column])
            return false;

        if (wordQueue.isEmpty())
            return true;

        visited[row][column] = true;
        char c = wordQueue.poll();
        // it seems the lambda expression is not very efficient
        return backTrackingWorse(new LinkedList<>(wordQueue), c, row - 1, column, Arrays.stream(visited).map(r -> r.clone()).toArray(boolean[][]::new)) ||
                backTrackingWorse(new LinkedList<>(wordQueue), c, row + 1, column, Arrays.stream(visited).map(r -> r.clone()).toArray(boolean[][]::new)) ||
                backTrackingWorse(new LinkedList<>(wordQueue), c, row, column - 1, Arrays.stream(visited).map(r -> r.clone()).toArray(boolean[][]::new)) ||
                backTrackingWorse(new LinkedList<>(wordQueue), c, row, column + 1, Arrays.stream(visited).map(r -> r.clone()).toArray(boolean[][]::new));

    }

    /***
     * I am not that easy to give up so, it turns out I can use visited directly
     * as long as I restore it back to false after recursion.
     *
     * finally it does improved a little : )
     */

    // 10.82% 43 ms 5.04%
    public boolean existImproved(char[][] board, String word) {
        boolean[][] visited;
        this.board = board;

        LinkedList<Character> wordQueue = new LinkedList<>();
        for (int i = 1; i < word.length(); i++) {
            wordQueue.add(word.charAt(i));
        }
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == word.charAt(0)) {
                    visited = new boolean[board.length][board[0].length];
                    if (backTrackingImproved((Queue<Character>) wordQueue.clone(), word.charAt(0), row, column, visited))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean backTrackingImproved(Queue<Character> wordQueue, char charToCheck, int row, int column, boolean[][] visited) {
        if (row < 0 || column < 0 || row == board.length || column == board[0].length)
            return false;

        if (board[row][column] != charToCheck || visited[row][column])
            return false;

        if (wordQueue.isEmpty())
            return true;

        visited[row][column] = true;
        char c = wordQueue.poll();
        boolean b = backTrackingImproved(new LinkedList<>(wordQueue), c, row - 1, column, visited) ||
                backTrackingImproved(new LinkedList<>(wordQueue), c, row + 1, column, visited) ||
                backTrackingImproved(new LinkedList<>(wordQueue), c, row, column - 1, visited) ||
                backTrackingImproved(new LinkedList<>(wordQueue), c, row, column + 1, visited);
        visited[row][column] = false;
        return b;
    }

    /**
     * if we can alter visited and restore it back,then we might actually don't need it
     * alter the board directly!
     * <p>
     * Further more, we don't actually need an extra queue, improve that as well
     * <p>
     * Finally, we get the best result.
     * Cheers.
     */
    // 99.91% 3m 100.00%
    public boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == word.charAt(0)) {
                    if (backTrackingBest(board, word.toCharArray(), 0, row, column))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean backTrackingBest(char[][] board, char[] chars, int index, int row, int column) {
        if (index == chars.length)
            return true;
        if (row < 0 || column < 0 || row == board.length || column == board[0].length)
            return false;
        if (board[row][column] != chars[index])
            return false;

        // xor the character so it will not be able to be matched again.
        board[row][column] ^= 256;
        boolean b = backTrackingBest(board, chars, index + 1, row - 1, column) ||
                backTrackingBest(board, chars, index + 1, row + 1, column) ||
                backTrackingBest(board, chars, index + 1, row, column - 1) ||
                backTrackingBest(board, chars, index + 1, row, column + 1);
        board[row][column] ^= 256;
        return b;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        System.out.println(wordSearch.exist(
                new char[][]{{'A', 'A'}}, "AAA"));
        System.out.println(wordSearch.exist(
                new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCESEEEFS"));
//        System.out.println(wordSearch.exist(
//                new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABC"));
//        System.out.println(wordSearch.exist(
//                new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "AA"));
    }
}
