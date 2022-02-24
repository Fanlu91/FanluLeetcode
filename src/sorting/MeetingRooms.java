package sorting;
// Source : https://leetcode.com/problems/meeting-rooms/
// Id     : 252
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/17
// Topic  : array 
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 98.94% 12.03%

public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        //! Arrays.sort(intervals);
        //6ms Arrays.sort(intervals, Comparator.comparingDouble(o -> o[0]));
        //5ms Arrays.sort(intervals, (a, b) -> Double.compare(a[0], b[0]));
        //4ms Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0])
                return false;
        }
        return true;
    }
}