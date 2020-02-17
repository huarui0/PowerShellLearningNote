package com.lzsoft.lzdata.mobile.purex.componentlib.Slider.introslider;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance.ExpandControlPanelActivity;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.anniinteropter.MyBounceInterpolator;
import com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.FullRecyclerViewActivity;
import com.lzsoft.lzdata.mobile.purex.login.Yudiz.material.LoginActivity;
import com.lzsoft.lzdata.mobile.purex.login.fanrunqi.materiallogin.MaterialLoginActivity;
import com.patrickiv.demo.continuousanimationdemo.StarAnimationView;


public class IntroSliderHomeActivity extends AppCompatActivity {

    private Button returnTestButton;
    private Button forwardTestButton;

    private StarAnimationView mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider_home);

        mAnimationView = (StarAnimationView) findViewById(R.id.animated_view);


        Toolbar toolbar = findViewById(R.id.intro_slider_toolbar);
        setSupportActionBar(toolbar);

        returnTestButton = findViewById(R.id.intro_slider_btn_play_again);
        returnTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We normally won't show the welcome slider again in real app
                // but this is for testing
                PrefManager prefManager = new PrefManager(getApplicationContext());

                // make first time launch TRUE
                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(IntroSliderHomeActivity.this, IntroSliderWelcomeActivity.class));
                finish();
            }
        });

        forwardTestButton = findViewById(R.id.intro_slider_btn_forward);
        forwardTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(IntroSliderHomeActivity.this, "启动下一頁面的按钮", Toast.LENGTH_SHORT).show();
                // Button button = findViewById(R.id.ani_to_enlarge_button_test);
                didTapButton(v, forwardTestButton);

                // startActivity(new Intent(IntroSliderHomeActivity.this, ExpandControlPanelActivity.class));
                startActivity(new Intent(IntroSliderHomeActivity.this, MaterialLoginActivity.class));  // LoginActivity
                finish();

            }
        });
    }

    public void didTapButton(View view, Button button) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_button);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.3, 20);  // 0.2 改为 0.5 跳得就很激烈
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
    }
}
