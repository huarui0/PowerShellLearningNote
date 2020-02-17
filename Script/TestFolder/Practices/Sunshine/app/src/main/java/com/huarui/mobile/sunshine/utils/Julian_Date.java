package com.huarui.mobile.sunshine.utils;

/**
 * Created by wanglai on 1/7/2017.
 */

public abstract class Julian_Date {

    int day;
    int month;
    int year;
    int between;
    String[] week = { "Sonntag", "Montag", "Dienstag", "Mittwoch",
            "Donnerstag", "Freitag", "Samstag" };


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long convertGregoriantoJulian() {
        double y = getYear() + (getMonth() - 2.85) / 12;
        double a = (int) (367 * y) - 1.75 * (int) (y) + getDay();
        double b = (int) (a) - 0.75 * (int) (y / 100);
        long jd = (int) (b) + 1721115;
        return jd;
    }
    public long getDaysBetween(long date1, long date2) {

        return between;
    }

    public long julianDay(int day, int month, int year){
        long jDay = 0;
        long JanOrFeb = (14 - month) / 12;
        long yr = (long) Math.floor(year + 4800 - JanOrFeb);
        long mon = (long) Math.floor(month + (12 * JanOrFeb) - 3);
        jDay = (long) Math.floor(day + (((153 * mon) + 2) / 5) + (365 * yr) + (yr / 4) + (yr / 400) - (yr / 100) - 32045);
        return jDay;
    }

    public double julianDay(int day, int month, int year, double hour, double minute, double second){
        double jDay = 0;
        jDay = ((hour - 12) / 24) + (minute / 1440) + (second / 86400);
        return julianDay(day, month, year) + jDay;
    }
}
