// http://210.51.185.134/WEB-INF/classes/cn/cri/cloudbridgewx/message/utils/DateUtil.java

// http://commons.apache.org/proper/commons-lang/
// http://commons.apache.org/proper/commons-lang/javadocs/api-3.5/index.html
// https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/package-summary.html
package com.huarui.mobile.sunshine.utils;

/**
 * Created by wanglai on 12/29/2016.
 */

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * 日期类型与字符串类型相互转换
 *
 * @author lcj
 * @version V1.0
 * @createDate 2014-2-14 下午12:36:30
 * @lastModified 最后修改时间
 * @editedBy 编辑人
 */
public class DateUtil {

    /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    public static final String DATETIME_PATTERN_H = "yyyy-MM-dd HH:mm:ss";

    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;

    /**
     * 暂时不用
     *
     * @param JD
     * @return
     */
    protected static final float normalizedJulian(float JD) {

        float f = Math.round(JD + 0.5f) - 0.5f;

        return f;
    }

    /**
     * 浮点值转换成日期格式<br>
     * 暂时不用
     * Returns the Date from a julian. The Julian date will be converted to noon GMT,
     * such that it matches the nearest half-integer (i.e., a julian date of 1.4 gets
     * changed to 1.5, and 0.9 gets changed to 0.5.)
     *
     * @param JD the Julian date
     * @return the Gregorian date
     */
    public static final Date toDate(float JD) {

		/* To convert a Julian Day Number to a Gregorian date, assume that it is for 0 hours, Greenwich time (so
         * that it ends in 0.5). Do the following calculations, again dropping the fractional part of all
		 * multiplicatons and divisions. Note: This method will not give dates accurately on the
		 * Gregorian Proleptic Calendar, i.e., the calendar you get by extending the Gregorian
		 * calendar backwards to years earlier than 1582. using the Gregorian leap year
		 * rules. In particular, the method fails if Y<400. */
        float Z = (normalizedJulian(JD)) + 0.5f;
        float W = (int) ((Z - 1867216.25f) / 36524.25f);
        float X = (int) (W / 4f);
        float A = Z + 1 + W - X;
        float B = A + 1524;
        float C = (int) ((B - 122.1) / 365.25);
        float D = (int) (365.25f * C);
        float E = (int) ((B - D) / 30.6001);
        float F = (int) (30.6001f * E);
        int day = (int) (B - D - F);
        int month = (int) (E - 1);

        if (month > 12) {
            month = month - 12;
        }

        int year = (int) (C - 4715); //(if Month is January or February) or C-4716 (otherwise)

        if (month > 2) {
            year--;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // damn 0 offsets
        c.set(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late  the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late) {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     *
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c) {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }

    /**
     * 暂时不用
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     *
     * @param date
     * @return the julian day number
     */
    public static final float toJulian(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return toJulian(c);
    }

    /**
     * 日期增加
     *
     * @param isoString 日期字符串
     * @param fmt       格式
     * @param field     年/月/日 Calendar.YEAR/Calendar.MONTH/Calendar.DATE
     * @param amount    增加数量
     * @return
     * @throws ParseException
     */
    public static final String dateIncrease(String isoString, String fmt, int field, int amount) {

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(stringToDate(isoString, fmt, true));
            cal.add(field, amount);

            return dateToString(cal.getTime(), fmt);

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param fmt       the time field.
     * @param field     the time field.
     * @param up        Indicates if rolling up or rolling down the field value.
     * @throws ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, String fmt, int field, boolean up)
            throws ParseException {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
        cal.setTime(stringToDate(isoString, fmt));
        cal.roll(field, up);

        return dateToString(cal.getTime(), fmt);
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param field     the time field.
     * @param up        Indicates if rolling up or rolling down the field value.
     * @throws ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, int field, boolean up)
            throws ParseException {

        return roll(isoString, DATETIME_PATTERN, field, up);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateText 字符串
     * @param format   日期格式
     * @param lenient  日期越界标志
     * @return
     */
    public static Date stringToDate(String dateText, String format, boolean lenient) {

        if (dateText == null) {

            return null;
        }

        DateFormat df = null;

        try {

            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);

            return df.parse(dateText);
        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     * @param format     日期格式
     * @return
     */
    public static Date stringToDate(String dateString, String format) {

        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     */
    public static Date stringToDate(String dateString) {

        return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /**
     * @param date
     * @return 带时间的Date
     * @Title: StringToDate
     * @Description: 字符串转换为日期
     * @author Lcj
     * @createTime 2014年3月25日 下午5:55:42
     */
    public static Date StringToDate1(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN_H);
        Date rdate = null;
        try {
            rdate = sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rdate;
    }

    /**
     * 根据时间变量返回时间字符串
     *
     * @param pattern 时间字符串样式
     * @param date    时间变量
     * @return 返回时间字符串
     */
    public static String dateToString(Date date, String pattern) {

        if (date == null) {

            return null;
        }

        try {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        } catch (Exception e) {

            return null;
        }
    }


    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 返回当前时间
     *
     * @return 返回当前时间
     */
    public static Date getCurrentDateTime() {
        java.util.Calendar calNow = java.util.Calendar.getInstance();
        java.util.Date dtNow = calNow.getTime();

        return dtNow;
    }

    /**
     * 返回当前日期字符串
     *
     * @param pattern 日期字符串样式
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        return dateToString(getCurrentDateTime(), pattern);
    }

    /**
     * 返回当前日期字符串 yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentDateString() {
        return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 返回当前日期+时间字符串 yyyy-MM-dd hh:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateToStringWithTime(Date date) {

        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     * 日期增加-按时增加
     *
     * @param date
     * @param hours
     * @return java.util.Date
     */
    public static Date dateIncreaseByHour(Date date, int hours) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);

        return cal.getTime();
    }

    /**
     * 日期增加-按日增加
     *
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByDay(Date date, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    /**
     * 日期增加-按月增加
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByMonth(Date date, int mnt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);

        return cal.getTime();
    }

    /**
     * 日期增加-按年增加
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByYear(Date date, int mnt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);

        return cal.getTime();
    }

    /**
     * 日期增加
     *
     * @param date 日期字符串 yyyy-MM-dd
     * @param days
     * @return 日期字符串 yyyy-MM-dd
     */
    public static String dateIncreaseByDay(String date, int days) {
        return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
    }

    /**
     * 日期增加
     *
     * @param date 日期字符串
     * @param fmt  日期格式
     * @param days
     * @return
     */
    public static String dateIncreaseByDay(String date, String fmt, int days) {
        return dateIncrease(date, fmt, Calendar.DATE, days);
    }

    /**
     * 日期字符串格式转换
     *
     * @param src    日期字符串
     * @param srcfmt 源日期格式
     * @param desfmt 目标日期格式
     * @return
     */
    public static String stringToString(String src, String srcfmt, String desfmt) {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }

    public static final String getCurrentTime() {
        long time = Calendar.getInstance().getTimeInMillis();

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.UTC);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"));

        // return DateFormatUtils.format(time, "yyyy-MM-dd hh:mm:ss");
    }

    public static final String getCurrentTime(Date d) {
        LocalDateTime localDateTime = LocalDateTime.of(getYear(dateToString(d)), getMonth(dateToString(d)), getDay(dateToString(d)), getHour(dateToString(d)), getMinute(dateToString(d)),getSecond(dateToString(d)));

        String localDateString = localDateTime.format(DateTimeFormatter.ISO_DATE);
        localDateString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        localDateString = localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.UK));

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    public static final String getCurrentDate() {
        long time = Calendar.getInstance().getTimeInMillis();

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.UTC);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"));

        // return DateFormatUtils.format(time, "yyyy-MM-dd 00:00:00");
    }

    public static final String getCurrentDate(Date d) {
        LocalDateTime localDateTime = LocalDateTime.of(getYear(dateToString(d)), getMonth(dateToString(d)), getDay(dateToString(d)), getHour(dateToString(d)), getMinute(dateToString(d)),getSecond(dateToString(d)));

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));

