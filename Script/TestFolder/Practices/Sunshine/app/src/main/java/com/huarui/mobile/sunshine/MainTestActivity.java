/**
 * Reference:
 * Android Studio Adding Icon/Image to Toolbar | 6
 * --> https://www.youtube.com/watch?v=Fw6v7zFUjWU
 * <p>
 * How to Create Welcome Screen in Android Studio
 * --> https://www.youtube.com/watch?v=jXtof6OUtcE
 * <p>
 * How To Make Splash Screen in Android Studio
 * --> https://www.youtube.com/watch?v=ND6a4V-xdjI
 */

package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.huarui.mobile.sunshine.utils.ScreenUtils;
import com.huarui.mobile.sunshine.utils.T;

import java.util.Calendar;

public class MainTestActivity extends Activity implements OnClickListener {


    private static final int MENU_COPY = Menu.FIRST;
    private static final int MENU_EDIT = Menu.FIRST + 1;
    private static final int MENU_PASTE = Menu.FIRST + 2;
    private static final int MENU_DELETE = Menu.FIRST + 3;
    private static final int MENU_OK = Menu.FIRST + 4;
    private static final int MENU_CANCEL = Menu.FIRST + 5;
    private static final int MENU_TEST = Menu.FIRST + 6;
    private static final int MENU_CLOUD_SETTING = Menu.FIRST + 7;

    private static final int MENU_TOOLBAR_SETTING = Menu.FIRST + 8;
    private static final int MENU_CHANGECOLOR = Menu.FIRST + 9;
    private static final int MENU_POPWINDOWTEST = Menu.FIRST + 10;

    private Toolbar thisToolbar;
    private String[] SpinnerList = {"India", "Australia", "South Africa", "New Zealand"};

    private Context thisContext = null;
    private PopupWindow mPopTop = null;

    private PopupWindow popupWindow = null;
    private TextView tvExit = null;
    private TextView tvSet = null;
    private TextView tvCancel = null;
    private View rootView;

    private ImageView imageView;
    private PopupWindow popupWindow_withImage;
    private LinearLayout linearLayoutPic;
    private LinearLayout linearLayoutGps;
    private LinearLayout linearLayoutMusic;
    private LinearLayout linearLayoutWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
/*
            这里是：状态栏和浏览栏的透明设置
*/
            // w.setStatusBarColor(Color.TRANSPARENT);
            // w.setNavigationBarColor(Color.TRANSPARENT);

        }

        setContentView(R.layout.activity_test_main);

        System.out.println("Hello World!");
        Calendar cldr = Calendar.getInstance();
        System.out.format("%tB %te, %tY%n", cldr, cldr, cldr);
        // System.exit(0);
        // return;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mCustomToolbar);
        myToolbar.setTitle("ToolbarTitle");
        myToolbar.setTitleTextColor(Color.WHITE);
        myToolbar.setSubtitle("工具栏子标题");
        myToolbar.setSubtitleTextColor(Color.YELLOW);
        myToolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
        myToolbar.setLogo(getDrawable(R.drawable.ic_action_customtoolbar));
        myToolbar.setLogoDescription(getResources().getString(R.string.app_name));
