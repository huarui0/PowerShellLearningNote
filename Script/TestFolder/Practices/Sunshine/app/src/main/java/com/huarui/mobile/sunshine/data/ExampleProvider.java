package com.huarui.mobile.sunshine.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by wanglai on 12/26/2016.
 */

public class ExampleProvider extends ContentProvider {
    private final String LOG_TAG = ExampleProvider.class.getSimpleName();

    static final int WEATHER = 100;
    static final int WEATHER_WITH_LOCATION = 101;
    static final int WEATHER_WITH_LOCATION_AND_DATE = 102;
    static final int LOCATION = 300;


    private static final int PEOPLE = 1;
    private static final int PEOPLE_ID = 2;
    private static final int PEOPLE_PHONES = 3;
    private static final int PEOPLE_PHONES_ID = 4;
    private static final int PEOPLE_CONTACTMETHODS = 7;
    private static final int PEOPLE_CONTACTMETHODS_ID = 8;

    private static final int DELETED_PEOPLE = 20;

    private static final int PHONES = 9;
    private static final int PHONES_ID = 10;
    private static final int PHONES_FILTER = 14;

    private static final int CONTACTMETHODS = 18;
    private static final int CONTACTMETHODS_ID = 19;

    private static final int CALLS = 11;
    private static final int CALLS_ID = 12;
    private static final int CALLS_FILTER = 15;

    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    // static final String authority = "content://com.marakana.android.lifecycle.providerdemo";
    private static final String authority = WeatherContract.CONTENT_AUTHORITY;
    public static final Uri CONTENT_URI = Uri.parse(authority);

    static {

        // For each type of URI you want to add, create a corresponding code.
        sUriMatcher.addURI(authority, WeatherContract.PATH_WEATHER, WEATHER);
        sUriMatcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
        sUriMatcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/#", WEATHER_WITH_LOCATION_AND_DATE);

        sUriMatcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);


        sUriMatcher.addURI(authority, "people", PEOPLE);
        sUriMatcher.addURI(authority, "people/#", PEOPLE_ID);
        sUriMatcher.addURI(authority, "people/#/phones", PEOPLE_PHONES);
        sUriMatcher.addURI(authority, "people/#/phones/#", PEOPLE_PHONES_ID);
        sUriMatcher.addURI(authority, "people/#/contact_methods", PEOPLE_CONTACTMETHODS);
        sUriMatcher.addURI(authority, "people/#/contact_methods/#", PEOPLE_CONTACTMETHODS_ID);
        sUriMatcher.addURI(authority, "deleted_people", DELETED_PEOPLE);
        sUriMatcher.addURI(authority, "phones", PHONES);
        sUriMatcher.addURI(authority, "phones/filter/*", PHONES_FILTER);
        sUriMatcher.addURI(authority, "phones/#", PHONES_ID);
        sUriMatcher.addURI(authority, "contact_methods", CONTACTMETHODS);
        sUriMatcher.addURI(authority, "contact_methods/#", CONTACTMETHODS_ID);
        sUriMatcher.addURI("call_log", "calls", CALLS);
        sUriMatcher.addURI("call_log", "calls/filter/*", CALLS_FILTER);
        sUriMatcher.addURI("call_log", "calls/#", CALLS_ID);

        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         */
        sUriMatcher.addURI("com.example.app.provider", "table3", 1);

        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         */
        sUriMatcher.addURI("com.example.app.provider", "table3/#", 2);
    }

    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        return true;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType returning: " + uri);

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            // Student: Uncomment and fill out these two cases
            case WEATHER_WITH_LOCATION_AND_DATE:
                return WeatherContract.WeatherEntry.CONTENT_ITEM_TYPE;
            case WEATHER_WITH_LOCATION:
                return WeatherContract.WeatherEntry.CONTENT_TYPE;
            case WEATHER:
                return WeatherContract.WeatherEntry.CONTENT_TYPE;
            case LOCATION:
                return WeatherContract.LocationEntry.CONTENT_TYPE;


            case PEOPLE:
                return "vnd.android.cursor.dir/person";


            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }
}
