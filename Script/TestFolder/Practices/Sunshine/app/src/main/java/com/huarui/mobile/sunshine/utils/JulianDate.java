// http://www.rgagnon.com/javadetails/java-0506.html

// http://stackoverflow.com/questions/5655034/how-can-i-convert-between-a-java-date-and-julian-day-number


package com.huarui.mobile.sunshine.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Created by wanglai on 1/4/2017.
 */
public class JulianDate extends Julian_Date {
    /**
     * Returns the Julian day number that begins at noon of
     * this day, Positive year signifies A.D., negative year B.C.
     * Remember that the year after 1 B.C. was 1 A.D.
     * <p>
     * ref :
     * Numerical Recipes in C, 2nd ed., Cambridge University Press 1992
     */
    // Gregorian Calendar adopted Oct. 15, 1582 (2299161)
    public static int JGREG = 15 + 31 * (10 + 12 * 1582);
    public static double HALFSECOND = 0.5;

    public static double toJulian(int[] ymd) {
        int year = ymd[0];
        int month = ymd[1]; // jan=1, feb=2,...
        int day = ymd[2];
        int julianYear = year;
        if (year < 0) julianYear++;
        int julianMonth = month;
        if (month > 2) {
            julianMonth++;
        } else {
            julianYear--;
            julianMonth += 13;
        }

        double julian = (java.lang.Math.floor(365.25 * julianYear)
                + java.lang.Math.floor(30.6001 * julianMonth) + day + 1720995.0);
        if (day + 31 * (month + 12 * year) >= JGREG) {
            // change over to Gregorian calendar
            int ja = (int) (0.01 * julianYear);
            julian += 2 - ja + (0.25 * ja);
        }
        return java.lang.Math.floor(julian);
    }

    // include h,m,s,sss. by wwanglai
    public static double toJulian(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // jan=1, feb=2,...
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        int julianYear = year;
        if (year < 0) julianYear++;
        int julianMonth = month;
        if (month > 2) {
            julianMonth++;
        } else {
            julianYear--;
            julianMonth += 13;
        }

        double julian = (java.lang.Math.floor(365.25 * julianYear)
                + java.lang.Math.floor(30.6001 * julianMonth) + day + 1720995.0);
        if (day + 31 * (month + 12 * year) >= JGREG) {
            // change over to Gregorian calendar
            int ja = (int) (0.01 * julianYear);
            julian += 2 - ja + (0.25 * ja);
        }
        return java.lang.Math.floor(julian);
    }

    public static double toJulianNew(GregorianCalendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // jan=1, feb=2,...
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);


        calendar.setGregorianChange(new Date(Long.MAX_VALUE));

        int julianYear = year;
        if (year < 0) julianYear++;
        int julianMonth = month;
        if (month > 2) {
            julianMonth++;
        } else {
            julianYear--;
            julianMonth += 13;
        }

