/*
Reference:
        https://www.youtube.com/watch?v=ND6a4V-xdjI
*/

package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread myThread = new Thread(){
            @Override
            public void run() {

                try {
                    sleep(3000);
                    Intent thisIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(thisIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        myThread.start();
    }
}
