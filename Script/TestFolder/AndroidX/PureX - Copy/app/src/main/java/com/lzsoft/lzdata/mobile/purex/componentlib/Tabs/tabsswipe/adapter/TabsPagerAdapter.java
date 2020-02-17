package com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe.GamesFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe.MoviesFragment;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe.TopRatedFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new TopRatedFragment();
		case 1:
			// Games fragment activity
			return new GamesFragment();
		case 2:
			// Movies fragment activity
			return new MoviesFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
