package com.huarui.mobile.sunshine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.huarui.mobile.sunshine.Interface.DatabaseConstants;

/**
 * Created by wanglai on 2/7/2017.
 */


public class SQLiteHelper extends SQLiteOpenHelper implements DatabaseConstants {

    public static final int DB_VERSION = 2;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_LANG + "(" + COL_LANG_ID
                + " INTEGER PRIMARY KEY NOT NULL, " + " " + COL_LANG_NAME
                + " VARCHAR(50) NOT NULL);");
        insertLanguage(db, "Java");
        insertLanguage(db, "Perl");
        insertLanguage(db, "Python");
        insertLanguage(db, "Ruby");
        insertLanguage(db, "Scala");
        insertLanguage(db, "C");
        insertLanguage(db, "C++");
    }

    private static void insertLanguage(SQLiteDatabase db, String language) {
        db.execSQL("INSERT INTO " + TABLE_LANG + " (" + COL_LANG_NAME
                + ") VALUES ('" + language + "');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}