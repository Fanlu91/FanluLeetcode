package orderedmap;
// Source : https://leetcode.com/problems/my-calendar-i/
// Id     : 729
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/12
// Topic  : Ordered Map
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 55.27% 19.51%

import java.util.*;

public class MyCalendarI {
    //TLE
    /*Set<Integer> booked;

    public MyCalendarI() {
//    public MyCalendar() {
        this.booked = new HashSet<>();
    }

    public boolean book(int start, int end) {
        int len = end - start;
        Integer[] tmp = new Integer[len];
        for (int i = 0; i < len; i++) {
            if (booked.contains(start + i))
                return false;
            tmp[i] = start + i;
        }
        booked.addAll(Arrays.asList(tmp));
        // use Integer[]  instead of int[]
        // as Arrays.asList(int[]) will internally consider int[] as a single element.
        return true;
    }*/


    TreeMap<Integer, Integer> calendar;

    // 55.27% 36 ms 19.51%
    public MyCalendarI() {
//    public MyCalendar() {
        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }

//    // improved performance
//    public boolean book1(int start, int end) {
//        Map.Entry<Integer, Integer> e = calendar.lowerEntry(end); // the greatest key strictly less than the given key
//        if (e != null && e.getValue() > start) {
//            return false;
//        } else {
//            calendar.put(start, end);
//            return true;
//        }
//    }

}