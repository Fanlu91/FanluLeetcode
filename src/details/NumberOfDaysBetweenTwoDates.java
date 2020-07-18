package details;
// Source : https://leetcode.com/problems/number-of-days-between-two-dates/
// Id     : 1360
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/13
// Topic  : Details
// Level  : Easy
// Other  :
// Tips   : year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)
// Links  :
// Result : 100% 100%

public class NumberOfDaysBetweenTwoDates {
    private final static int[] daysInMonth = new int[]{31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};

    public int daysBetweenDates(String date1, String date2) {
        String dateArray1[] = date1.split("-");
        String dateArray2[] = date2.split("-");

        int days1 = getDayCount(Integer.valueOf(dateArray1[0]), Integer.valueOf(dateArray1[1]), Integer.valueOf(dateArray1[2]));
        int days2 = getDayCount(Integer.valueOf(dateArray2[0]), Integer.valueOf(dateArray2[1]), Integer.valueOf(dateArray2[2]));

        return Math.abs(days1 - days2);
    }

    private int getDayCount(Integer year, Integer month, Integer date) {
        int days = date;

        for (int i = 0; i < month; i++) {
            days += daysInMonth[i];
        }
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month > 2)
            days++;

        year--;
        days += 365 * year;
        days += year/ 4 - year/ 100 + year/ 400;

        return days;
    }
}