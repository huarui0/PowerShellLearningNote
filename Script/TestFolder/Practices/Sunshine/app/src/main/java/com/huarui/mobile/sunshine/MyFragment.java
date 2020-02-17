package com.huarui.mobile.sunshine;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wanglai on 11/30/2016.
 */

public class MyFragment extends Fragment implements OnClickListener {
    OnButton2ClickListener mListener;
    private Button button1;
    private Button button2;
    private TextView tv_test;

    private static int TIME_OUT = 7000; //4000 = 4s; 25000

    Animation fadeIn, fadeOut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(5000);
        fadeIn.setFillAfter(true);

        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(2000);
        fadeOut.setFillAfter(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_fragment_main, container, false);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        tv_test = view.findViewById(R.id.tv_test);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        return view;
    }

    // Fragment中的方法，供Activity调用
    public void toastShow() {
        Log.i("MyFragment", "Activity使用Fragment的方法");
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:
                // 调用Activity中的方法
                if (tv_test.getText() != null) {
                    tv_test.setText("Start 执行Activity中的方法！");
                    tv_test.startAnimation(fadeIn);
                    tv_test.startAnimation(fadeOut);

                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String text1 = ((MainFragmentActivity) getActivity()).getTestString();
                        tv_test.setText(text1);
                        tv_test.startAnimation(fadeIn);

                    }
                }, TIME_OUT);
                break;
            case R.id.button2:
                // fragment和activity共享点击事件
                String showText = "fragment和activity共享点击事件";
                mListener.OnButton2ClickListener(showText);
                break;
            default:
                break;
        }
    }

    // 定义接口
    public interface OnButton2ClickListener {
        public void OnButton2ClickListener(String showText);
    }

    // 如果MainActivity没有实现这个接口，MyFragment就会
    // 抛出ClassCastException，如果成功了，那么MyFragment就
    // 可以调用mListener的方法来与MainActivity共享事件。


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnButton2ClickListener) context;
        } catch (Exception e) {
            // TODO: handle exception
            throw new ClassCastException(context.toString() + "must implement OnButton2ClickListener");
        }
    }
/*
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        try {
            mListener = (OnButton2ClickListener) activity;
        } catch (Exception e) {
            // TODO: handle exception
            throw new ClassCastException(activity.toString() + "must implement OnButton2ClickListener");
        }
    }
*/
}
