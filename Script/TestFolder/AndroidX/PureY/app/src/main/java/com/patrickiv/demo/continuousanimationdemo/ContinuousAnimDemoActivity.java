package com.patrickiv.demo.continuousanimationdemo;

import android.os.Bundle;

import android.view.View;

import com.lzsoft.lzdata.mobile.purex.R;

import androidx.appcompat.app.AppCompatActivity;

public class ContinuousAnimDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private StarAnimationView mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_continuous_animation_demo);
        mAnimationView = (StarAnimationView) findViewById(R.id.animated_view);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_resume).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimationView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAnimationView.pause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pause:
                mAnimationView.pause();
                break;
            case R.id.btn_resume:
                mAnimationView.resume();
                break;
        }
    }
}
