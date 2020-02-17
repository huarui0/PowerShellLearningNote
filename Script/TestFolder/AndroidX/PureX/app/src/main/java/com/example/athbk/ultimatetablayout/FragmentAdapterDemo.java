package com.example.athbk.ultimatetablayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.ultimatetablayout.IFTabAdapter;

/**
 * Created by athbk on 8/25/17.
 */

public class FragmentAdapterDemo extends FragmentPagerAdapter implements IFTabAdapter {


    public FragmentAdapterDemo(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentDemo.newInstance();
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public String getTitle(int position) {
        return "";
    }

    @Override
    public int getIcon(int position) {
        return R.drawable.tab_1_selected;
    }

    @Override
    public boolean isEnableBadge(int position) {
        if (position == 0){
            return true;
        }
        return false;
    }
}
