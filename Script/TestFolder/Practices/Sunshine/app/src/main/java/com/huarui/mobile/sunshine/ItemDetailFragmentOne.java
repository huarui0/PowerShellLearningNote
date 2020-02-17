// http://www.appstoremarketresearch.com/articles/android-tutorial-loader-simple-cursor-adapter/
// https://github.com/danielgsalas/Android-WarOfTheWorlds

package com.huarui.mobile.sunshine;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.huarui.mobile.sunshine.data.MartianLoaderCallbacks;

/**
 * Created by wanglai on 2/14/2017.
 */

public class ItemDetailFragmentOne extends ListFragment {
    private SimpleCursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeContentLoader();
    }

    /**
     * initializeContentLoader
     */
    private void initializeContentLoader() {

        // array of database column names
        String[] columns = new String[] {
                "_id", "name", "observation" };

        // array of views to display database values
        int[] viewIds = new int[] {
                R.id.text1, R.id.text2, R.id.text3 };

        // CursorAdapter to load data from the Cursor into the ListView
        mAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.fragment_one_list_item, null, columns, viewIds, 0);

        // tie the CursorAdapter to the ListView
        setListAdapter(mAdapter);

        // LoaderCallbacks to create the CursorLoader and pass Cursors to the CursorAdapter
        MartianLoaderCallbacks callbacks = new MartianLoaderCallbacks(getActivity());

        // tie the CursorAdapter to the LoaderCallbacks
        callbacks.setCursorAdapter(mAdapter);

        // create a unique identifier for the Loader
        int loaderId = callbacks.getClass().getSimpleName().hashCode();

        // tell the LoaderManager to start the load
        getLoaderManager().initLoader(loaderId, null, callbacks);
    }
}
