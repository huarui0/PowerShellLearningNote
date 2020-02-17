package com.huarui.mobile.sunshine;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Reference:
 * ForecastAdapter.java
 * https://github.com/boxme/Udacity-Android-Class-Sunshine/blob/master/app/src/main/java/com/desmond/sunshine/ForecastAdapter.java
 */

/**
 * Created by wanglai on 1/13/2017.
 */

public class ForecastAdapter extends CursorAdapter {

    public final String TAG = ForecastAdapter.class.getSimpleName();
    private final int VIEW_TYPE_TODAY = 0;
    private final int VIEW_TYPE_FUTURE_DAY = 1;

    private boolean mUseTodayLayout;

    public ForecastAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    public void setUseTodayLayout(boolean useTodayLayout) {
        mUseTodayLayout = useTodayLayout;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 && mUseTodayLayout) ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
    }

    @Override
    public int getViewTypeCount() {
        // There are two different layouts
        return 2;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_forecast_new, parent, false);
        // return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
// Read date from cursor
        long dateInMillis = cursor.getLong(ForecastFragment.COL_WEATHER_DATE);
        // 还是用下面的好？
        // long dateInMillis = cursor.getLong(cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE));

        // Find TextView and set formatted date on it
        viewHolder.dateView.setText(Utility.formatDate(dateInMillis));
    }

    /**
     * Cache of the child views for a forecast list item
     */
    public static class viewHolder {
        // public final ImageView iconView;
        public static TextView dateView;
        public static TextView descriptionView;
        public static TextView highTempView;
        public static TextView lowTempView;

        public viewHolder(View view) {
            // iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_date_textview_new);
            descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview_new);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview_new);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview_new);
        }
    }
}
