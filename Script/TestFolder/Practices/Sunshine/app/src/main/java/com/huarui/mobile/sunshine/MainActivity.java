package com.huarui.mobile.sunshine;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by wanglai on 11/22/2016.
 */

public class MainActivity extends Activity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

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
    private static final int MENU_ITEM_21 = Menu.FIRST + 1 + MENU_ITEM_TOP_2;

    private static final int MENU_ITEM_30 = Menu.FIRST + 10 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_40 = Menu.FIRST + 20 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_50 = Menu.FIRST + 30 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_60 = Menu.FIRST + 40 + MENU_ITEM_TOP_2;
    private static final int MENU_ITEM_70 = Menu.FIRST + 50 + MENU_ITEM_TOP_2;

    private static final int MENU_ITEM_80 = Menu.FIRST + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_90 = Menu.FIRST + 10 + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_100 = Menu.FIRST + 20 + MENU_ITEM_TOP_3;
    private static final int MENU_ITEM_110 = Menu.FIRST + 30 + MENU_ITEM_TOP_3;

    private static final int MENU_ITEM_120 = Menu.FIRST + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_130 = Menu.FIRST + 10 + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_140 = Menu.FIRST + 20 + MENU_ITEM_TOP_4;
    private static final int MENU_ITEM_150 = Menu.FIRST + 30 + MENU_ITEM_TOP_4;

    private static final int MENU_ITEM_160 = Menu.FIRST + MENU_ITEM_TOP_5;
    private static final int MENU_ITEM_170 = Menu.FIRST + 10 + MENU_ITEM_TOP_5;
    private static final int MENU_ITEM_180 = Menu.FIRST + 20 + MENU_ITEM_TOP_5;
    private static final int MENU_ITEM_190 = Menu.FIRST + 30 + MENU_ITEM_TOP_5;
    private static final int MENU_ITEM_200 = Menu.FIRST + 40 + MENU_ITEM_TOP_5;
    private static final int MENU_ITEM_210 = Menu.FIRST + 50 + MENU_ITEM_TOP_5;

    private static final int MENU_ITEM_220 = Menu.FIRST + 10 + MENU_ITEM_TOP_6;
    private static final int MENU_ITEM_230 = Menu.FIRST + 20 + MENU_ITEM_TOP_6;
    private static final int MENU_ITEM_240 = Menu.FIRST + 30 + MENU_ITEM_TOP_6;
    private static final int MENU_ITEM_250 = Menu.FIRST + 40 + MENU_ITEM_TOP_6;
    private static final int MENU_ITEM_260 = Menu.FIRST + 50 + MENU_ITEM_TOP_6;

    private static final int MENU_ITEM_1600 = Menu.FIRST + MENU_ITEM_TOP_10;
    private static final int MENU_ITEM_1700 = Menu.FIRST + MENU_ITEM_TOP_11;
    private static final int MENU_ITEM_1800 = Menu.FIRST + MENU_ITEM_TOP_12;
    private static final int MENU_ITEM_1900 = Menu.FIRST + MENU_ITEM_TOP_13;
    private static final int MENU_ITEM_2000 = Menu.FIRST + MENU_ITEM_TOP_14;
    private static final int MENU_ITEM_2100 = Menu.FIRST + MENU_ITEM_TOP_15;
    private static final int MENU_ITEM_2200 = Menu.FIRST + MENU_ITEM_TOP_16;
    private static final int MENU_ITEM_2300 = Menu.FIRST + MENU_ITEM_TOP_17;
    private static final int MENU_ITEM_2400 = Menu.FIRST + MENU_ITEM_TOP_18;

    private Toolbar thisToolbar;

    private Button btn1, btn2;

    ListView lv;
    Context context;

    ArrayList prgmName;
    public static int[] prgmImages = {R.drawable.ic_stat_music, R.drawable.heart, R.drawable.heart, R.drawable.heart, R.drawable.heart, R.drawable.heart, R.drawable.heart, R.drawable.heart, R.drawable.heart};
    public static String[] prgmNameList = {"Let Us C", "c++", "JAVA", "Jsp", "Microsoft .Net", "Android", "PHP", "Jquery", "JavaScript"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.mainCustomToolbar);

