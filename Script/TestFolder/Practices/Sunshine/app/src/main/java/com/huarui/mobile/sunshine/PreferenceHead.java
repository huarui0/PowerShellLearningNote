package com.huarui.mobile.sunshine;

import android.content.Context;
import android.preference.Preference;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by wanglai on 12/6/2016.
 */

public class PreferenceHead extends Preference {

    private OnClickListener onBackButtonClickListener;

    public PreferenceHead(Context context) {
        super(context);
        setLayoutResource(R.layout.preference_head);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        Button btBack = (Button) view.findViewById(R.id.back);
        btBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onBackButtonClickListener != null) {
                    onBackButtonClickListener.onClick(v);
                }
            }
        });
    }

    public void setOnBackButtonClickListener(OnClickListener onClickListener) {
        this.onBackButtonClickListener = onClickListener;
    }
}