        // return DateFormatUtils.format(d, "yyyy-MM-dd 00:00:00");
    }

    public static final String getCurrentDateShortStyle() {
        long time = Calendar.getInstance().getTimeInMillis();
        LocalDateTime localDateTime =  LocalDateTime.ofEpochSecond(time,0,ZoneOffset.UTC);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"));

        // return DateFormatUtils.format(time, "yyyy-MM-dd");
    }

    public static final String getCurrentDateShortStyle(Date d) {

        LocalDateTime localDateTime = LocalDateTime.of(getYear(dateToString(d)), getMonth(dateToString(d)), getDay(dateToString(d)), getHour(dateToString(d)), getMinute(dateToString(d)),getSecond(dateToString(d)));

        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // return DateFormatUtils.format(d, "yyyy-MM-dd");
    }

    public static final String shortStyle(String longStyleDate) {
        if (longStyleDate == null || "".equals(longStyleDate))
            return "0000-00-00";
        return longStyleDate.substring(0, 10);
    }

    public static final String longStyle(String shortStyleDate) {
        if (shortStyleDate == null || "".equals(shortStyleDate))
            return "0000-00-00 00:00:00";
        return shortStyleDate + " 00:00:00";
    }

    /**
     * 日期增加-按日增加
     *
     * @param days
     * @return java.util.Date:返回与当天间隔指定日期的日期,返回日期格式"yyyy-MM-dd 00:00:00");
     */
    public static String dateIncreaseByDay(int days) {
        // Date d = dateIncreaseByDay(new Date(), days);
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime =localDateTime.plusDays(days);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));

        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd 00:00:00");
    }

    /**
     * 日期增加-按日增加
     *
     * @param hours
     * @return java.util.Date:返回与当天间隔指定日期的时间,日期格式"yyyy-MM-dd kk:mm:ss");
     */
    public static String timeIncreaseByHour(int hours) {
        // Date d = dateIncreaseByHour(new Date(), hours);
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime =localDateTime.plusHours(hours);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));

        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd kk:mm:ss");
    }

    /**
     * 日期增加-按日增加
     *
     * @param days
     * @return java.util.Date:返回与当天间隔指定日期的时间,日期格式"yyyy-MM-dd kk:mm:ss");
     */
    public static String timeIncreaseByDay(int days) {
        // Date d = dateIncreaseByDay(new Date(), days);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime =localDateTime.plusDays(days);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 日期增加-按日增加
     *
     * @param weeks
     * @return java.util.Date:返回与当天间隔指定日期的日期,返回日期格式"yyyy-MM-dd 00:00:00");
     */
    public static String dateIncreaseByWeek(int weeks) {
        int days = 7 * weeks;
        // Date d = dateIncreaseByDay(new Date(), days);

        LocalDateTime localDateTime = LocalDateTime.now();
        // localDateTime =localDateTime.plusDays(days);
        localDateTime =localDateTime.plusWeeks(weeks);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));

        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd 00:00:00");
    }

    /**
     * 日期增加-按日增加
     *
     * @param weeks
     * @return java.util.Date:返回与当天间隔指定日期的时间,日期格式"yyyy-MM-dd kk:mm:ss");
     */
    public static String timeIncreaseByWeek(int weeks) {
        int days = 7 * weeks;
        // Date d = dateIncreaseByDay(new Date(), days);

        LocalDateTime localDateTime = LocalDateTime.now();
        // localDateTime =localDateTime.plusDays(days);
        localDateTime =localDateTime.plusWeeks(weeks);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 日期增加-按月增加
     *
     * @param mnt
     * @return java.util.Date:返回与当天间隔指定月的日期,
     */
    public static String dateIncreaseByMonth(int mnt) {
        // Date d = dateIncreaseByMonth(new Date(), mnt);

        LocalDateTime localDateTime = LocalDateTime.now();
        // localDateTime =localDateTime.plusDays(days);
        localDateTime =localDateTime.plusMonths(mnt);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));

        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd 00:00:00");
    }

    /**
     * 日期增加-按月增加
     *
     * @param mnt
     * @return java.util.Date:返回与当天间隔指定月的时间,
     */
    public static String timeIncreaseByMonth(int mnt) {
        // Date d = dateIncreaseByMonth(new Date(), mnt);

        LocalDateTime localDateTime = LocalDateTime.now();
        // localDateTime =localDateTime.plusDays(days);
        localDateTime =localDateTime.plusMonths(mnt);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 日期增加-按年增加
     *
     * @param year
     * @return java.util.Date:返回与当天间隔指定年的时间
     */
    public static String dateIncreaseByYear(int year) {

       // Date d = dateIncreaseByYear(new Date(), mnt);


        LocalDateTime localDateTime = LocalDateTime.now();
        // localDateTime =localDateTime.plusDays(days);
        localDateTime =localDateTime.plusYears(year);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));


        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd 00:00:00");
    }

    /**
     * 日期增加-按年增加
     *
     * @param year
     * @return java.util.Date:返回与当天间隔指定年的时间
     */
    public static String timeIncreaseByYear(int year) {

        // Date d = dateIncreaseByYear(new Date(), mnt);

        LocalDateTime localDateTime = LocalDateTime.now();
        // localDateTime =localDateTime.plusDays(days);
        localDateTime =localDateTime.plusYears(year);
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));


        // return DateFormatUtils.format(d.getTime(), "yyyy-MM-dd kk:mm:ss");
    }

    /**
     * 获取本周的第一天
     *
     * @return
     */
    public static Date weekBegin() {
        Calendar mth = Calendar.getInstance();
        mth.set(Calendar.DAY_OF_WEEK, mth.getActualMinimum(Calendar.DAY_OF_WEEK));
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        return mth.getTime();
    }

    /**
     * 获取本周最后一天
     *
     * @return
     */
    public static Date weekEnd() {
        Calendar mth = Calendar.getInstance();
        mth.set(Calendar.DAY_OF_WEEK, mth.getActualMaximum(Calendar.DAY_OF_WEEK));
        mth.set(Calendar.HOUR_OF_DAY, 23);
        mth.set(Calendar.MINUTE, 59);
        mth.set(Calendar.SECOND, 59);
        return mth.getTime();
    }


    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static Date monthBegin() {
        Calendar mth = Calendar.getInstance();
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMinimum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        return mth.getTime();
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static Date monthEnd() {
        Calendar mth = Calendar.getInstance();
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMaximum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 23);
        mth.set(Calendar.MINUTE, 59);
        mth.set(Calendar.SECOND, 59);
        return mth.getTime();
    }

    /**
     * 获取指定日期当月的第一天
     *
     * @return
     * @mnt:偏离量，负数为当月前月份
     */
    public static Date monthBeginByDate(Date date) {
        Calendar mth = Calendar.getInstance();
        mth.setTime(date);
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMinimum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        return mth.getTime();
    }

    /**
     * 获取指定日期当月的最后一天
     *
     * @return
     */
    public static Date monthEndByDate(Date date) {
        Calendar mth = Calendar.getInstance();
        mth.setTime(date);
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMaximum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 23);
        mth.set(Calendar.MINUTE, 59);
        mth.set(Calendar.SECOND, 59);
        return mth.getTime();
    }

    /**
     * 获取指定日期当月的第一天
     *
     * @return
     * @mnt:偏离量，负数为当月前月份
     */
    public static Date yearBeginByDate(Date date) {
        Calendar mth = Calendar.getInstance();
        mth.setTime(date);
        mth.set(Calendar.DAY_OF_YEAR, mth.getActualMinimum(Calendar.DAY_OF_YEAR));
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        return mth.getTime();
    }

    /**
     * 获取指定日期当月的最后一天
     *
     * @return
     */
    public static Date yearEndByDate(Date date) {
        Calendar mth = Calendar.getInstance();
        mth.setTime(date);
        mth.set(Calendar.DAY_OF_YEAR, mth.getActualMaximum(Calendar.DAY_OF_YEAR));
        mth.set(Calendar.HOUR_OF_DAY, 23);
        mth.set(Calendar.MINUTE, 59);
        mth.set(Calendar.SECOND, 59);
        return mth.getTime();
    }

    /**
     * 获取当季的第一天
     *
     * @return
     */
    public static Date seasonBegin() {
        Calendar mth = Calendar.getInstance();
        int season = ((mth.get(Calendar.MONTH)) / 3) + 1;//当前季度值(month从0开始计数)
        int beginMonth = 3 * season - 3; //季度的第一个月

        mth.set(Calendar.MONTH, beginMonth);
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMinimum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        return mth.getTime();
    }

    /**
     * 获取当季最后一天
     *
     * @return
     */
    public static Date seasonEnd() {
        Calendar mth = Calendar.getInstance();
        int season = ((mth.get(Calendar.MONTH)) / 3) + 1;//当前季度值
        int endMonth = 3 * season - 1; //季度的最后一个月
        mth.set(Calendar.MONTH, endMonth);
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMaximum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 23);
        mth.set(Calendar.MINUTE, 59);
        mth.set(Calendar.SECOND, 59);
        Date date = mth.getTime();
        return date;
    }

    /**
     * 获取指定时间所在季节的第一天
     *
     * @return
     */
    public static Date seasonBegin(Date date) {
        Calendar mth = Calendar.getInstance();
        mth.setTime(date);

        int season = ((mth.get(Calendar.MONTH)) / 3) + 1;//当前季度值(month从0开始计数)
        int beginMonth = 3 * season - 3; //季度的第一个月
        mth.set(Calendar.MONTH, beginMonth);
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMinimum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        return mth.getTime();
    }

    /**
     * 获取指定时间所在季度的最后一天
     *
     * @return
     */
    public static Date seasonEnd(Date date) {
        Calendar mth = Calendar.getInstance();
        mth.setTime(date);

        int season = ((mth.get(Calendar.MONTH)) / 3) + 1;//当前季度值
        int endMonth = 3 * season - 1; //季度的最后一个月
        mth.set(Calendar.MONTH, endMonth);
        mth.set(Calendar.DAY_OF_MONTH, mth.getActualMaximum(Calendar.DAY_OF_MONTH));
        mth.set(Calendar.HOUR_OF_DAY, 23);
        mth.set(Calendar.MINUTE, 59);
        mth.set(Calendar.SECOND, 59);
        return mth.getTime();
    }

    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static Date yearBegin() {
        Calendar mth = Calendar.getInstance();
        mth.set(Calendar.DAY_OF_YEAR, mth.getActualMinimum(Calendar.DAY_OF_YEAR));
        mth.set(Calendar.HOUR_OF_DAY, 0);
        mth.set(Calendar.MINUTE, 0);
        mth.set(Calendar.SECOND, 0);
        return mth.getTime();
    }

    /**
     * 获取当年最后一天
     *
     * @return
     */
    public static Date yearEnd() {
        Calendar mth = Calendar.getInstance();
        mth.set(Calendar.DAY_OF_YEAR, mth.getActualMaximum(Calendar.DAY_OF_YEAR));
        mth.set(Calendar.HOUR_OF_DAY, 23);
        mth.set(Calendar.MINUTE, 59);
        mth.set(Calendar.SECOND, 59);
        return mth.getTime();
    }

    public static int getWeekIndex(Date date) {
        Calendar mth = Calendar.getInstance();
        mth.setTime(date);
        return mth.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getYear(String s) {
        if (s == null || s.length() < 10) {
            return 1970;
        }
        return Integer.parseInt(s.substring(0, 4));
    }

    public static int getMonth(String s) {
        if (s == null || s.length() < 10) {
            return 1;
        }
        return Integer.parseInt(s.substring(5, 7));
    }

    public static int getDay(String s) {
        if (s == null || s.length() < 10) {
            return 1;
        }
        return Integer.parseInt(s.substring(8, 10));
    }

    public static int getHour(String s) {
        if (s == null || s.length() < 13) {
            return 0;
        }
        return Integer.parseInt(s.substring(11, 13));
    }

    public static int getMinute(String s) {
        if (s == null || s.length() < 16) {
            return 0;
        }
        return Integer.parseInt(s.substring(14, 16));
    }


    public static int getSecond(String s) {
        if (s == null || s.length() < 19) {
            return 0;
        }
        return Integer.parseInt(s.substring(17, 19));
    }

    public static Calendar getCal(String s) {
        Calendar cal = Calendar.getInstance();
        cal.set(getYear(s), getMonth(s) - 1, getDay(s), getHour(s), getMinute(s), getSecond(s));
        //System.out.println(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)+" "+cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
        return cal;
    }

    /**
     * 获取两个时间相隔多少天多少小时多少分
     *
     * @param type
     * @param begin
     * @param end
     * @return
     */
    public static String getBetween(String type, Date begin, Date end) {
        long between = 0;
        between = (end.getTime() - begin.getTime());
        long day = 0;
        long hour = 0;
        long min = 0;
        long s = 0;
        String returnStr = "";
        //type D天H小时M分
        if (type.contains("D")) {
            day = between / (24 * 60 * 60 * 1000);
            if (day > 0)
                returnStr += day + "天";
        }
        if (type.contains("H")) {
            hour = (between / (60 * 60 * 1000) - day * 24);
            if (hour > 0)
                returnStr += hour + "小时";
        }
        if (type.contains("M")) {
            min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
            if (min > 0)
                returnStr += min + "分";
        }
        if (type.contains("S")) {
            s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            if (s > 0)
                returnStr += s + "秒";
        }
        if (type.contains("c")) {
            s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            if (s > 0)
                returnStr += s;
        }

        //long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        //long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
        //        - min * 60 * 1000 - s * 1000);
        //System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" + ms
        //        + "毫秒");
        return returnStr;
    }

    /**
     * @param time 传入秒级的timestamp 如："1395024901"
     * @return String
     * @Title: homeShowTime
     * @Description: 返回主页时间显示
     * @author Lcj
     * @createTime 2014年3月14日 下午3:47:38
     */
    public static String homeShowTime(String time) {
        long timel = Long.parseLong(time);
        if (time.length() > 10) {
            timel = timel / 1000;
        }
        long currentTime = System.currentTimeMillis() / 1000;
        long cha = currentTime - timel;
        String fen = String.valueOf(cha / 60);
        String miao = String.valueOf(cha % 60);
        int fenI = Integer.parseInt(fen);
        time += "000";
        Long ltime = Long.parseLong(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = format.format(ltime);
        String current = format.format(new Date());
        String currentDay = current.substring(0, 10);
        String day = time.substring(0, 10);
        String result = "";
        if (fenI < 1) {
            result = miao + "秒前";
        } else if (fenI >= 1 && fenI < 60) {
            result = fen + "分钟前";
        } else if (currentDay.equals(day)) {
            result = "今天 " + time.substring(11, 16);
        } else {
            result = time.substring(5, 10) + " " + time.substring(11, 16);
        }
        return result;
    }

    /**
     * @param timestamp 字符串类型timestamp(如：1396348519)
     * @return Date
     * @Title: timestampToDate
     * @Description: 根据传入的字符串返回date类型
     * @author Lcj
     * @createTime 2014年4月1日 下午6:49:46
     */
    public static Date timestampToDate(String timestamp) {
        Date d = new Date();
        timestamp += "000";
        Timestamp ts = new Timestamp(Long.parseLong(timestamp));
        d = ts;
        return d;
    }

    public static String formatDate(Date date, String pattern) {
        DateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parseDate(String date) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public static String dateToSecond(Date date) {
        long m = date.getTime();
        m = m / 1000;
        String result = String.valueOf(m);
        return result;
    }

    /**
     * @param date
     * @return Long
     * @Title: dateToSecondL
     * @Description: 将日期转换为长整型秒
     * @author Lcj
     * @createTime 2014年6月11日 上午9:52:35
     */
    public static Long dateToSecondL(Date date) {
        long m = date.getTime();
        m = m / 1000;
        return m;
    }

    /**
     * @param day
     * @param x
     * @return String
     * @Title: addDateMinut
     * @Description: 获取几分钟后的时间
     * @author Lcj
     * @createTime 2014年6月17日 下午9:11:06
     */
    public static String addDateMinut(String day, int x) {
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN_H);// 24小时制
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, x);
        date = cal.getTime();
        cal = null;
        return format.format(date);
    }

    public static String secondsToDays(long seconds) {
        if (seconds > 0) {
            // 一天的秒数
            int s = 60 * 60 * 24;
            long days = seconds / s;
            long minutes = (seconds % s) / 3600;
            long second = ((seconds % s) % 3600) / 60;
            return days + "天&nbsp;" + minutes + "小时&nbsp;" + second + "分";
        } else {
            return "0";
        }
    }

    /**
     * @param time
     * @return long
     * @Title: DateToSec
     * @Description: 将‘yyy-MM-dd'T'HH:mm:ss+0000’日期转换为秒数
     * @author Lcj
     * @createTime 2014年7月25日 下午5:24:03
     */
    public static long DateToSec(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss+0000");
        long timeStart = 0;
        try {
            timeStart = sdf.parse(time).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeStart / 1000;
    }

    public static String getNow1() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            Date d = df.parse(df.format(new Date()));
            System.out.println(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static void beforeOneHourToNowDate() {
        Calendar calendar = Calendar.getInstance();
/* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date a = null;
//a=df.parse(calendar.getTime());
    }

    public static void main(String[] args) {
        // String testDate = "20140612";
        GregorianCalendar testCalendar = new GregorianCalendar(2014, Calendar.JUNE, 12, 0, 0, 0);
        long testDate = testCalendar.getTimeInMillis();


        Calendar calendar_JD_test = GregorianCalendar.getInstance();
        calendar_JD_test.set(2016, 0, 5, 11, 20, 30);

        float toJulian= toJulian(calendar_JD_test);
        System.out.println("toJulian:" + toJulian);
        System.exit(0);

        getNow1();
//		beforeOneHourToNowDate();


        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        System.out.println(date);


        // get the supported ids for GMT-08:00 (Pacific Standard Time)
        String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0)
            System.exit(0);

        // begin output
        System.out.println("Current Time");

        // create a Pacific Standard Time time zone
        SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);

        // set up rules for Daylight Saving Time
        pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
        pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

        // create a GregorianCalendar with the Pacific Daylight time zone
        // and the current date and time
        Calendar calendar = new GregorianCalendar(pdt);
        Date trialTime = new Date();
        calendar.setTime(trialTime);

        // print out a bunch of interesting things
        System.out.println("ERA: " + calendar.get(Calendar.ERA));
        System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
        System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("DATE: " + calendar.get(Calendar.DATE));
        System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("DAY_OF_WEEK_IN_MONTH: "
                + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
        System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
        System.out.println("ZONE_OFFSET: "
                + (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)));
        System.out.println("DST_OFFSET: "
                + (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000)));

        System.out.println("Current Time, with hour reset to 3");
        calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
        calendar.set(Calendar.HOUR, 3);
        System.out.println("ERA: " + calendar.get(Calendar.ERA));
        System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
        System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("DATE: " + calendar.get(Calendar.DATE));
        System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("DAY_OF_WEEK_IN_MONTH: "
                + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
        System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
        System.out.println("ZONE_OFFSET: "
                + (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000))); // in hours
        System.out.println("DST_OFFSET: "
                + (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000))); // in hours


    }
}