package com.example.athbk.ultimatetablayout;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.ultimatetablayout.OnClickTabListener;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.ultimatetablayout.UltimateTabLayout;

public class UltimateTabLayoutActivity extends AppCompatActivity {

    UltimateTabLayout tabLayout;
    ViewPager viewPager;

    FragmentAdapterDemo adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athbk_ultimate_tab_layout);

        tabLayout = (UltimateTabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        adapter = new FragmentAdapterDemo(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

//        options. if you override onClickTabListener.
        tabLayout.setOnClickTabListener(new OnClickTabListener() {
            @Override
            public void onClickTab(int currentPos) {
                Log.e("LOG", "OnClickTab " + currentPos);
                    viewPager.setCurrentItem(currentPos);
                    if (currentPos == 1){
                        tabLayout.setNumberBadge(currentPos, 0);
                    }
                    else {
                        tabLayout.setNumberBadge(currentPos, 1);
                    }
            }
        });




        tabLayout.setViewPager(viewPager, adapter);
    }
}