        double julian = (java.lang.Math.floor(365.25 * julianYear)
                + java.lang.Math.floor(30.6001 * julianMonth) + day + 1720995.0);
        if (day + 31 * (month + 12 * year) >= JGREG) {
            // change over to Gregorian calendar
            int ja = (int) (0.01 * julianYear);
            julian += 2 - ja + (0.25 * ja);
        }
        return java.lang.Math.floor(julian);
    }

    /**
     * Converts a Julian day to a calendar date
     * ref :
     * Numerical Recipes in C, 2nd ed., Cambridge University Press 1992
     */
    public static int[] fromJulian(double injulian) {
        int jalpha, ja, jb, jc, jd, je, year, month, day;
        double julian = injulian + HALFSECOND / 86400.0;
        ja = (int) julian;
        if (ja >= JGREG) {
            jalpha = (int) (((ja - 1867216) - 0.25) / 36524.25);
            ja = ja + 1 + jalpha - jalpha / 4;
        }

        jb = ja + 1524;
        jc = (int) (6680.0 + ((jb - 2439870) - 122.1) / 365.25);
        jd = 365 * jc + jc / 4;
        je = (int) ((jb - jd) / 30.6001);
        day = jb - jd - (int) (30.6001 * je);
        month = je - 1;
        if (month > 12) month = month - 12;
        year = jc - 4715;
        if (month > 2) year--;
        if (year <= 0) year--;

        return new int[]{year, month, day};
    }

    public static void main(String args[]) {
        //

        // create a new calendar
        GregorianCalendar cal =
                (GregorianCalendar) GregorianCalendar.getInstance();

        // print the current date and time
        System.out.println("" + cal.getTime());

        // get Gregorian change and print it
        System.out.println("Date change:" + cal.getGregorianChange());


        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2016, 0, 5, 11, 20, 30);
        int year = calendar.get(Calendar.YEAR);

        System.out.println("calendar.get(Calendar.YEAR) : " + year);
        System.out.println("calendar.YEAR : " + calendar.YEAR);
        System.out.println("Calendar.YEAR : " + Calendar.YEAR);


        int month = calendar.get(Calendar.MONTH); // jan=1, feb=2,...

        System.out.println("calendar.get(Calendar.MONTH) : " + month);
        System.out.println("calendar.MONTH : " + calendar.MONTH);
        System.out.println("Calendar.MONTH : " + Calendar.MONTH);

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("calendar.get(Calendar.DAY_OF_MONTH) : " + day);
        System.out.println("calendar.DAY_OF_MONTH : " + calendar.DAY_OF_MONTH);
        System.out.println("Calendar.DAY_OF_MONTH : " + Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        System.out.println("calendar.get(Calendar.HOUR_OF_DAY) : " + hour);
        System.out.println("calendar.HOUR_OF_DAY : " + calendar.HOUR_OF_DAY);
        System.out.println("Calendar.HOUR_OF_DAY : " + Calendar.HOUR_OF_DAY);

        int minute = calendar.get(Calendar.MINUTE);

        System.out.println("calendar.get(Calendar.MINUTE) : " + minute);
        System.out.println("calendar.MINUTE : " + calendar.MINUTE);
        System.out.println("Calendar.MINUTE : " + Calendar.MINUTE);

        int second = calendar.get(Calendar.SECOND);

        System.out.println("calendar.get(Calendar.SECOND) : " + second);
        System.out.println("calendar.SECOND : " + calendar.SECOND);
        System.out.println("Calendar.SECOND : " + Calendar.SECOND);

        int millisecond = calendar.get(Calendar.MILLISECOND);

        System.out.println("calendar.get(Calendar.MILLISECOND) : " + millisecond);
        System.out.println("calendar.MILLISECOND : " + calendar.MILLISECOND);
        System.out.println("Calendar.MILLISECOND : " + Calendar.MILLISECOND);

        // System.exit(0);

        // FIRST TEST reference point
        System.out.println("Julian date for " + calendar.getTime() + " : " + toJulian(calendar));
        int curMonth = month + 1;
        System.out.println("Julian date for " + year + "_" + curMonth +  "_" + day + " : "
                + toJulian(new int[]{year, curMonth, day}));


        GregorianCalendar calendar_toJD =  (GregorianCalendar)GregorianCalendar.getInstance();
        calendar_toJD.set(2016, 0, 5, 11, 20, 30);
        calendar_toJD.setGregorianChange(new Date(Long.MAX_VALUE));
        int year_toJD = calendar_toJD.get(Calendar.YEAR);

        System.out.println("calendar_toJD.get(Calendar.YEAR) : " + year_toJD);
        System.out.println("calendar_toJD.YEAR : " + calendar_toJD.YEAR);
        System.out.println("Calendar.YEAR : " + Calendar.YEAR);


        int month_toJD = calendar_toJD.get(Calendar.MONTH); // jan=1, feb=2,...

        System.out.println("calendar_toJD.get(Calendar.MONTH) : " + month_toJD);
        System.out.println("calendar_toJD.MONTH : " + calendar_toJD.MONTH);
        System.out.println("Calendar.MONTH : " + Calendar.MONTH);

        int day_toJD = calendar_toJD.get(Calendar.DAY_OF_MONTH);

        System.out.println("calendar_toJD.get(Calendar.DAY_OF_MONTH) : " + day_toJD);
        System.out.println("calendar_toJD.DAY_OF_MONTH : " + calendar_toJD.DAY_OF_MONTH);
        System.out.println("Calendar.DAY_OF_MONTH : " + Calendar.DAY_OF_MONTH);

        int hour_toJD = calendar_toJD.get(Calendar.HOUR_OF_DAY);

        System.out.println("calendar_toJD.get(Calendar.HOUR_OF_DAY) : " + hour_toJD);
        System.out.println("calendar_toJD.HOUR_OF_DAY : " + calendar_toJD.HOUR_OF_DAY);
        System.out.println("Calendar.HOUR_OF_DAY : " + Calendar.HOUR_OF_DAY);

        int minute_toJD = calendar_toJD.get(Calendar.MINUTE);

        System.out.println("calendar_toJD.get(Calendar.MINUTE) : " + minute_toJD);
        System.out.println("calendar_toJD.MINUTE : " + calendar_toJD.MINUTE);
        System.out.println("Calendar.MINUTE : " + Calendar.MINUTE);

        int second_toJD = calendar_toJD.get(Calendar.SECOND);

        System.out.println("calendar_toJD.get(Calendar.SECOND) : " + second_toJD);
        System.out.println("calendar_toJD.SECOND : " + calendar_toJD.SECOND);
        System.out.println("Calendar.SECOND : " + Calendar.SECOND);

        int millisecond_toJD = calendar_toJD.get(Calendar.MILLISECOND);

        System.out.println("calendar_toJD.get(Calendar.MILLISECOND) : " + millisecond_toJD);
        System.out.println("calendar_toJD.MILLISECOND : " + calendar_toJD.MILLISECOND);
        System.out.println("Calendar.MILLISECOND : " + Calendar.MILLISECOND);

        long julianDay_new = calendar_toJD.getTimeInMillis();
        System.out.println("julianDay_new : " + julianDay_new);


        double julian = (java.lang.Math.floor(365.25 * year_toJD)
                + java.lang.Math.floor(30.6001 * month_toJD) + day_toJD + 1720995.0);
        System.out.println("julian : " + julian);

        if (day_toJD + 31 * (month_toJD + 12 * year_toJD) >= JGREG) {
            // change over to Gregorian calendar
            int ja = (int) (0.01 * year_toJD);
            julian += 2 - ja + (0.25 * ja);
        }
        System.out.println("julian : " + julian);

        System.out.println(" toJulian - julian : " + (toJulian(calendar) - julian));

        JulianDate julianDate = new JulianDate();
        julianDate.setDay(day);
        julianDate.setDay(month);
        julianDate.setYear(year);
        long thisJulianDate = julianDate.convertGregoriantoJulian();
        System.out.println("thisJulianDate : " + thisJulianDate);
        long julianDay_2 = julianDate.julianDay(day,month,year);
        System.out.println("julianDay_2 : " + julianDay_2);
        double julianDate_2 = julianDate.julianDay(day,month,year,hour,minute,second);
        System.out.println("julianDate_2 : " + julianDate_2);

        JulianDate_2 julianDate_2_1 = new JulianDate_2();
        julianDate_2_1.setYear(year);
        julianDate_2_1.setMonth(month);
        julianDate_2_1.setDay(day);
        double julianDay_21 = julianDate_2_1.getJulianDay();
        System.out.println("julianDay_21 : " + julianDay_21);

/*

        Calendar calendar_epochDay =  (GregorianCalendar)GregorianCalendar.getInstance();
        calendar_epochDay.set(1970, 1, 1, 0, 0, 0);
*/

        long julianDay_epochDay = julianDate.julianDay(1970,1,1);
        System.out.println("julianDay_epochDay : " + julianDay_epochDay);

        System.exit(0);





        long TEST_DATE = 1419033600L;
        double dayJulian = 1419033600L;
        int julianToDate[]  = fromJulian(TEST_DATE);
        System.out.println
                ("... back to calendar (julianToDate) : " + julianToDate[0] + " " + julianToDate[1]
                        + " " + julianToDate[2]);

        System.exit(0);

        // FIRST TEST reference point
        System.out.println("Julian date for May 23, 1968 : "
                + toJulian(new int[]{1968, 5, 23}));
        // output : 2440000
        int results[] = fromJulian(toJulian(new int[]{1968, 5, 23}));
        System.out.println
                ("... back to calendar : " + results[0] + " "
                        + results[1] + " " + results[2]);

        // SECOND TEST today
        Calendar today = Calendar.getInstance();
        double todayJulian = toJulian
                (new int[]{today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1,
                        today.get(Calendar.DATE)});
        System.out.println("Julian date for today : " + todayJulian);
        results = fromJulian(todayJulian);
        System.out.println
                ("... back to calendar : " + results[0] + " " + results[1]
                        + " " + results[2]);

        // THIRD TEST
        double date1 = toJulian(new int[]{2005, 1, 1});
        double date2 = toJulian(new int[]{2005, 1, 31});
        System.out.println("Between 2005-01-01 and 2005-01-31 : "
                + (date2 - date1) + " days");

   /*
      expected output :
         Julian date for May 23, 1968 : 2440000.0
         ... back to calendar 1968 5 23
         Julian date for today : 2453487.0
         ... back to calendar 2005 4 26
         Between 2005-01-01 and 2005-01-31 : 30.0 days
   */
    }
}