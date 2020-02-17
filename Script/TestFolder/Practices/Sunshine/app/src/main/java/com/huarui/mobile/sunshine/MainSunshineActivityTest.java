package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.huarui.mobile.sunshine.ForecastFragmentTest.OnRefreshItemListener;

/**
 * Created by wanglai on 11/28/2016.
 */

public class MainSunshineActivityTest extends Activity implements OnRefreshItemListener {

    /**
     * Called when the activity is first created.
     */

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

    private static final int MENU_ITEM_1 = Menu.FIRST + MENU_ITEM_TOP_1;

    private static final int MENU_ITEM_2 = Menu.FIRST + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_3 = Menu.FIRST + 1 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_4 = Menu.FIRST + 2 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_5 = Menu.FIRST + 3 + MENU_ITEM_TOP_2;

    private static final int MENU_ITEM_6 = Menu.FIRST + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_7 = Menu.FIRST + 1 + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_8 = Menu.FIRST + 2 + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_9 = Menu.FIRST + 3 + MENU_ITEM_TOP_3;

    private static final int MENU_ITEM_10 = Menu.FIRST + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_11 = Menu.FIRST + 1 + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_12 = Menu.FIRST + 2 + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_13 = Menu.FIRST + 3 + MENU_ITEM_TOP_4;

    private static final int MENU_ITEM_14 = Menu.FIRST + MENU_ITEM_TOP_8;
    private static final int MENU_ITEM_15 = Menu.FIRST + MENU_ITEM_TOP_9;
    private static final int MENU_ITEM_16 = Menu.FIRST + MENU_ITEM_TOP_10;
    private static final int MENU_ITEM_17 = Menu.FIRST + MENU_ITEM_TOP_11;
    private static final int MENU_ITEM_18 = Menu.FIRST + MENU_ITEM_TOP_12;
    private static final int MENU_ITEM_19 = Menu.FIRST + MENU_ITEM_TOP_13;
    private static final int MENU_ITEM_20 = Menu.FIRST + MENU_ITEM_TOP_14;
    private static final int MENU_ITEM_21 = Menu.FIRST + MENU_ITEM_TOP_15;
    private static final int MENU_ITEM_22 = Menu.FIRST + MENU_ITEM_TOP_16;
    private static final int MENU_ITEM_23 = Menu.FIRST + MENU_ITEM_TOP_17;
    private static final int MENU_ITEM_24 = Menu.FIRST + MENU_ITEM_TOP_18;

    private Toolbar thisToolbar;
    ForecastFragmentTest forecastFragment;

