// https://gist.github.com/profsergiocosta/b94601ca3fa95a2343a8
// https://gist.github.com/talhakosen/f94ee9ae97d3bc3f57dc740037d84070
// testBasicWeatherQuery()
// https://github.com/TimAlonso/Android_Sunshine_Udacity/tree/master/Sunshine/app/src/main/java/com/example/talonso/sunshine/sync
// http://www.java2s.com/Open-Source/Android_Free_Code/Example/course/TimAlonso_Android_Sunshine_Udacity.htm
// http://www.java2s.com/Open-Source/Android_Free_Code/App/course/com_example_kylelehman_sunshineForecastFragment_java.htm
package com.huarui.mobile.sunshine;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.huarui.mobile.sunshine.data.WeatherContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragmentTest extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = ForecastFragmentTest.class.getSimpleName();

    private static final int FORECAST_LOADER = 0;
    // For the forecast view we're showing only a small subset of the stored data.
    // Specify the columns we need.
    private static final String[] FORECAST_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            WeatherContract.WeatherEntry.TABLE_NAME + "." + WeatherContract.WeatherEntry._ID,
            WeatherContract.WeatherEntry.COLUMN_DATE,
            WeatherContract.WeatherEntry.COLUMN_SHORT_DESC,
            WeatherContract.WeatherEntry.COLUMN_MAX_TEMP,
            WeatherContract.WeatherEntry.COLUMN_MIN_TEMP,
            WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING,
            WeatherContract.WeatherEntry.COLUMN_WEATHER_ID,
            WeatherContract.LocationEntry.COLUMN_COORD_LAT,
            WeatherContract.LocationEntry.COLUMN_COORD_LONG
    };
    // These indices are tied to FORECAST_COLUMNS.  If FORECAST_COLUMNS changes, these
    // must change.
    static final int COL_WEATHER_ID = 0;
    static final int COL_WEATHER_DATE = 1;
    static final int COL_WEATHER_DESC = 2;
    static final int COL_WEATHER_MAX_TEMP = 3;
    static final int COL_WEATHER_MIN_TEMP = 4;
    static final int COL_LOCATION_SETTING = 5;
    static final int COL_WEATHER_CONDITION_ID = 6;
    static final int COL_COORD_LAT = 7;
    static final int COL_COORD_LONG = 8;

    private ArrayAdapter<String> mForecastArrayAdapter;
    private SimpleCursorAdapter mForecastSimCurAdapter;
    private CursorAdapter mForecastAdapter;

    int forecastAdapterType = 1;
    static final int ARRARY_ADAPTER_TYPE = 0;
    static final int SIMPLE_CURROR_ADAPTER_TYPE = 1;
    static final int CURROR_ADAPTER_TYPE = 2;

    OnRefreshItemListener mRefreshItemListener;

    private SimpleCursorAdapter mAdapter;

    public ForecastFragmentTest() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_forecastfragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh_fragment) {
            FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity());
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

            String location = prefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));

            // weatherTask.execute();
            //使用参数模式
            /*
            weatherTask.execute("Fuzhou,cn"); // "201306,91"
            */
            // change to follow statement;
            weatherTask.execute(location); // "201306,91"
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initializeContentLoader();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forcast);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String forecast = mForecastArrayAdapter.getItem(position);
                // Toast can be a debug tool here.
//                Toast.makeText(getActivity(), forecast, Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, forecast);
//                startActivity(intent);
            }
        });

        return rootView;
    }

    /**
     * initializeContentLoader
     */
    private void initializeContentLoader() {

        String[] columns = new String[]{
                "date", "short_desc", "max", "min"};
/*
        String[] columns = new String[]{
                WeatherContract.WeatherEntry.COLUMN_DATE,
                WeatherContract.WeatherEntry.COLUMN_SHORT_DESC,
                WeatherContract.WeatherEntry.COLUMN_MAX_TEMP,
                WeatherContract.WeatherEntry.COLUMN_MIN_TEMP
        };
*/

/*
        int[] viewIds = new int[]{
                R.id.text1, R.id.text2, R.id.text3};
*/

        int[] viewIds = new int[]{
                R.id.list_item_date_textview_new,
                R.id.list_item_forecast_textview_new,
                R.id.list_item_high_textview_new,
                R.id.list_item_low_textview_new
        };

        mAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.list_item_forecast_new, null, columns, viewIds, 0);

        mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                boolean isMetric = Utility.isMetric(getActivity());
                switch (columnIndex) {
                    case COL_WEATHER_MAX_TEMP:
                    case COL_WEATHER_MIN_TEMP: {
                        ((TextView) view).setText(Utility.formatTemperature(cursor.getDouble(columnIndex), isMetric));
                        return true;
                    }
                    case COL_WEATHER_DATE: {
                        long dateString = cursor.getLong(columnIndex);
                        TextView dateView = (TextView) view;
                        dateView.setText(Utility.formatDate(dateString));
                        return true;
                    }
                }
                return false;
            }
        });

        // setListAdapter(mAdapter);
        // 改为
