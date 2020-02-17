// https://github.com/ozkuran/Algorithms/blob/3d942255ffa5c9602bbe45745e5ff737117fbb5b/java/src/com/ozkuran/algorithms/time/julianday/JulianDayTest.java

package com.huarui.mobile.sunshine;

import com.huarui.mobile.sunshine.utils.JulianDay;

import junit.framework.TestCase;

/**
 * Created by wanglai on 1/7/2017.
 */


public class JulianDayTest extends TestCase {

    public void testConvertDayMonthYearToJulianDay() throws Exception {
        JulianDay jd = new JulianDay();
        long jday = jd.julianDay(24,1,2015);
        assertEquals(jday, 2457047);
    }

    public void testConvertDayMonthYearHourMinuteSecondToJulianDay() throws Exception {
        JulianDay jd = new JulianDay();
        double jday = jd.julianDay(24,1,2015,0,0,0);
        assertEquals(jday, 2457046.5);

    }
}
