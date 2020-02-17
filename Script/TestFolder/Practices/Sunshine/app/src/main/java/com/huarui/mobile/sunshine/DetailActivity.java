// https://gist.github.com/brite-joseph/722764b6bbb52432d3bd14c2f8d687f1
package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import android.widget.Toolbar;

import static android.view.MenuItem.SHOW_AS_ACTION_ALWAYS;

public class DetailActivity extends Activity {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();

    ForecastDetailFragment fragment;

    private ShareActionProvider mShareActionProvider;

    // Group ID
    private static final int MENU_GROUP_1 = Menu.FIRST * 10000;
    private static final int MENU_GROUP_2 = Menu.FIRST * 20000;
    private static final int MENU_GROUP_3 = Menu.FIRST * 30000;

    private static final int MENU_ITEM_TOP_1 = Menu.FIRST * 100 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_2 = Menu.FIRST * 200 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_3 = Menu.FIRST * 300 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_4 = Menu.FIRST * 400 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_5 = Menu.FIRST * 500 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_6 = Menu.FIRST * 600 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_7 = Menu.FIRST * 700 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_8 = Menu.FIRST * 800 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_9 = Menu.FIRST * 900 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_10 = Menu.FIRST * 1000 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_11 = Menu.FIRST * 1100 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_12 = Menu.FIRST * 1200 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_13 = Menu.FIRST * 1300 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_14 = Menu.FIRST * 1400 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_15 = Menu.FIRST * 1500 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_16 = Menu.FIRST * 1600 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_17 = Menu.FIRST * 1700 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_18 = Menu.FIRST * 1800 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_19 = Menu.FIRST * 1900 + MENU_GROUP_1;
    private static final int MENU_ITEM_TOP_20 = Menu.FIRST * 2000 + MENU_GROUP_1;

    private static final int MENU_ITEM_10 = Menu.FIRST + MENU_ITEM_TOP_1;

    private static final int MENU_ITEM_20 = Menu.FIRST + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_30 = Menu.FIRST + 1 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_40 = Menu.FIRST + 2 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_50 = Menu.FIRST + 3 + MENU_ITEM_TOP_2;

    private static final int MENU_ITEM_60 = Menu.FIRST + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_70 = Menu.FIRST + 1 + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_80 = Menu.FIRST + 2 + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_90 = Menu.FIRST + 3 + MENU_ITEM_TOP_3;

    private static final int MENU_ITEM_100 = Menu.FIRST + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_110 = Menu.FIRST + 1 + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_120 = Menu.FIRST + 2 + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_130 = Menu.FIRST + 3 + MENU_ITEM_TOP_4;

    private static final int MENU_ITEM_140 = Menu.FIRST + MENU_ITEM_TOP_8;
    private static final int MENU_ITEM_150 = Menu.FIRST + MENU_ITEM_TOP_9;
    private static final int MENU_ITEM_160 = Menu.FIRST + MENU_ITEM_TOP_10;
    private static final int MENU_ITEM_170 = Menu.FIRST + MENU_ITEM_TOP_11;
    private static final int MENU_ITEM_180 = Menu.FIRST + MENU_ITEM_TOP_12;
    private static final int MENU_ITEM_190 = Menu.FIRST + MENU_ITEM_TOP_13;
    private static final int MENU_ITEM_200 = Menu.FIRST + MENU_ITEM_TOP_14;
    private static final int MENU_ITEM_210 = Menu.FIRST + MENU_ITEM_TOP_15;
    private static final int MENU_ITEM_220 = Menu.FIRST + MENU_ITEM_TOP_16;
    private static final int MENU_ITEM_230 = Menu.FIRST + MENU_ITEM_TOP_17;
    private static final int MENU_ITEM_240 = Menu.FIRST + MENU_ITEM_TOP_18;

    private Toolbar thisToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunshine_detail);

        // Android开发中获取屏幕宽高的几种方法
        // http://blog.csdn.net/biaobiao1217/article/details/46876995

        // Display display = getWindowManager().getDefaultDisplay();

        DisplayMetrics dm = new DisplayMetrics();
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        if (dm.heightPixels > dm.widthPixels) {
            // ForecastDetailFragment fragment2 = new ForecastDetailFragment();
            fragment = new ForecastDetailFragment();
            getFragmentManager().beginTransaction().replace(R.id.detail_sunshine_layout_inner, fragment).commit();
        } else {
            ForecastFragment fragment1 = new ForecastFragment();
            getFragmentManager().beginTransaction().replace(R.id.detail_sunshine_layout_inner, fragment1).commit();
        }

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mainCustomToolbar);

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        // The order position of the item
        int menuItemOrder = Menu.NONE;

        MenuItem menuitemShare = menu.add(MENU_GROUP_1, MENU_ITEM_10, menuItemOrder, R.string.action_share);
        menuitemShare.setIcon(android.R.drawable.ic_menu_crop).setShowAsAction(SHOW_AS_ACTION_ALWAYS);

        // Get the provider and hold onto it to set/change the share intent.
        // ShareActionProvider mShareActionProvider = (ShareActionProvider) menuitemShare.getActionProvider();
        mShareActionProvider = (ShareActionProvider) menuitemShare.getActionProvider();

/*
        Intent intent = fragment.getShareForecastIntent();
        if (mShareActionProvider != null){
            mShareActionProvider.setShareIntent(intent);
        } else {
            Log.d(LOG_TAG,"Share Action Privider is null?");
        }
*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cloud_settings) {
            startActivity(new Intent(this,CloudSettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onOptionsItemSelected(Toolbar toolbar, MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case MENU_ITEM_10:

                // startActivity(new Intent(this, MainTestActivity.class));

                Intent intent = fragment.getShareForecastIntent();
                setShareIntent(intent);
                startActivity(intent);

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true;
            case MENU_ITEM_20:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainSunshineActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + "，Name：" + MainSunshineActivity.class.getSimpleName() + ".");
                return true; // break;
            case MENU_ITEM_30:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, SunshineSettingsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_40:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, CloudSettingsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;

            case MENU_ITEM_50:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, SettingsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;

            case MENU_ITEM_60:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, PreferenceWithHeaders.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_70:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, ToolbarSettingsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;

            case MENU_ITEM_80:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView1Activity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_90:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView2Activity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_100:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView2Activity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_110:
                /*
                Intent intent = new Intent(this, MainTestActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_120:
                /*
                Intent intent = new Intent(this, MainTestActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_130:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_140:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_150:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_160:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_170:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_180:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainFragmentActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_190:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_200:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_210:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_220:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_230:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_240:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
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
    // 调用来更新share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        } else {
            Log.d(LOG_TAG,"Share Action Privider is null?");
        }
    }
    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
