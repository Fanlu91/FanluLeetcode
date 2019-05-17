package com.fanlu.leetcode.array;

import java.util.HashMap;

public class ArrayRelated {
	/**
	 * remove element from an array.
	 * 
	 * @param A
	 * @param elem
	 * @return
	 */
	public int removeElement(int[] A, int elem) {
		int k = A.length - 1;
		for (int i = 0; i <= k;)
			if (A[i] == elem)
				A[i] = A[k--];
			else
				i++;// here is the key thing
		return k + 1;
	}

	/**
	 * Given a sorted array, remove the duplicates in place such that each
	 * element appear only once and return the new length. Do not allocate extra
	 * space for another array, you must do this in place with constant memory.
	 * 
	 * @param args
	 */
	public int removeDuplicates(int[] A) {
		int dupNum = 0;
		int left = 0;
		boolean flag = true;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] == A[i + 1]) {
				if (flag) {
					left = i + 1;
					flag = false;
				}
				dupNum++;
			} else if (A[i] < A[i + 1]) {
				if (!flag) {
					A[left] = A[i + 1];
					left++;
				}
			}
		}
		return A.length - dupNum;
	}

	/**
	 * Given a non-negative number represented as an array of digits, plus one
	 * to the number. The digits are stored such that the most significant digit
	 * is at the head of the list.
	 * 
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		int carry = 1;// the one pulsed at the first time or the carry from the
						// previous digit.
		int[] result = new int[digits.length + 1];
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i] = digits[i] + carry;
			if (digits[i] > 9) {
				digits[i] -= 10;
				carry = 1;
			} else {
				carry = 0;
			}
		}
		if (carry == 1) {
			result[0] = 1;
			for (int i : digits) {
				int index = 1;
				result[index] = i;
				index++;
			}
			return result;
		}
		return digits;
	}

	/**
	 * Given two sorted integer arrays A and B, merge B into A as one sorted
	 * array. You may assume that A has enough space (size that is greater or
	 * equal to m + n) to hold additional elements from B. The number of
	 * elements initialized in A and B are m and n respectively.
	 * 
	 * key is move the array from the end of A.
	 * 
	 * @param A
	 * @param m
	 * @param B
	 * @param n
	 */
	public void merge(int A[], int m, int B[], int n) {
		while (m + n > 0) {
			if (n < 1) {
				return;
			}
			if (m < 1) {
				A[m + n - 1] = B[n - 1];
				n--;
			} else {
				if ((A[m - 1] >= B[n - 1])) {
					A[m + n - 1] = A[m - 1];
					m--;
				} else {
					A[m + n - 1] = B[n - 1];
					n--;
				}
			}
			// System.out.println(Arrays.toString(A));
		}
		// use exp1?exp2:exp3 to solve this.
		// I must say this way is cool(and a little bit faster according to the
		// performance) but much harder to debug.
		// while(m+n>0 && n != 0){
		// A[m+n -1] = (m<1 || A[m-1]<= B[n-1])?B[(n--) -1]:A[(m--) -1];
		// }
	}
	/**
	 * find the size of looped number in a integer array.
	 * @param A
	 * @return
	 */
	public int solution(int[] A) {
		int nonCycle = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int i = 0;
		for (; i < A.length; i++) {
			if (!map.containsKey(A[i])) {
				map.put(A[i], 1);
				nonCycle++;
			} else if (map.containsKey(A[i])) {
				map.put(A[i], map.get(A[i]) + 1);
				if (map.get(A[i]) == 3) {
					return i - nonCycle;
				}
			}
			System.out.println(nonCycle);
		}
		return i - nonCycle;
	}
	public static void main(String[] args) {
		ArrayRelated ar = new ArrayRelated();
		int A[] = new int[10];
		// A[0] = 2;
		// A[1] = 3;
		// ar.merge(A, 0, new int[]{1}, 1);
		

	}
}
