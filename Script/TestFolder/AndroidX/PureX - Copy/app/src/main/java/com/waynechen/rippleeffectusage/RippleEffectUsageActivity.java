package com.waynechen.rippleeffectusage;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lzsoft.lzdata.mobile.purex.R;

public class RippleEffectUsageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_effect_usage);

        Drawable background = getResources().getDrawable( R.drawable.rose_flower_blossom_bloom ,null);
        int color = Color.RED;
        findViewById(R.id.ll).setBackground(getBackgroundDrawable(color, background));

    }

    public static RippleDrawable getBackgroundDrawable(int pressedColor, Drawable backgroundDrawable)
    {
        return new RippleDrawable(getPressedState(pressedColor), backgroundDrawable, null);
    }

    public static ColorStateList getPressedState(int pressedColor)
    {
        return new ColorStateList(new int[][]{ new int[]{}},new int[]{pressedColor});
    }
}
