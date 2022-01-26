package dynamicprogramming;
// Source : https://leetcode.com/problems/jump-game/
// Id     : 55
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/23
// Topic  : dynamicprogramming 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result :

public class JumpGame {
    // 150ms
    public boolean canJump(int[] nums) {
        if (nums.length == 1)
            return true;
        return dfs(nums, 0, new boolean[nums.length]);
    }

    private boolean dfs(int[] nums, int start, boolean[] mem) {
        // System.out.println(start);
        if (start >= nums.length - 1)
            return true;
        if (mem[start])
            return false;
        for (int i = nums[start]; i > 0; i--) {
            if (dfs(nums, start + i, mem))
                return true;
            else
                mem[start + i] = true;
        }
        mem[start] = true;
        return false;
    }

    public boolean canJump1(int[] nums) {
//    public boolean canJump(int[] nums) {
        int n = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= n) {
                n = 1;
            } else {
                n++;
            }
            if (i == 0 && n > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
    }
}