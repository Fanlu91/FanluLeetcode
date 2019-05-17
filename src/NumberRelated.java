package com.fanlu.leetcode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberRelated {

    public int reverseInt(int x) {
        String str = "" + x;
        int result = 0;
        String value = str;

        if (str.contains("+") || str.contains("-")) {
            value = str.substring(1);
        }
        for (int i = 0; i < value.length(); i++) {
            result += (value.charAt(i) - '0') * Math.pow(10, i);
        }
        if (str.contains("-")) {
            return 0 - result;
        }
        return result;
    }

    /**
     * check whether an integer is palindrome
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        char[] chars = Integer.toString(x).toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * check whether an string input is palindrome
     *
     * @param s a
     * @return
     */
    public boolean isPalindrome(String s) {
        if (null == s)
            return true;
        char[] letters = s.trim().toLowerCase().toCharArray();
        int l = 0;
        int r = letters.length - 1;
        while (l < r) {
            if (isValidLetter(letters[l])) {
                if (isValidLetter(letters[r])) {
                    if (letters[l] != letters[r])
                        return false;
                    else {
                        l++;
                        r--;
                    }
                } else {
                    r--;
                }
            } else {
                l++;
            }
        }
        return true;
    }

    public boolean isValidLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
            return true;
        else
            return false;
    }

    /**
     * generate Pascal's Triangle. using factorial tool and the formula(j and
     * row number n;) of index. no a very good answer.
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> theList = new ArrayList<Integer>();
            for (int j = 1; j <= i; j++) {
                // the formula
                BigInteger bi = (factorial(i - 1).divide(factorial(i - j))
                        .divide(factorial(j - 1)));
                theList.add(bi.intValue());
            }
            list.add(theList);
        }
        return list;
    }

    /**
     * use iteration to get the result from the previous list. ac solution
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> pascalTriangle(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (numRows == 0) {
            return list;
        }
        List<Integer> element = new ArrayList<Integer>();
        element.add(1);
        for (int i = 0; i < numRows; i++) {
            list.add(element);
            element = this.getNextRow(element);
        }
        return list;
    }

    public List<Integer> getNextRow(List<Integer> previous) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        for (int i = 0; i < previous.size() - 1; i++) {
            list.add(previous.get(i) + previous.get(i + 1));
        }
        return list;
    }

    /**
     * Given an index k, return the kth row of the Pascal's triangle. use only
     * O(k) extra space
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0)
            return list;
        list.add(1);
        for (int j = 1; j <= rowIndex; j++) {
            //j represents the index in the list;
            //for each index we need to do the addition using the loop below.
            int left = list.get(0);
            for (int i = 1; i < j; i++) {
                int right = list.get(i);
                list.set(i, left + right);
                left = right;
            }
            list.add(1);
            System.out.println(Arrays.toString(list.toArray()));
        }
        return list;
    }

    /**
     * factorial(if long is used, 21 will cause overflow)
     *
     * @param n
     * @return
     */
    private BigInteger factorial(int n) {
        if (n < 0)
            return new BigInteger(String.valueOf(0));
        if (n == 0)
            return new BigInteger(String.valueOf(1));
        else
            return factorial(n - 1).multiply(new BigInteger(String.valueOf(n)));
    }

    /**
     * factorial, using int array to store the result(reverse order) according
     * to http://www.cnblogs.com/anderslly/archive/2008/05/19/factorial-
     * algorithms.html but there is a problem in his code(should fill the array
     * with 1 first) something more about factorial: how many 0s at the end of
     * 100! ? (hint: Prime number. 2a×5b×p1a1...pnan, The number of 0 depends on
     * how many 5 those numbers contains, answer is 24=20+4[25 50 75 100])
     *
     * @param n
     * @return
     */
    public int[] FactorialLargeNumber(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("x must be>=0");
        }
        if (n == 0 || n == 1) {
            return new int[]{1};
        }
        int MAXLENGTH = 100000;
        int[] array = new int[MAXLENGTH];
        // number of digits;
        int digits = 1;
        // start each Multiplication from 2 to n
        long carry = 0;
        Arrays.fill(array, 1);
        for (int i = 2; i <= n; i++) {
            // do the Multiplication;
            // Multiplier will be the new 'n' and each digital of the result
            // before
            for (int j = 0; j < digits; j++) {
                // System.out.println("carry value"+carry);
                long multipleResult = array[j] * i + carry;
                // System.out.println(multipleResult);
                array[j] = (int) (multipleResult % 10);
                carry = multipleResult / 10;
            }
            // assign the digits after multi
            while (carry != 0) {
                array[digits++] = (int) (carry % 10);
                carry /= 10;
            }
        }
        int[] result = new int[digits];
        result = Arrays.copyOf(array, digits);
        return result;
    }

    public String countAndSay(int n) {
        String result = "";
        return result;
    }

    public static void main(String[] args) {
        NumberRelated n = new NumberRelated();
        // System.out.println("here"+n.factorial(25));
        // System.out.println(Arrays.toString(n.FactorialLargeNumber(25)));
//		System.out.println(n.isPalindrome(""));
        n.getRow(5);
    }

}