/*
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forcast);
        listView.setAdapter(mAdapter);
*/
    }


    private void updateWeather() {
        FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = prefs.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));
        weatherTask.execute(location);
    }

    // Fragment中的方法，供Activity调用
    public void updateWeatherByActivity() {
        updateWeather();
        Log.i("ForecastFragment", "执行updateWeather方法");
    }

    // // fragment和activity共享点击事件
    public void RefreshFragmentListItem() {
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask(getActivity());
        mRefreshItemListener.OnRefreshItemListener(fetchWeatherTask);
        Log.i("ForecastFragment", "fragment和activity共享Method");
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    // 定义接口
    public interface OnRefreshItemListener {
        public void OnRefreshItemListener(FetchWeatherTask weatherTask);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mRefreshItemListener = (OnRefreshItemListener) context;
        } catch (Exception e) {
            // TODO: handle exception
            throw new ClassCastException(context.toString() + "must implement OnButton2ClickListener");
        }
    }

    // 下面这个为主
// https://github.com/fggeraissate/SunshineWeatherApp/blob/master/app/src/main/java/com/example/android/sunshine/FetchWeatherTask.java
// https://github.com/boxme/Udacity-Android-Class-Sunshine/blob/master/app/src/main/java/com/desmond/sunshine/FetchWeatherTask.java
// https://github.com/joaopedronardari/SunshineAppLollipop/blob/master/app/src/main/java/com/example/android/sunshine/app/FetchWeatherTask.java
    public class FetchWeatherTask_Inner extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        private Context mContext;

        public FetchWeatherTask_Inner(Context context) {
            mContext = context;
        }

        public FetchWeatherTask_Inner() {
            mContext = getContext(); // getActivity(); --用哪个更合适？
        }

        /**
         * The date/time conversion code is going to be moved outside the asynctask later,
         * so for convenience we're breaking it out into its own method now.
         */
        private String getReadableDateString(long time) {
            // Because the API returns a unix timestamp (measured in seconds),
            // it must be converted to milliseconds in order to be converted to valid date.
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(time);
        }

        /**
         * Prepare the weather high/lows for presentation.
         */
        private String formatHighLows_Old(double high, double low) {
            // For presentation, assume the user doesn't care about tenths of a degree.
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        /**
         * Prepare the weather high/lows for presentation.
         */
        private String formatHighLows(double high, double low) {
            // Data is fetched in Celsius by default.
            // If user prefers to see in Fahrenheit, convert the values here.
            // We do this rather than fetching in Fahrenheit so that the user can
            // change this option without us having to re-fetch the data once
            // we start storing the values in a database.
            SharedPreferences sharedPrefs =
                    PreferenceManager.getDefaultSharedPreferences(mContext);
            String unitType = sharedPrefs.getString(
                    mContext.getString(R.string.pref_units_key),
                    mContext.getString(R.string.pref_units_metric));

            if (unitType.equals(mContext.getString(R.string.pref_units_imperial))) {
                high = (high * 1.8) + 32;
                low = (low * 1.8) + 32;
            } else if (!unitType.equals(getContext().getString(R.string.pref_units_metric))) {
                Log.d(LOG_TAG, "Unit type not found:" + unitType);
            }

            // For presentation, assume the user doesn't care about tenths of a degree.
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        // Other version

        /**
         * Prepare the weather high/lows for presentation.
         */
        private String formatHighLows_OtherVersion(double high, double low) {
            // Data is fetched in Celsius by default.
            // If user prefers to see in Fahrenheit, convert the values here.
            // We do this rather than fetching in Fahrenheit so that the user can
            // change this option without us having to re-fetch the data once
            // we start storing the values in a database
            SharedPreferences sharePrefs =
                    PreferenceManager.getDefaultSharedPreferences(getContext());
            String unitType = sharePrefs.getString(
                    getContext().getString(R.string.pref_units_key),
                    getContext().getString(R.string.pref_units_metric));

            if (unitType.equals(getContext().getString(R.string.pref_units_imperial))) {
                high = (high * 1.8) + 32;
                low = (low * 1.8) + 32;
            } else if (!unitType.equals(getContext().getString(R.string.pref_units_metric))) {
                Log.d(LOG_TAG, "Unit type not found: " + unitType);
            }

            // For presentation, assume the user doesn't care about tenths of a degree.
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        /**
         * Helper method to handle insertion of a new location in the weather database.
         *
         * @param locationSetting The location string used to request updates from the server.
         * @param cityName        A human-readable city name, e.g "Mountain View"
         * @param lat             the latitude of the city
         * @param lon             the longitude of the city
         * @return the row ID of the added location.
         */
        long addLocation(String locationSetting, String cityName, double lat, double lon) {

            long locationId;

            // First, check if the location with this city name exists in the db
            Cursor cursorLocation = mContext.getContentResolver().query(
                    WeatherContract.LocationEntry.CONTENT_URI,
                    new String[]{WeatherContract.LocationEntry._ID},
                    WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + " = ?",
                    new String[]{locationSetting},
                    null);

            // If it exists, return the current ID
            if (cursorLocation.moveToFirst()) {
                int locationIdIndex = cursorLocation.getColumnIndex(WeatherContract.LocationEntry._ID);
                locationId = cursorLocation.getLong(locationIdIndex);

                // Otherwise, insert it using the content resolver and the base URI
            } else {
                // Now the content provider is set up, inserting rows of data is pretty simple.
                // First create a ContentValues object to hold the data you want to insert.
                ContentValues contentValuesLocation = new ContentValues();

                // Then add the data, along with the corresponding name of the data type, so the content
                // provider knows what kind of value is being inserted.
                contentValuesLocation.put(WeatherContract.LocationEntry.COLUMN_CITY_NAME, cityName);
                contentValuesLocation.put(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, locationSetting);
                contentValuesLocation.put(WeatherContract.LocationEntry.COLUMN_COORD_LAT, lat);
                contentValuesLocation.put(WeatherContract.LocationEntry.COLUMN_COORD_LONG, lon);

                // Finally, insert location data into the database.
                Uri uriInserted = mContext.getContentResolver().insert(
                        WeatherContract.LocationEntry.CONTENT_URI,
                        contentValuesLocation);

                // The resulting URI contains the ID for the row. Extract the locationId from the Uri.
                locationId = ContentUris.parseId(uriInserted);
            }

            cursorLocation.close();

            // Wait, that worked?  Yes!
            return locationId;
        }

        /**
         * Take the String representing the complete forecast in JSON Format and
         * pull out the data we need to construct the Strings needed for the wireframes.
         * <p>
         * Fortunately parsing is easy:  constructor takes the JSON string and converts it
         * into an Object hierarchy for us.
         */
        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_WEATHER = "weather";
            final String OWM_TEMPERATURE = "temp";
            final String OWM_MAX = "max";
            final String OWM_MIN = "min";
            final String OWM_DESCRIPTION = "main";

            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            // OWM returns daily forecasts based upon the local time of the city that is being
            // asked for, which means that we need to know the GMT offset to translate this data
            // properly.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            Time dayTime = new Time();
            dayTime.setToNow();

            // we start at the day returned by local time. Otherwise this is a mess.
            int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

            // now we work exclusively in UTC
            dayTime = new Time();

            String[] resultStrs = new String[numDays];
            for (int i = 0; i < weatherArray.length(); i++) {
                // For now, using the format "Day, description, hi/low"
                String day;
                String description;
                String highAndLow;

                // Get the JSON object representing the day
                JSONObject dayForecast = weatherArray.getJSONObject(i);

                // The date/time is returned as a long.  We need to convert that
                // into something human-readable, since most people won't read "1400356800" as
                // "this saturday".
                long dateTime;
                // Cheating to convert this to UTC time, which is what we want anyhow
                dateTime = dayTime.setJulianDay(julianStartDay + i);
                day = getReadableDateString(dateTime);

                // description is in a child array called "weather", which is 1 element long.
                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                description = weatherObject.getString(OWM_DESCRIPTION);

                // Temperatures are in a child object called "temp".  Try not to name variables
                // "temp" when working with temperature.  It confuses everybody.
                JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
                double high = temperatureObject.getDouble(OWM_MAX);
                double low = temperatureObject.getDouble(OWM_MIN);

                highAndLow = formatHighLows(high, low);
                resultStrs[i] = day + " - " + description + " - " + highAndLow;
            }

            for (String s : resultStrs) {
                Log.v(LOG_TAG, "Forecast entry: " + s);
            }
            return resultStrs;

        }

        /**
         * Created by wwanglai on 7/10/16.
         * https://gist.github.com/fujianto/d96982ac3d4c4c6c674f
         */
        public class WeatherDataParser {
            /* The date/time conversion code is going to be moved outside the asynctask later,
             * so for convenience we're breaking it out into its own method now.
             */
            private String getReadableDateString(long time) {

                // Because the API returns a unix timestamp (measured in seconds),
                // it must be converted to milliseconds in order to be converted to valid date.
                Date date = new Date(time * 1000);
                SimpleDateFormat format = new SimpleDateFormat("EEE, MMM dd");
                return format.format(date).toString();
            }

            /**
             * Prepare the weather high/lows for presentation.
             */
            private String formatHighLows(double high, double low) {

                // For presentation, assume the user doesn't care about tenths of a degree.
                long roundedHigh = Math.round(high);
                long roundedLow = Math.round(low);
                String highLowStr = roundedHigh + "/" + roundedLow;
                return highLowStr;
            }

            /**
             * Take the String representing the complete forecast in JSON Format and
             * pull out the data we need to construct the Strings needed for the wireframes.
             * <p>
             * Fortunately parsing is easy: constructor takes the JSON string and converts it
             * into an Object hierarchy for us.
             */
            private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
                    throws JSONException {

                // These are the names of the JSON objects that need to be extracted.
                final String OWM_LIST = "list";
                final String OWM_WEATHER = "weather";
                final String OWM_TEMPERATURE = "temp";
                final String OWM_MAX = "max";
                final String OWM_MIN = "min";
                final String OWM_DATETIME = "dt";
                final String OWM_DESCRIPTION = "main";

                JSONObject forecastJson = new JSONObject(forecastJsonStr);
                JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);
                String[] resultStrs = new String[numDays];

                for (int i = 0; i < weatherArray.length(); i++) {
                    // For now, using the format "Day, description, hi/low"
                    String day;
                    String description;
                    String highAndLow;

                    // Get the JSON object representing the day
                    JSONObject dayForecast = weatherArray.getJSONObject(i);

                    // The date/time is returned as a long. We need to convert that
                    // into something human-readable, since most people won't read "1400356800" as
                    // "this saturday".
                    long dateTime = dayForecast.getLong(OWM_DATETIME);
                    day = getReadableDateString(dateTime);

                    // description is in a child array called "weather", which is 1 element long.
                    JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                    description = weatherObject.getString(OWM_DESCRIPTION);

                    // Temperatures are in a child object called "temp". Try not to name variables
                    // "temp" when working with temperature. It confuses everybody.
                    JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
                    double high = temperatureObject.getDouble(OWM_MAX);
                    double low = temperatureObject.getDouble(OWM_MIN);
                    highAndLow = formatHighLows(high, low);
                    resultStrs[i] = day + " - " + description + " - " + highAndLow;
                }
                for (String s : resultStrs) {
                    Log.v(LOG_TAG, "Forecast Entry: " + s);
                }
                return resultStrs;
            }
        }

        @Override
        protected String[] doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            //URL url = null;
            // String querys = "Fuzhou,cn";
            String format = "json";
            String units = "metric";
            int numDays = 7;
            String thisAppid = "5d3bc25020ac396cec829c88d7b3da53";
            try {
/*
                String httpOpenWeatherMapUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Fuzhou,cn&mode=json&units=metric&cnt=7&appid=5d3bc25020ac396cec829c88d7b3da53";
                                                http://api.openweathermap.org/data/2.5/forecast/daily?q=Fuzhou,cn&mode=json&units=metric&cnt=7&appid=5d3bc25020ac396cec829c88d7b3da53
                                                http://api.openweathermap.org/data/2.5/forecast/daily?id=1810821&mode=json&units=metric&cnt=7&appid=5d3bc25020ac396cec829c88d7b3da53
                                                http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7&appid=5d3bc25020ac396cec829c88d7b3da53
                                                http://api.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=5d3bc25020ac396cec829c88d7b3da53
                                                http://api.openweathermap.org/data/2.5/weather?q=Fuzhou,cn&mode=json&units=metric&cnt=7&appid=5d3bc25020ac396cec829c88d7b3da53

                URL url = new URL(httpOpenWeatherMapUrl);
*/
// 改为

                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
                final String QUERY_PARAM = "q";
                final String FORMAT_PARAM = "mode";
                final String UNITS_PARAM = "units";
                final String DAYS_PARAM = "cnt";
                final String APPID_PARAM = "appid";

                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, params[0])
                        .appendQueryParameter(FORMAT_PARAM, format)
                        .appendQueryParameter(UNITS_PARAM, units)
                        .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                        .appendQueryParameter(APPID_PARAM, thisAppid)
                        .build();

                URL url = new URL(builtUri.toString());
                Log.v(LOG_TAG, "Built URI" + builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                Log.v(LOG_TAG, "Forecast JSON String:" + forecastJsonStr);
            } catch (IOException e) {
                // Log.e("PlaceholderFragment", "Error ", e);
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
/**
 * By Direct Mathed:getWeatherDataFromJson wwanglai
 * https://gist.github.com/profsergiocosta/b94601ca3fa95a2343a8
 */
            try {
                return getWeatherDataFromJson(forecastJsonStr, numDays);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

/**
 * By Inner Class WeatherDataParser wwanglai
 * https://gist.github.com/fujianto/d96982ac3d4c4c6c674f
 */
            //Get data and parse from JSON, Return it to onPostExecute
/*
            try{
                WeatherDataParser dataParser =  new WeatherDataParser();
                return dataParser.getWeatherDataFromJson(forecastJsonStr, numDays);
            } catch (JSONException e){
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
*/
            return null;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p/>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param result The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {

                switch (forecastAdapterType) {
                    case ARRARY_ADAPTER_TYPE:

                        mForecastArrayAdapter.clear();                     //Clear all previous list item from adapter
                        for (String dayForecastStr : result) {
                            // Which is the most suitable one：addAll or add?
                            // Note that if you're targeting Honeycomb devices in above, You can use an addAll method here!!!
                            // so you don't have to add them one by one. You can just add it once and update the Listview once.
                            // mForecastAdapter.addAll(dayForecastStr);  //Add JSON data to ListView
                            mForecastArrayAdapter.add(dayForecastStr);
                        }
                        break;
                    case SIMPLE_CURROR_ADAPTER_TYPE:
                        break;
                    case CURROR_ADAPTER_TYPE:
                        break;
                    default:
                        mForecastArrayAdapter.clear();                     //Clear all previous list item from adapter
                        for (String dayForecastStr : result) {
                            // Which is the most suitable one：addAll or add?
                            // Note that if you're targeting Honeycomb devices in above, You can use an addAll method here!!!
                            // so you don't have to add them one by one. You can just add it once and update the Listview once.
                            // mForecastAdapter.addAll(dayForecastStr);  //Add JSON data to ListView
                            mForecastArrayAdapter.add(dayForecastStr);
                        }
                }
                // New data is from the server. Hooray!
                Log.e("From app", "!!!!!!!!!!!!!Test-2!!!!!!!!!!!!!!!!!!!!");
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String locationSetting = Utility.getPreferredLocation(getActivity());
        Uri weatherForLocationUri = WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());

        Log.d(LOG_TAG, "Uri:" + weatherForLocationUri);

        getLoaderManager().initLoader(FORECAST_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // This is called when a new Loader needs to be created.  This
        // fragment only uses one loader, so we don't care about checking the id.

        // To only show current and future dates, filter the query to return weather only for
        // dates after or including today.

        // Sort order:  Ascending, by date.

/*
        String sortOrder = WeatherContract.WeatherEntry.COLUMN_DATE + " ASC";

        String locationSetting = Utility.getPreferredLocation(getActivity());
        Uri weatherForLocationUri = WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());

        Log.d(LOG_TAG, "Uri:" + weatherForLocationUri);

        return new CursorLoader(getActivity(),
                weatherForLocationUri,
                FORECAST_COLUMNS,
                null,
                null,
                sortOrder);
*/
        // Uri uri = AppContentProvider.CONTENT_URI_MARTIAN;
        Uri uri = WeatherContract.WeatherEntry.CONTENT_URI;

        Log.d(LOG_TAG, "Uri:" + uri);

        // String[] projection = { "_id", "martian_tripod_id", "name", "observation" };
        String[] projection = {
                WeatherContract.WeatherEntry.COLUMN_DATE,
                WeatherContract.WeatherEntry.COLUMN_SHORT_DESC,
                WeatherContract.WeatherEntry.COLUMN_MAX_TEMP,
                WeatherContract.WeatherEntry.COLUMN_MIN_TEMP
        };

        String selection = null;
        String[] selectionArgs = null;
        // String sortOrder = "_id ASC";
        String sortOrder = WeatherContract.WeatherEntry.COLUMN_DATE + " ASC";

        return new CursorLoader(getActivity(), uri,
                FORECAST_COLUMNS, selection, selectionArgs, sortOrder);
//        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // mForecastSimCurAdapter.swapCursor(data);

        if (data == null) {
            String logtag = this.getClass().getSimpleName();
            Log.e(logtag, "onLoadFinished cursor is null");
        }
        else if (mAdapter != null) {

            Cursor oldCursor = mAdapter.swapCursor(data);

            if (oldCursor != null) {
                oldCursor.close();
            }
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}