package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
    private static int SPLASH_TIME_OUT = 4000; //4000 = 4s; 25000

    Animation fadeIn, fadeOut;
    TextView tv;
    Button startAnimationBotton;
    Boolean fadeflag = true;
    //-----------------------------------
    TextView blobText;
    public String[] text = new String[]{""};
    public int position = 0;
    Animation fadeiInAnimationObject;
    Animation textDisplayAnimationObject;
    Animation delayBetweenAnimations;
    Animation fadeOutAnimationObject;
    int fadeEffectDuration;
    int delayDuration;
    int displayFor;

    public WelcomeActivity() {

    }

    public WelcomeActivity(TextView textV, String[] textList) {
        this(textV, 700, 1000, 2000, textList);
    }

    public WelcomeActivity(TextView textView, int fadeEffectDuration, int delayDuration, int displayLength, String[] textList) {
        blobText = textView;
        // tv = textView;
        text = textList;
        this.fadeEffectDuration = fadeEffectDuration;
        this.delayDuration = delayDuration;
        this.displayFor = displayLength;
        InnitializeAnimation();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv = (TextView) findViewById(R.id.textView_Welcome);
        startAnimationBotton = (Button) findViewById(R.id.btnStartAnimation);

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(4000);
        fadeIn.setFillAfter(true);

        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(5000);
        fadeOut.setFillAfter(true);

        startAnimationBotton.setText("Start fadeIn");

        startAnimationBotton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        if (fadeflag) {
                            startAnimationBotton.setText("Start fadeOut");
                            tv.setText("This is an animated textview");
                            tv.startAnimation(fadeIn);
                            fadeflag = false;
                        } else {
                            startAnimationBotton.setText("Start fadeIn");
                            tv.setText("This is an animated textview");
                            tv.startAnimation(fadeOut);
                            fadeflag = true;
                        }
                    }
                }
        );

        // testAlphaAnimation(fadeIn);
        testAlphaAnimation(fadeOut);

        TextView introText = (TextView) findViewById(R.id.blobText);
        WelcomeActivity animator = new WelcomeActivity(introText, new String[]{
                "Hello user!",
                "Have a NijaSpoon account?",
                "You can log in with your NijaSpoon Account",
                "or sign up using Facebook",
                "NijaSpoon",
                "Real Food, in Real Time"});
        animator.startAnimation();

        // Welcome Screen here
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(WelcomeActivity.this, SplashActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    public void testAlphaAnimation(Animation fade) {

        // startanimation.setText("Start fadeOut");
        // tv.setText("This is an animated textview");
        tv.startAnimation(fade);
    }

    private void InnitializeAnimation() {
        fadeiInAnimationObject = new AlphaAnimation(0f, 1f);
        fadeiInAnimationObject.setDuration(fadeEffectDuration);
        textDisplayAnimationObject = new AlphaAnimation(1f, 1f);
        textDisplayAnimationObject.setDuration(displayFor);
        delayBetweenAnimations = new AlphaAnimation(0f, 0f);
        delayBetweenAnimations.setDuration(delayDuration);
        fadeOutAnimationObject = new AlphaAnimation(1f, 0f);
        fadeOutAnimationObject.setDuration(fadeEffectDuration);
        fadeiInAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                position++;
                if (position >= text.length) {
                    position = 0;
                }
                blobText.setText(text[position]);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                blobText.startAnimation(textDisplayAnimationObject);
            }
        });
        textDisplayAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeOutAnimationObject);
            }
        });
        fadeOutAnimationObject.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(delayBetweenAnimations);
            }
        });
        delayBetweenAnimations.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeiInAnimationObject);
            }
        });
    }

    public void startAnimation(TextView thisText) {
        thisText.startAnimation(fadeOutAnimationObject);
    }

    public void startAnimation() {
        blobText.startAnimation(fadeOutAnimationObject);
    }
}
