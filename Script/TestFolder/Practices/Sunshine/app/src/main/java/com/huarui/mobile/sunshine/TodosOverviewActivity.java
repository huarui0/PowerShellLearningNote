// http://www.vogella.com/tutorials/AndroidSQLite/article.html
package com.huarui.mobile.sunshine;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import com.huarui.mobile.sunshine.data.TodoContentProvider;
import com.huarui.mobile.sunshine.data.TodoTable;
import com.huarui.mobile.sunshine.utils.MenuManager;

/**
 * Created by wanglai on 1/4/2017.
 */

public class TodosOverviewActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;
    private static final int DELETE_ID = Menu.FIRST + 1;
    // private Cursor cursor;
    private SimpleCursorAdapter adapter;

    private Toolbar thisToolbar;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_todo_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.todoCustomToolbar);
        thisToolbar = myToolbar;
        Menu myMenu = myToolbar.getMenu();

        onCreateOptionsMenu(myMenu);
        // Set an OnMenuItemClickListener to handle menu item clicks
        myToolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener()

                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Handle the menu item

                        onOptionsItemSelected(thisToolbar, item);
                        return true;

                    }
                }

        );

        this.getListView().setDividerHeight(2);
        fillData();
        registerForContextMenu(getListView());
    }

    // create the menu based on the XML defintion
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // The order position of the item
        int menuItemOrder = Menu.NONE;

        menu.add(MenuManager.MENU_GROUP_1, MenuManager.MENU_ITEM_10, menuItemOrder, "Insert")
                .setIcon(android.R.drawable.ic_menu_crop);

        return true;
    }

    // Reaction to the menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch (item.getItemId()) {
            case R.id.insert:
                createTodo();
                return true;
        }
        return super.onOptionsItemSelected(item);
        */
        return onOptionsItemSelected(thisToolbar, item);
    }

    public boolean onOptionsItemSelected(Toolbar toolbar, MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case MenuManager.MENU_ITEM_10:
                // startActivity(new Intent(this, MainSunshineActivity.class));
                createTodo();
                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true;
            default:
                /*
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, CloudSettingsActivity.class));
                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");

                return false;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
                        .getMenuInfo();
                Uri uri = Uri.parse(TodoContentProvider.CONTENT_URI + "/"
                        + info.id);
                getContentResolver().delete(uri, null, null);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void createTodo() {
        Intent i = new Intent(this, TodoDetailActivity.class);
        startActivity(i);
    }

    // Opens the second activity if an entry is clicked
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, TodoDetailActivity.class);
        Uri todoUri = Uri.parse(TodoContentProvider.CONTENT_URI + "/" + id);
        i.putExtra(TodoContentProvider.CONTENT_ITEM_TYPE, todoUri);

        startActivity(i);
    }

    private void fillData() {

        // Fields from the database (projection)
        // Must include the _id column for the adapter to work
        String[] from = new String[]{TodoTable.COLUMN_SUMMARY};
        // Fields on the UI to which we map
        int[] to = new int[]{R.id.label};

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_todo_row, null, from,
                to, 0);

        setListAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }

    // creates a new loader after the initLoader () call
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {TodoTable.COLUMN_ID, TodoTable.COLUMN_SUMMARY};
        CursorLoader cursorLoader = new CursorLoader(this,
                TodoContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // data is not available anymore, delete reference
        adapter.swapCursor(null);
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
