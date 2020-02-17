package com.huarui.mobile.sunshine;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class MainListView1Activity extends Activity {

    private static final int MENU_ITEM_1 = Menu.FIRST;
    private static final int MENU_ITEM_2 = Menu.FIRST + 1;
    private static final int MENU_ITEM_3 = Menu.FIRST + 2;
    private static final int MENU_ITEM_4 = Menu.FIRST + 3;
    private static final int MENU_ITEM_5 = Menu.FIRST + 4;
    private static final int MENU_ITEM_6 = Menu.FIRST + 5;
    private static final int MENU_ITEM_7 = Menu.FIRST + 6;
    private static final int MENU_ITEM_8 = Menu.FIRST + 7;
    private static final int MENU_ITEM_9 = Menu.FIRST + 8;
    private static final int MENU_ITEM_10 = Menu.FIRST + 9;
    private static final int MENU_ITEM_11 = Menu.FIRST + 10;

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

        setContentView(R.layout.activity_listview1_main);

        context = this;

        lv = (ListView) findViewById(R.id.listView_test);
        lv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));


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

        // Group ID
        int group1_Id = 0;
        int group2_Id = 1;

        // The order position of the item
        int menuItemOrder = Menu.NONE;

        menu.add(group1_Id, MENU_ITEM_1, menuItemOrder, "Refresh")
                .setIcon(android.R.drawable.ic_menu_crop);

        // 一个menu可以包括多个子菜单
        SubMenu subMenu = menu.addSubMenu(group1_Id, MENU_ITEM_2, menuItemOrder, "系统设置(Setting)");
        // 子菜单可以包括多个菜单项
        MenuItem menuitem1 = subMenu.add(group1_Id, MENU_ITEM_3, MENU_ITEM_3, "设置");

        subMenu.add(group1_Id, MENU_ITEM_4, menuItemOrder, "CloudSetting");
        //  .setIcon(R.drawable.icon); more expand menu 不支持icon, setIcon不会报错，但运行时还是看不到icon的
        subMenu.add(group1_Id, MENU_ITEM_5, menuItemOrder, "工具栏设置").setIcon(android.R.drawable.ic_menu_preferences);

        // 子菜单项不支持显示图标，这样做是没意义的，尽管不会报错！

        menuitem1.setIcon(R.drawable.ic_settings_applications);
        //但是子菜单本身是支持图标的
        subMenu.setIcon(R.drawable.ic_settings_display);
        // 添加子菜单项栏目的标题图标，
        subMenu.setHeaderIcon(R.drawable.ic_settings_applications);
        // menu.addSubMenu("又一个子菜单");
        menu.addSubMenu(group1_Id, MENU_ITEM_6, menuItemOrder, "又一个子菜单");
        // 2.通过资源指定标题
        // menu.add(R.string.action_favorite);
        menu.add(group1_Id, MENU_ITEM_7, menuItemOrder, R.string.action_favorite);
        // 3.显示指定菜单项的组号、ID、排序号、标题
        menu.add(
                group1_Id,            //组号
                MENU_ITEM_8, //唯一的ID号
                MENU_ITEM_8, //排序号
                "弹出窗口测试"); //标题

        menu.add(group1_Id, MENU_ITEM_9, menuItemOrder, "RecyclerView_1 Test");

        menu.add(group1_Id, MENU_ITEM_10, menuItemOrder, "RecyclerView_2 Test");

        menu.add(group1_Id, MENU_ITEM_11, menuItemOrder, "部件功能 Test");

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
            case MENU_ITEM_1:

                startActivity(new Intent(this, MainListView1Activity.class));

                displayToast("MENU_ITEM_1 Selected");
                return true;
            case MENU_ITEM_2:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_2 Selected");
                return true; // break;
            case MENU_ITEM_3:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_3 Selected");
                return true; // break;
            case MENU_ITEM_4:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_4 Selected");
                return true; // break;
            case MENU_ITEM_5:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_5 Selected");
                return true; // break;
            case MENU_ITEM_6:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_6 Selected");
                return true; // break;
            case MENU_ITEM_7:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                // startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_7 Selected");
                return true; // break;
            case MENU_ITEM_8:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, PopActivity.class));

                displayToast("MENU_ITEM_8 Selected");
                return true; // break;
            case MENU_ITEM_9:
                /*
                Intent intent = new Intent(this, MainRecyclerView1Activity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainImagesActivity.class));

                displayToast("MENU_ITEM_9 Selected");
                return true; // break;
            case MENU_ITEM_10:
                /*
                Intent intent = new Intent(this, MainTestActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_10 Selected");
                return true; // break;
            case MENU_ITEM_11:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, MainTestActivity.class));

                displayToast("MENU_ITEM_11 Selected");
                return true; // break;
            default:
                /*
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, CloudSettingsActivity.class));
                return false;
        }

    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