// myToolbar.setNavigationIcon(materialMenu);
        myToolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标

        myToolbar.setNavigationOnClickListener(new View.OnClickListener()

                                               {
                                                   @Override
                                                   public void onClick(View v) {
                                                       finish();
                                                       // random state
                                                       // actionBarMenuState = generateState(actionBarMenuState);
                                                       // getMaterialMenu(toolbar).animateIconState(intToState(actionBarMenuState));
                                                   }
                                               }

        );

        myToolbar.setLogo(

                getDrawable(R.drawable.ic_action_customtoolbar)

        );
        myToolbar.setLogoDescription(

                getResources()

                        .

                                getString(R.string.app_name)

        );

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
        // TODO Auto-generated method stub

        // The order position of the item
        int menuItemOrder = Menu.NONE;

        menu.add(MENU_GROUP_1, MENU_ITEM_10, menuItemOrder, "Refresh")
                .setIcon(android.R.drawable.ic_menu_crop);

        menu.add(MENU_GROUP_1, MENU_ITEM_20, menuItemOrder, "SunshineApp")
                .setIcon(android.R.drawable.ic_menu_crop);

        menu.add(MENU_GROUP_1, MENU_ITEM_21, menuItemOrder, "SunshineAppTest")
                .setIcon(android.R.drawable.ic_menu_crop);

        // 一个menu可以包括多个子菜单
        SubMenu subMenu_Settings = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_2, menuItemOrder, "系统设置(Setting)");
        // 子菜单可以包括多个菜单项
        MenuItem menuitem1 = subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_30, menuItemOrder, "SunshineSettings");

        subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_40, menuItemOrder, "CloudSettings");
        //  .setIcon(R.drawable.icon); more expand menu 不支持icon, setIcon不会报错，但运行时还是看不到icon的
        subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_50, menuItemOrder, "Settings(SettingsActivity)");
        subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_60, menuItemOrder, "PreferenceWithHeaders").setIcon(android.R.drawable.ic_menu_preferences);
        subMenu_Settings.add(MENU_GROUP_1, MENU_ITEM_70, menuItemOrder, "ToolbarSettingsActivity").setIcon(android.R.drawable.ic_menu_preferences);

        // 子菜单项不支持显示图标，这样做是没意义的，尽管不会报错！
        menuitem1.setIcon(R.drawable.ic_settings_applications);
        //但是子菜单本身是支持图标的
        subMenu_Settings.setIcon(R.drawable.ic_settings_display);
        // 添加子菜单项栏目的标题图标，
        subMenu_Settings.setHeaderIcon(R.drawable.ic_settings_applications);

        // menu.addSubMenu("又一个子菜单");
        SubMenu subMenu_ListViewTest = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_3, menuItemOrder, "ListView测试");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_80, menuItemOrder, "ListView测试1");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_90, menuItemOrder, "ListView测试2");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_100, menuItemOrder, "ListView测试3（Json）");

        subMenu_ListViewTest.add(MENU_GROUP_1, MENU_ITEM_110, menuItemOrder, "ListView测试4");

        // menu.addSubMenu("又一个子菜单");
        SubMenu subMenu_RecyclerViewTest = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_4, menuItemOrder, "RecyclerView 测试");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_120, menuItemOrder, "RecyclerView 测试1");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_130, menuItemOrder, "RecyclerView 测试2");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_140, menuItemOrder, "RecyclerView 测试3");

        subMenu_RecyclerViewTest.add(MENU_GROUP_1, MENU_ITEM_150, menuItemOrder, "RecyclerView 测试4");

        // menu.addSubMenu("又一个子菜单");
        SubMenu subMenu_ContentProviderTest = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_5, menuItemOrder, "ContentProvider 测试");
        subMenu_ContentProviderTest.add(MENU_GROUP_1, MENU_ITEM_160, menuItemOrder, "StudentsProvider 测试1");
        subMenu_ContentProviderTest.add(MENU_GROUP_1, MENU_ITEM_170, menuItemOrder, "ImagesProvider 测试2");
        subMenu_ContentProviderTest.add(MENU_GROUP_1, MENU_ITEM_180, menuItemOrder, "CommentsProvider 测试3");
        subMenu_ContentProviderTest.add(MENU_GROUP_1, MENU_ITEM_190, menuItemOrder, "ContactsProvider 测试4");
        subMenu_ContentProviderTest.add(MENU_GROUP_1, MENU_ITEM_200, menuItemOrder, "PersonsProvider 测试5");
        subMenu_ContentProviderTest.add(MENU_GROUP_1, MENU_ITEM_210, menuItemOrder, "TodosProvider 测试5");

        // menu.addSubMenu("又一个子菜单");
        SubMenu subMenu_SimpleCursorAdapterTest = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_6, menuItemOrder, "SimpleCursorAdapter 测试");
        subMenu_SimpleCursorAdapterTest.add(MENU_GROUP_1, MENU_ITEM_220, menuItemOrder, "LanguageListActivity 测试1");
        subMenu_SimpleCursorAdapterTest.add(MENU_GROUP_1, MENU_ITEM_230, menuItemOrder, "MainCursorLoaderListActivity 测试2");
        subMenu_SimpleCursorAdapterTest.add(MENU_GROUP_1, MENU_ITEM_240, menuItemOrder, "MainCountryActivity 测试3");

        // menu.addSubMenu("又一个子菜单");
        SubMenu subMenu_CustomGridViewTest = menu.addSubMenu(MENU_GROUP_1, MENU_ITEM_TOP_7, menuItemOrder, "GridView 测试");
        subMenu_CustomGridViewTest.add(MENU_GROUP_1, MENU_ITEM_250, menuItemOrder, "Custom GridView 测试1");

        menu.add(MENU_GROUP_1, MENU_ITEM_1600, menuItemOrder, "部件功能 Test");

        // 2.通过资源指定标题
        // menu.add(R.string.action_favorite);
        menu.add(MENU_GROUP_1, MENU_ITEM_1700, menuItemOrder, R.string.action_favorite);
        // 3.显示指定菜单项的组号、ID、排序号、标题
        menu.add(
                MENU_GROUP_1,            //组号
                MENU_ITEM_1800, //唯一的ID号
                menuItemOrder, //排序号
                "Fragment与Activity通讯测试"); //标题

        menu.add(MENU_GROUP_1, MENU_ITEM_1900, MENU_ITEM_1900, "弹出窗口(PopActivity) 测试");

        menu.add(MENU_GROUP_1, MENU_ITEM_2000, MENU_ITEM_2000, R.string.action_map);

        menu.add(MENU_GROUP_1, MENU_ITEM_2100, MENU_ITEM_2100, "备用2 测试");

        menu.add(MENU_GROUP_1, MENU_ITEM_2200, MENU_ITEM_2200, "备用3 测试");

        // 如果希望显示菜单，请返回true
        //return super.onCreateOptionsMenu(menu);
        return true; //true表示要显示menu; false表示不显示menu

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);

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
            case MENU_ITEM_10:

                startActivity(new Intent(this, MainStudentsActivity.class));

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
            case MENU_ITEM_21:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainSunshineActivityTest.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + "，Name：" + MainSunshineActivityTest.class.getSimpleName() + ".");
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
                startActivity(new Intent(this, MainSunshineActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_110:
                /*
                Intent intent = new Intent(this, MainPersonsActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainPersonsActivity.class));

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
                startActivity(new Intent(this, MainStudentsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_170:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainImagesActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_180:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainCommentsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_190:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainContactsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_200:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainPersonsActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_210:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, TodosOverviewActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;

            case MENU_ITEM_220:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, LanguageListActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;

            case MENU_ITEM_230:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, LanguageListActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_240:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, LanguageListActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;

            case MENU_ITEM_250:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, FoodSQLiteDemoActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_1600:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_1700:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_1800:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainFragmentActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_1900:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_2000:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, MainTestActivity.class));

                openPreferredLocationInMap();

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_2100:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_2200:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_2300:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_" + id + " Selected! Code:" + id + ".");
                return true; // break;
            case MENU_ITEM_2400:
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
}
