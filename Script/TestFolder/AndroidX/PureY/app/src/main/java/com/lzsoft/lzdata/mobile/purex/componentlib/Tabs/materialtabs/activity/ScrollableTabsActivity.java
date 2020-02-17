package com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.EightFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.FiveFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.FourFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.NineFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.OneFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.SevenFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.SixFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.TenFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.ThreeFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.materialtabs.fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class ScrollableTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_tabs_scrollable_tab);

        toolbar = (Toolbar) findViewById(R.id.scrollable_material_tabs_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new TwoFragment(), "TWO");
        adapter.addFrag(new ThreeFragment(), "THREE");
        adapter.addFrag(new FourFragment(), "FOUR");
        adapter.addFrag(new FiveFragment(), "FIVE");
        adapter.addFrag(new SixFragment(), "SIX");
        adapter.addFrag(new SevenFragment(), "SEVEN");
        adapter.addFrag(new EightFragment(), "EIGHT");
        adapter.addFrag(new NineFragment(), "NINE");
        adapter.addFrag(new TenFragment(), "TEN");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
