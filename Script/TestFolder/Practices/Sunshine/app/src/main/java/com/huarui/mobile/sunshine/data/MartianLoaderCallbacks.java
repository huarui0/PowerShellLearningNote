// http://www.appstoremarketresearch.com/articles/android-tutorial-loader-simple-cursor-adapter/

package com.huarui.mobile.sunshine.data;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.CursorAdapter;

/**
 * Created by wanglai on 2/13/2017.
 */

public class MartianLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context context;
    private CursorAdapter cursorAdapter;

    public MartianLoaderCallbacks(Context context) {
        this.context = context;
    }

    public void setCursorAdapter(CursorAdapter cursorAdapter) {
        this.cursorAdapter = cursorAdapter;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri uri = AppContentProvider.CONTENT_URI_MARTIAN;
        String[] projection = {"_id", "martian_tripod_id", "name", "observation"};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = "_id ASC";

        return new CursorLoader(this.context, uri,
                projection, selection, selectionArgs, sortOrder);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data == null) {
            String logtag = this.getClass().getSimpleName();
            Log.e(logtag, "onLoadFinished cursor is null");
        }
        else if (cursorAdapter != null) {

            //if (cursorAdapter.getCursor() != null) {
            //    cursorAdapter.getCursor().close();
            //}

            Cursor oldCursor = cursorAdapter.swapCursor(data);

            if (oldCursor != null) {
                oldCursor.close();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (cursorAdapter != null) {

            Cursor oldCursor = cursorAdapter.swapCursor(null);

            if (oldCursor != null) {
                oldCursor.close();
            }
        }
    }
}