    private final String LOG_TAG = MainSunshineActivityTest.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunshine_main);

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

        // Android开发中获取屏幕宽高的几种方法
        // http://blog.csdn.net/biaobiao1217/article/details/46876995

        // Display display = getWindowManager().getDefaultDisplay();

        DisplayMetrics dm = new DisplayMetrics();
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        if (dm.heightPixels > dm.widthPixels) {
            ForecastFragmentTest fragment1 = new ForecastFragmentTest();

            forecastFragment = fragment1;

            getFragmentManager().beginTransaction().replace(R.id.main_sunshine_layout_inner, forecastFragment).commit();
        } else {
            ForecastDetailFragment fragment2 = new ForecastDetailFragment();
            getFragmentManager().beginTransaction().replace(R.id.main_sunshine_layout_inner, fragment2).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        // The order position of the item
        int menuItemOrder = Menu.NONE;

        menu.add(MENU_GROUP_1, MENU_ITEM_1, menuItemOrder, "Refresh")
                .setIcon(android.R.drawable.ic_menu_crop);

        menu.add(MENU_GROUP_1, MENU_ITEM_2, menuItemOrder, "SunshineApp")
                .setIcon(android.R.drawable.ic_menu_crop);

        // 一个menu可以包括多个子菜单
        SubMenu subMenu_Settings = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_2, menuItemOrder, "系统设置(Setting)");
        // 子菜单可以包括多个菜单项
        MenuItem menuitem1 = subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_3, MENU_ITEM_3, "Settings");

        subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_4, menuItemOrder, "SunshineSettings");
        //  .setIcon(R.drawable.icon); more expand menu 不支持icon, setIcon不会报错，但运行时还是看不到icon的
        subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_5, menuItemOrder, "CloudSettings").setIcon(android.R.drawable.ic_menu_preferences);

        // 子菜单项不支持显示图标，这样做是没意义的，尽管不会报错！

        menuitem1.setIcon(R.drawable.ic_settings_applications);
        //但是子菜单本身是支持图标的
        subMenu_Settings.setIcon(R.drawable.ic_settings_display);
        // 添加子菜单项栏目的标题图标，
        subMenu_Settings.setHeaderIcon(R.drawable.ic_settings_applications);

        // menu.addSubMenu("又一个子菜单");
        SubMenu subMenu_ListViewTest = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_3, menuItemOrder, "ListView测试");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_6, menuItemOrder, "ListView测试1");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_7, menuItemOrder, "ListView测试2");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_8, menuItemOrder, "ListView测试3（Json）");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_9, menuItemOrder, "ListView测试4");

        // menu.addSubMenu("又一个子菜单");
        SubMenu subMenu_RecyclerViewTest = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_4, menuItemOrder, "RecyclerView 测试");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_10, menuItemOrder, "RecyclerView 测试1");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_11, menuItemOrder, "RecyclerView 测试2");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_12, menuItemOrder, "RecyclerView 测试3");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_13, menuItemOrder, "RecyclerView 测试4");

        menu.add(MENU_GROUP_1, MENU_ITEM_14, menuItemOrder, "部件功能 Test");


        // 2.通过资源指定标题
        // menu.add(R.string.action_favorite);
        menu.add(MENU_GROUP_1, MENU_ITEM_15, menuItemOrder, R.string.action_favorite);
        // 3.显示指定菜单项的组号、ID、排序号、标题
        menu.add(
                MENU_GROUP_1,            //组号
                MENU_ITEM_16, //唯一的ID号
                MENU_ITEM_16, //排序号
                R.string.action_map); //标题

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_17, menuItemOrder, "弹出窗口 测试");

        // 如果希望显示菜单，请返回true
        //return super.onCreateOptionsMenu(menu);
        return true; //true表示要显示menu; false表示不显示menu

    }

    public boolean onOptionsItemSelected(Toolbar toolbar, MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case MENU_ITEM_1:
                forecastFragment.updateWeatherByActivity();

                // startActivity(new Intent(this, MainRecyclerView1Activity.class));

                displayToast("MENU_ITEM_1 Selected! Code:" + id + ".");
                return true;
            case MENU_ITEM_2:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainSunshineActivityTest.class));

                displayToast("MENU_ITEM_2 Selected! Code:" + id + "，Name：" + MainSunshineActivityTest.class.getSimpleName() + ".");
                return true; // break;
            case MENU_ITEM_3:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, SettingsActivity.class));

                displayToast("MENU_ITEM_3 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_4:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, SunshineSettingsActivity.class));

                displayToast("MENU_ITEM_4 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_5:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, CloudSettingsActivity.class));

                displayToast("MENU_ITEM_5 Selected! Code:" + id + ".");
                return true; // break;

            case MENU_ITEM_6:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView1Activity.class));

                displayToast("MENU_ITEM_6 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_7:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView2Activity.class));

                displayToast("MENU_ITEM_7 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_8:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView2Activity.class));

                displayToast("MENU_ITEM_8 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_9:
                /*
                Intent intent = new Intent(this, MainRecyclerView1Activity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView2Activity.class));

                displayToast("MENU_ITEM_9 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_10:
                /*
                Intent intent = new Intent(this, MainTestActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainListView2Activity.class));

                displayToast("MENU_ITEM_10 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_11:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_11 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_12:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_12 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_13:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_13 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_14:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_14 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_15:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_15 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_16:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, MainTestActivity.class));

                openPreferredLocationInMap();

                displayToast("MENU_ITEM_16 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_17:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_17 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_18:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_18 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_19:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_19 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_20:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_20 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_21:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_21 Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_22:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_22 Selected! Code:" + id + ".");
                return true; // break;
            default:
                /*
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, CloudSettingsActivity.class));
                displayToast("MENU_ITEM_(Code:" + id + ") Selected! Code:" + id + ".");

                return false;
        }

    }

    private void openPreferredLocationInMap() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String location = prefs.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        // Using the URI scheme for showing a location found on a map.  This super-handy
        // intent can is detailed in the "Common Intents" page of Android's developer site:
        // http://developer.android.com/guide/components/intents-common.html#Maps
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", location).build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getBaseContext().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + geoLocation.toString() + ", no receiving apps installed!");
        }
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public Fragment getVisibleFragment() {
        /*
        FragmentManager fragmentManager = this.getFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragment();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        */
        return null;
    }

    private void getFragment() {
        // TODO Auto-generated method stub
        // FragmentManager可以用来管理Activity中的fragment，在Android 3.0（即API 11）时引入，所
        // 以对于之前的版本，需要使用support library中的FragmentActivity，并且使用getSupportFragmentManager()方法
        FragmentManager fragmentManager = getFragmentManager();
        // FragmentTransaction 每一个事务都是同时要执行的一套变化，必须调用commit()才能生效
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        // 再添加之前先移除之前的fragment
        beginTransaction.remove(forecastFragment);
        // 在beginTransaction事务中加入add动作
        beginTransaction.add(R.id.main_sunshine_layout_inner, forecastFragment);
        // 如果添加这一行代码，那么用户按下back时会返回前一个fragment状态
        beginTransaction.addToBackStack(null);
        // 执行事务
        beginTransaction.commit();
    }

    @Override
    public void OnRefreshItemListener(FetchWeatherTask weatherTask) {

    }
}
