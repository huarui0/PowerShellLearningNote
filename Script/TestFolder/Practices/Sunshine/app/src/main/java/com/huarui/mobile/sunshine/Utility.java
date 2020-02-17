package com.huarui.mobile.sunshine;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.huarui.mobile.sunshine.data.WeatherContract;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by wanglai on 12/7/2016.
 *  * @see ForecastAdapter
 */

public class Utility {
    /**
     * @see MainSunshineActivity#openPreferredLocationInMap()
     */
    public static String getPreferredLocation(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getString(R.string.pref_location_key),
                context.getString(R.string.pref_location_default));
    }

    /**
     * @see ForecastAdapter#formatHighLows(double, double)
     */
    public static boolean isMetric(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(context.getString(R.string.pref_units_key),
                context.getString(R.string.pref_units_metric))
                .equals(context.getString(R.string.pref_units_metric));
    }
    /**
     * @see ForecastAdapter#formatHighLows(double, double)
     */
    static String formatTemperature(double temperature, boolean isMetric) {
        double temp;
        if ( !isMetric ) {
            temp = 9*temperature/5+32;
        } else {
            temp = temperature;
        }
        return String.format("%.0f", temp);
    }

    /**
     * @see ForecastAdapter#convertCursorRowToUXFormat(Cursor cursor)
     */
    static String formatDate(long dateInMillis) {
        Date date = new Date(dateInMillis);
        return DateFormat.getDateInstance().format(date);
    }

    static String formatDate(String dateString) {
        Date date = WeatherContract.getDateFromDb(dateString);
        return DateFormat.getDateInstance().format(date);
    }
}