/*
        // Set a Toolbar to act as the ActionBar for this Activity window.
        
        setActionBar(myToolbar);
*/
        thisToolbar = myToolbar;
        Menu myMenu = myToolbar.getMenu();
        onCreateOptionsMenu(myMenu);

        // Set an OnMenuItemClickListener to handle menu item clicks
        myToolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Handle the menu item

                        onOptionsItemSelected(thisToolbar, item);
                        return true;

                    }
                });

        // Inflate a menu to be displayed in the toolbar_test
        myToolbar.inflateMenu(R.menu.toolbar_action_button_menu);

        Button cloudSettingTestButton = (Button) findViewById(R.id.btnCloudSettingTest);

        cloudSettingTestButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        getWindow().setExitTransition(new Explode());

                        //下面这一行，有两种方法。方法一：
                        Intent intent = new Intent(MainTestActivity.this, CloudSettingsActivity.class);
                        /*                //方法二：
                        Intent intent = new Intent();
                        intent.setClass(MainTestActivity.this, PreferenceWithHeaders.class);
                        */

                        //备注：setClass函数的第一个参数是一个Context对象
                        //备注：Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
                        //备注：setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象

                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainTestActivity.this).toBundle());
                    }
                }
        );

        Button settingsTestButton = (Button) findViewById(R.id.btnSettingsTest);

        settingsTestButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        //下面这一行，有两种方法。方法一：
                        Intent intent = new Intent(MainTestActivity.this, SettingsActivity.class);
                        /*                //方法二：
                        Intent intent = new Intent();
                        intent.setClass(MainTestActivity.this, PreferenceWithHeaders.class);
                        */

                        //备注：setClass函数的第一个参数是一个Context对象
                        //备注：Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
                        //备注：setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象

                        startActivity(intent);
                    }
                }
        );

        Button prefActivityTestButton = (Button) findViewById(R.id.btnPrefactivityTest);

        prefActivityTestButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        //下面这一行，有两种方法。方法一：
                        Intent intent = new Intent(MainTestActivity.this, PreferenceWithHeaders.class);
                        /*                //方法二：
                        Intent intent = new Intent();
                        intent.setClass(MainTestActivity.this, PreferenceWithHeaders.class);
                        */

                        //备注：setClass函数的第一个参数是一个Context对象
                        //备注：Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
                        //备注：setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象

                        startActivity(intent);
                    }
                }
        );

        Button changeToolbarColorButton = (Button) findViewById(R.id.btnchangeToolbarColor);
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        String storeColor = spf.getString(getString(R.string.color_key), "#FFFF0059"); // #FFFF0059 is default color red.

        System.out.println("Store Preference Color:" + storeColor);

        RelativeLayout mainActivity = (RelativeLayout) findViewById(R.id.activity_main);
        mainActivity.setBackgroundColor(Color.parseColor(storeColor));

        changeToolbarColorButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        finish(); // Close this activity when open ToolbarSettingsActivity
                        //下面这一行，有两种方法。方法一：
                        Intent intent = new Intent(getApplicationContext(), ToolbarSettingsActivity.class);
                        /*                //方法二：
                        Intent intent = new Intent();
                        intent.setClass(MainTestActivity.this, PreferenceWithHeaders.class);
                        */

                        //备注：setClass函数的第一个参数是一个Context对象
                        //备注：Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
                        //备注：setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象

                        startActivity(intent);
                    }
                }
        );

        Button popupMenuTestButton = (Button) findViewById(R.id.btnPopupMenuTest);

        popupMenuTestButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(MainTestActivity.this, findViewById(R.id.btnPopupMenuTest));
                        popup.getMenuInflater().inflate(R.menu.toolbar_action_button_menu, popup.getMenu());
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu_red:
                                        Toast.makeText(MainTestActivity.this, "你点了红~",
                                                Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu_blue:
                                        Toast.makeText(MainTestActivity.this, "你点了蓝~",
                                                Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu_green:
                                        Toast.makeText(MainTestActivity.this, "你点了绿~",
                                                Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return true;
                            }
                        });
                        popup.show();
                    }
                }
        );

        // Context thisContext;
        thisContext = MainTestActivity.this;
        Button popupWindowTestButton = (Button) findViewById(R.id.btnPopupWindowTest);
        popupWindowTestButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow(MainTestActivity.this, v);
            }
        });

        Button popupWindowTestButton2 = (Button) findViewById(R.id.btnPopupWindowTest2);
        popupWindowTestButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                showPopupWindow(view);
            }
        });

        Button popupWindowTestButton3 = (Button) findViewById(R.id.btnPopupWindowTest3);
        popupWindowTestButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // showPopup(view);
                test(view);
            }
        });

        Spinner thisSpinner = (Spinner) findViewById(R.id.spinner);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SpinnerList);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainTestActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.list_entries_color_name));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        thisSpinner.setAdapter(arrayAdapter);

        imageView = (ImageView) findViewById(R.id.iv_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnimation();
                if (popupWindow_withImage == null) {
                    showPopupWindow();
                } else if (popupWindow_withImage.isShowing()) {
                    popupWindow_withImage.dismiss();
                } else {
                    Switch sw = (Switch) findViewById(R.id.switch1);
                    if (sw.isChecked()) {
                        showPopupWindow();
                    } else {
                        popupWindow_withImage.showAsDropDown(imageView, -300, 0);
                    }
                }
            }
        });

        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    buttonView.setText("弹出");
                } else {
                    buttonView.setText("飞入");
                }
            }
        });
        sw.setChecked(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        // Group ID
        int groupId = 0;
        // The order position of the item
        int menuItemOrder = Menu.NONE;

        menu.add(groupId, MENU_COPY, menuItemOrder, "Copy")
                .setIcon(android.R.drawable.ic_menu_crop);
        menu.add(groupId, MENU_EDIT, menuItemOrder, "Edit");
        menu.add(groupId, MENU_PASTE, menuItemOrder, "Paste");
        menu.add(groupId, MENU_DELETE, menuItemOrder, "Delete");
        menu.add(groupId, MENU_OK, menuItemOrder, "Ok");
        menu.add(groupId, MENU_CANCEL, menuItemOrder, "Cancel");

        // 一个menu可以包括多个子菜单
        SubMenu subMenu = menu.addSubMenu(groupId, MENU_TEST, Menu.NONE, "系统设置(Setting)");
        // 子菜单可以包括多个菜单项
        MenuItem menuitem1 = subMenu.add(groupId, Menu.FIRST + 1, Menu.FIRST + 1, "设置");
        subMenu.add(groupId, MENU_CLOUD_SETTING, menuItemOrder, "CloudSetting");
        //  .setIcon(R.drawable.icon); more expand menu 不支持icon, setIcon不会报错，但运行时还是看不到icon的
        subMenu.add(groupId, MENU_TOOLBAR_SETTING, 1, "工具栏设置").setIcon(android.R.drawable.ic_menu_preferences);


        // 子菜单项不支持显示图标，这样做是没意义的，尽管不会报错！


        menuitem1.setIcon(R.drawable.ic_settings_applications);
        //但是子菜单本身是支持图标的
        subMenu.setIcon(R.drawable.ic_settings_display);
        // 添加子菜单项栏目的标题图标，
        subMenu.setHeaderIcon(R.drawable.ic_settings_applications);
        menu.addSubMenu("又一个子菜单");


        menu.add(groupId, MENU_CHANGECOLOR, 1, "Change Color").setIcon(android.R.drawable.ic_menu_preferences);

        // 添加菜单项（多种方式）
        // 1.直接指定标题
        menu.add("菜单项1");
        // 2.通过资源指定标题
        menu.add(R.string.action_favorite);
        // 3.显示指定菜单项的组号、ID、排序号、标题
        menu.add(
                1,            //组号
                MENU_POPWINDOWTEST, //唯一的ID号
                MENU_POPWINDOWTEST, //排序号
                "弹出窗口测试"); //标题

        // 如果希望显示菜单，请返回true
        //return super.onCreateOptionsMenu(menu);
        return true; //true表示要显示menu; false表示不显示menu

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //响应每个菜单项(通过菜单项的ID)
        switch (id) {
            case R.id.menu_red:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                thisToolbar.setBackgroundColor(Color.parseColor("#F44336"));
                startActivity(new Intent(this, CloudSettingsActivity.class));
                return true; // break;

            case R.id.menu_blue:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                thisToolbar.setBackgroundColor(Color.parseColor("#03A9F4"));
                startActivity(new Intent(this, CloudSettingsActivity.class));
                return true; // break;
            case R.id.menu_green:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                thisToolbar.setBackgroundColor(Color.parseColor("#4CAF50"));
                startActivity(new Intent(this, CloudSettingsActivity.class));
                return true; // break;
            case MENU_TOOLBAR_SETTING:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                thisToolbar.setBackgroundColor(Color.parseColor("#4CAF50"));
                startActivity(new Intent(this, CloudSettingsActivity.class));
                return true; // break;
            case MENU_POPWINDOWTEST:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, PopActivity.class));
                return true; // break;
            default:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return false;
