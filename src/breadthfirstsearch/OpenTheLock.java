package breadthfirstsearch;
// Source : https://leetcode.com/problems/open-the-lock/
// Id     : 752
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/18
// Topic  : Breadth First Search
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100% 83.33%

import java.util.*;

public class OpenTheLock {

    // 11.63% 280 ms 11.11%
    public int openLock(String[] deadends, String target) {

        Queue<String> queue = new LinkedList<>(); // 使用队列记录待走的路径
        Set<String> visited = new HashSet<>(); // 使用集合记录已走的路径

        for (String dead : deadends)
            visited.add(dead);
        if (visited.contains("0000"))
            return -1;

        String initial = "0000";
        queue.offer(initial); // 将起点加入队列
        visited.add(initial);
        int step = 0; // 记录扩散的步数

        while (!queue.isEmpty()) {
            int size = queue.size(); // 确定了当前层有几个节点，后续继续加入并不影响。
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                System.out.println(visited.size() + "asa" + queue.size());
                System.out.println(cur);
                /* 划重点：这里判断是否到达终点 */
                if (cur.equals(target))
                    return step;
                /* 将 cur 的相邻节点加入队列 */
                for (String option : getAdjecentStates(cur)) // cur.adj() 泛指 cur 相邻的节点
                    if (!visited.contains(option)) {
                        queue.add(option);
                        visited.add(option);
                    }

            }
            // 当前层结束，增加一步开始计算下一层
            step++;
        }
        return -1;
    }


    private String[] getAdjecentStates(String cur) {
        String[] ans = new String[]{"", "", "", "", "", "", "", ""};
        for (int i = 0; i < 4; i++) {
            int c = cur.charAt(i) - '0';
            int pre = (c + 9) % 10;
            int next = (c + 1) % 10;
            for (int j = 0; j < 8; j++) {
                if (i == j)
                    ans[j] += next;
                else if (i + 4 == j)
                    ans[j] += pre;
                else
                    ans[j] += c;
            }
        }
        return ans;
    }


    // 100% 1ms 83.33%
    public int openLock1(String[] deadends, String target) {
//    public int openLock(String[] deadends, String target) {
        if (ifMeetDeadend(deadends, "0000"))
            return -1;
        List<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char[] chars = target.toCharArray();
            char temp = chars[i];
            chars[i] = (char) ((temp - 48 + 1) % 10 + 48);
            String t1 = new String(chars);
            if (!ifMeetDeadend(deadends, t1)) {
                options.add(t1);
            }

            chars[i] = (char) ((temp - 48 + 9) % 10 + 48);
            String t2 = new String(chars);
            if (!ifMeetDeadend(deadends, t2)) {
                options.add(t2);
            }
        }
        if (options.isEmpty())
            return -1;

        int step = 0;
        for (String option : options) {
            char[] chars = option.toCharArray();
            int cur = 1;
            for (int j = 0; j < 4; j++) {
                int t = chars[j] - 48;
                if (t > 5) {
                    cur += 10 - t;
                } else {
                    cur += t;
                }
            }
            if (step == 0 || cur < step) {
                step = cur;
            }
        }

        return step;
    }

    public boolean ifMeetDeadend(String[] deadends, String target) {
        for (String deadend : deadends) {
            if (deadend.equals(target)) {
                return true;
            }
        }
        return false;
    }


    private String plusOne(String cur, int j) {
        char[] ch = cur.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }

    private String minusOne(String cur, int j) {
        char[] ch = cur.toCharArray();
        if (ch[j] == '0') {
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }

    public int openLock2(String[] deadends, String target) {
//    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<String>();
        for (String str : deadends) {
            deads.add(str);
        }
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String str : q1) {
                if (deads.contains(str)) {
                    continue;
                }
                if (q2.contains(str)) {
                    return step;
                }
                visited.add(str);
                for (int i = 0; i < 4; i++) {
                    String up = plusOne(str, i);
                    if (!visited.contains(up)) {
                        temp.add(up);
                    }
                    String down = minusOne(str, i);
                    if (!visited.contains(down)) {
                        temp.add(down);
                    }
                }
            }
            step++;
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

    public static void main(String[] args) {
        OpenTheLock openTheLock = new OpenTheLock();
//        System.out.println(openTheLock.getAdjecentStates("0000")[1]);
        openTheLock.openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"},
                "8888");
    }
}