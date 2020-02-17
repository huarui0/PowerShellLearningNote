package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.huarui.mobile.sunshine.MyFragment.OnButton2ClickListener;

import java.util.ArrayList;

/**
 * Created by wanglai on 11/30/2016.
 */

public class MainFragmentActivity extends Activity implements OnClickListener, OnButton2ClickListener {
    private Button btn1, btn2;
    MyFragment fragment;

    Animation fadeIn, fadeOut;


    RecyclerView recyclerView;
    ArrayList<String> Number;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerView.Adapter RecyclerViewHorizontalAdapter;
    LinearLayoutManager HorizontalLayout ;
    View ChildView ;
    int RecyclerViewItemPosition ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        initView();
        // 创建MyFragment对象
        fragment = new MyFragment();

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(5000);
        fadeIn.setFillAfter(true);

        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(2000);
        fadeOut.setFillAfter(true);
    }

    private void initView() {
        // TODO Auto-generated method stub
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    private void getFragment() {
        // TODO Auto-generated method stub
        // FragmentManager可以用来管理Activity中的fragment，在Android 3.0（即API 11）时引入，所
        // 以对于之前的版本，需要使用support library中的FragmentActivity，并且使用getSupportFragmentManager()方法
        FragmentManager fragmentManager = getFragmentManager();
        // FragmentTransaction 每一个事务都是同时要执行的一套变化，必须调用commit()才能生效
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        // 再添加之前先移除之前的fragment
        beginTransaction.remove(fragment);
        // 在beginTransaction事务中加入add动作
        beginTransaction.add(R.id.fragment_ll, fragment);
        // 如果添加这一行代码，那么用户按下back时会返回前一个fragment状态
        beginTransaction.addToBackStack(null);
        // 执行事务
        beginTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn1:
                getFragment();
                break;
            case R.id.btn2:
                fragment.toastShow();
                break;
            default:
                break;
        }
    }
    // Activity中的方法，提供给Fragment调用

    public String getTestString() {

        OnButton2ClickListener("成功执行activity中的方法");

        return "成功执行activity中的方法";

    }

    // 实现Fragment中的接口
    @Override
    public void OnButton2ClickListener(String showText) {
        // TODO Auto-generated method stub
        TextView tv_activity = (TextView) findViewById(R.id.tv_activity);
        tv_activity.setText(showText);
        tv_activity.startAnimation(fadeIn);
    }
}
