package orderedmap;
// Source : https://leetcode.com/problems/my-calendar-ii/
// Id     : 731
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/12
// Topic  : Ordered Map
// Level  : Medium+
// Other  :
// Tips   :
// Links  : 729
// Result : 100.00% 70.83%

import java.util.TreeMap;

public class MyCalendarII {
    TreeMap<Integer, Integer> singleMap;
    TreeMap<Integer, Integer> doubleMap;

    public MyCalendarII() {
//    public MyCalendarTwo() {
        singleMap = new TreeMap<>();
        doubleMap = new TreeMap<>();
    }

    public boolean book(int start, int end) {

        Integer pre = doubleMap.lowerKey(start);//出现两次区间中，起点小于start的
        Integer next = doubleMap.ceilingKey(start);//出现两次区间中，起点大于等于start的
        if (pre != null && doubleMap.get(pre) > start)
            return false;
        if (next != null && next < end)
            return false;


        pre = singleMap.lowerKey(start);
        next = singleMap.ceilingKey(start);

        //完全没有冲突
        if ((pre == null || singleMap.get(pre) < start) && (next == null || next > end)) {
            singleMap.put(start, end);
            return true;
        }

        //与前一个区间有重叠部分，将重叠部分塞入doubleMap，同时在singleMap中合并当前区间和前一个区间
        if (!(pre == null || singleMap.get(pre) < start)) {
            Integer i = singleMap.get(pre);
            singleMap.put(pre, Math.max(end, i));
            addDouble(start, Math.min(end, i));
            start = pre;
            end = Math.max(end, i);
        }

        //如果和后一个区间有冲突，冲突区域塞入doubleMap，同时在singleMap中合。
        //需要考虑当前准备插入的区间，同时包含了多个已有区间的情况，所以继续向后找
        //这里应该有优化点，直接查找后一个是比调用higherKey方法来得快的，没仔细想了
        while (next != null && next <= end) {
            Integer i = singleMap.get(next);
            addDouble(next, Math.min(end, i));
            singleMap.remove(next);
            singleMap.put(start, Math.max(end, i));
            end = Math.max(end, i);
            next = singleMap.higherKey(start);
        }

        return true;
    }

    public void addDouble(int start, int end) {
        if (start >= end) {
            return;
        }
        doubleMap.put(start, end);
    }

}