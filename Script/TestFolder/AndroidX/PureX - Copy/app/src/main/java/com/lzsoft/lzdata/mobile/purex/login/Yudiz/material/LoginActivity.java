package com.lzsoft.lzdata.mobile.purex.login.Yudiz.material;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.WindowManager;

import android.view.View.OnClickListener;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lzsoft.lzdata.mobile.purex.ControlPannel.expandable.instance.ExpandControlPanelActivity;
import com.lzsoft.lzdata.mobile.purex.R;

public class LoginActivity extends AppCompatActivity {
    @RequiresApi(Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yudiz_material_sample_login);
        Button next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(navigateTo(ExpandControlPanelActivity.class));
        changeStatusBarColor(this.getColor(android.R.color.white));

    }

    public OnClickListener navigateTo(final Class<?> clazz) {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, clazz);
                startActivity(intent);
            }
        };
    }

    // 与 IntroSliderWelcomeActivity 相同的函数
    public void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
}