/*
                //对没有处理的事件，交给父类来处理
                return super.onOptionsItemSelected(item);
*/
        }
        //返回true表示处理完菜单项的事件，不需要将该事件继续传播下去了
        // return true;
    }

    public boolean onOptionsItemSelected(Toolbar toolbar, MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_red:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                thisToolbar.setBackgroundColor(Color.parseColor("#F44336"));
                // startActivity(new Intent(this, CloudSettingsActivity.class));

                displayToast("menu_red Selected");
                return true;
            case R.id.menu_blue:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                thisToolbar.setBackgroundColor(Color.parseColor("#03A9F4"));
                // startActivity(new Intent(this, CloudSettingsActivity.class));

                displayToast("menu_blue Selected");
                return true;
            case R.id.menu_green:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                thisToolbar.setBackgroundColor(Color.parseColor("#4CAF50"));
                // startActivity(new Intent(this, CloudSettingsActivity.class));

                displayToast("menu_green Selected");
                return true;
            case MENU_TOOLBAR_SETTING:

                startActivity(new Intent(this, ToolbarSettingsActivity.class));

                displayToast("TOOLBAR_SETTING Selected");
                return true;
            case MENU_POPWINDOWTEST:
                /*
                Intent intent = new Intent(this, PopActivity.class);
                startActivity(intent);
                */
                startActivity(new Intent(this, PopActivity.class));
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


    private void initPopWindow(Context mContext, View v) {
        // 一个自定义的布局，作为显示的内容
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, null, false);
        Button btn_xixi = (Button) view.findViewById(R.id.btn_xixi);
        Button btn_hehe = (Button) view.findViewById(R.id.btn_hehe);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 10, 0);

        //设置popupWindow里的按钮的事件
        btn_xixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainTestActivity.this, "你点击了嘻嘻~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainTestActivity.this, "你点击了呵呵~", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
    }

    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(thisContext).inflate(
                R.layout.pop_window, null);
        // 设置按钮的点击事件
        Button button = (Button) contentView.findViewById(R.id.btnTestPopupWindow);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(thisContext, "button is pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getDrawable(R.drawable.ic_pop_bg));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view, 10, 0);

    }

    private void showPopup(View thisView) {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_layout, null);//PopupWindow对象
        popupWindow = new PopupWindow(this);//初始化PopupWindow对象
        popupWindow.setContentView(view);//设置PopupWindow布局文件
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);//设置PopupWindow宽
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);//设置PopupWindow高
        rootView = LayoutInflater.from(this).inflate(R.layout.activity_test_main, null);//父布局
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        popupWindow.setOutsideTouchable(true);
        tvSet = (TextView) view.findViewById(R.id.tv_set);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvExit = (TextView) view.findViewById(R.id.tv_exit);//在view对象中通过findViewById找到TextView控件
        tvSet.setOnClickListener(this);//注册点击监听
        tvCancel.setOnClickListener(this);//注册点击监听
        tvExit.setOnClickListener(this);//注册点击监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Toast.makeText(MainTestActivity.this, "PupWindow消失了！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                popupWindow.dismiss();//关闭PopupWindow
                break;
            case R.id.tv_exit:
                finish();//调用Activity的finish方法退出应用程序
                break;
            case R.id.tv_set:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
        }
    }

    public void test(View view) {
        if (popupWindow == null) {
            showPopup(view);
        } else {
            popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);//设置PopupWindow的弹出位置。
        }
    }

    private void setMyPop() {
        mPopTop = new PopupWindow(thisContext);
        int w = ScreenUtils.getScreenWidthNew(thisContext);
        int h = ScreenUtils.getScreenHeightNew(thisContext);
        mPopTop.setWidth(w / 2);
        mPopTop.setHeight(LayoutParams.WRAP_CONTENT);
        mPopTop.setFocusable(true);////获取焦点
        mPopTop.setTouchable(true);
        mPopTop.setOutsideTouchable(true);//设置popupwindow外部可点击
        //    mPopTop.update();// 刷新状态
        ColorDrawable dw = new ColorDrawable(0000000000);// 实例化一个ColorDrawable颜色为半透明
        mPopTop.setBackgroundDrawable(dw);// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        mPopTop.setAnimationStyle(R.style.AnimationPreview);//设置显示和消失动画
        LayoutInflater inflater = (LayoutInflater) thisContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.pop_top, null);
        setContentViewClickListener(conentView);
        mPopTop.setContentView(conentView);

    }

    private void setContentViewClickListener(View conentView) {
        LinearLayout lin_car_full = (LinearLayout) conentView
                .findViewById(R.id.lin_car_full);
        LinearLayout lin_car_empty = (LinearLayout) conentView
                .findViewById(R.id.lin_car_empty);

        LinearLayout lin_car_half = (LinearLayout) conentView
                .findViewById(R.id.lin_car_half);
        LinearLayout lin_car_rest = (LinearLayout) conentView
                .findViewById(R.id.lin_car_rest);
        lin_car_empty.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {


                T.showLong(thisContext, "空车");
                mPopTop.dismiss();
            }
        });

        lin_car_full.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                T.showLong(thisContext, "满载");
                mPopTop.dismiss();
            }
        });

        lin_car_half.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {


                T.showLong(thisContext, "半载");
                mPopTop.dismiss();
            }
        });

        lin_car_rest.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                T.showLong(thisContext, "勿扰");
                mPopTop.dismiss();
            }
        });
    }

    private void addAnimation() {//加入了旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);//设置动画时间
        imageView.setAnimation(rotateAnimation);//设置动画
        imageView.startAnimation(rotateAnimation);//开始动画
    }

    private void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);//获取popupWindow子布局对象
        popupWindow_withImage = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);//初始化
        popupWindow_withImage.showAsDropDown(imageView, -300, 0);//在ImageView控件下方弹出
        popupWindow_withImage.setAnimationStyle(R.style.popupAnim);//设置动画

        linearLayoutPic = (LinearLayout) view.findViewById(R.id.ll_pic);
        linearLayoutPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainTestActivity.this, "单击了图片按钮！", Toast.LENGTH_SHORT).show();
                popupWindow_withImage.dismiss();
            }
        });

        linearLayoutGps = (LinearLayout) view.findViewById(R.id.ll_gps);
        linearLayoutGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainTestActivity.this, "单击了GPS按钮！", Toast.LENGTH_SHORT).show();
                popupWindow_withImage.dismiss();
            }
        });

        linearLayoutMusic = (LinearLayout) view.findViewById(R.id.ll_music);
        linearLayoutMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainTestActivity.this, "单击了音乐按钮！", Toast.LENGTH_SHORT).show();
                popupWindow_withImage.dismiss();
            }
        });

        linearLayoutWifi = (LinearLayout) view.findViewById(R.id.ll_wifi);
        linearLayoutWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainTestActivity.this, "单击了WIFI按钮！", Toast.LENGTH_SHORT).show();
                popupWindow_withImage.dismiss();
            }
        });
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
