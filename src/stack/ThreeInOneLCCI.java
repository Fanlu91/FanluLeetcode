package stack;
// Source : https://leetcode.com/problems/three-in-one-lcci/
// Id     : mst03.01
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/9
// Topic  : stack 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 91.52% 5.64%

import java.util.LinkedList;

public class ThreeInOneLCCI {
    // 9 ms
    int[] stack;
    int[] pointers;
    int stackSize;

    public ThreeInOneLCCI(int stackSize) {
//    public TripleInOne(int stackSize) {
        this.stack = new int[stackSize * 3];
        this.pointers = new int[3];
        pointers[1] = stackSize;
        pointers[2] = stackSize * 2;
        this.stackSize = stackSize;
    }

    public void push(int stackNum, int value) {
        if (pointers[stackNum] != (1 + stackNum) * stackSize) {
            stack[pointers[stackNum]] = value;
            pointers[stackNum]++;
        }
    }

    public int pop(int stackNum) {
        if (pointers[stackNum] == stackNum * stackSize) {
            return -1;
        } else {
            int val = stack[pointers[stackNum] - 1];
            pointers[stackNum]--;
            return val;
        }
    }

    public int peek(int stackNum) {
        if (pointers[stackNum] == stackNum * stackSize) {
            return -1;
        } else {
            int val = stack[pointers[stackNum] - 1];
            return val;
        }
    }

    public boolean isEmpty(int stackNum) {
        return pointers[stackNum] == stackNum * stackSize;
    }
}