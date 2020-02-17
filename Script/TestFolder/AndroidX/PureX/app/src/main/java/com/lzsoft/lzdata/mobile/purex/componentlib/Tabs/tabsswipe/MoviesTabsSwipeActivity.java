package com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar.Tab;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lzsoft.lzdata.mobile.purex.R;
import com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe.adapter.TabsPagerAdapter;

public class MoviesTabsSwipeActivity extends AppCompatActivity implements
		TabLayout.OnTabSelectedListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;


	private Toolbar toolbar;
	private TabLayout tabLayout;

	// Tab titles
	private String[] tabs = { "Top Rated", "Games", "Movies" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs_swipe_movies);

		// actionBar = getActionBar();

		toolbar = findViewById(R.id.tabs_swipe_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		SupportActionBar.SetCustomView(R.layout.toolbarview);
*/
		// Initilization
		viewPager = findViewById(R.id.tabs_swipe_viewpager);

/*
 * 		移到 setupViewPager 中
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
 */
		setupViewPager(viewPager);

		// actionBar.setHomeButtonEnabled(false);
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// TabLayout.Tab tab;//  = new TabLayout.Tab();
		// tabLayout.addTab(tab);
		// Adding Tabs

		tabLayout = findViewById(R.id.tabs_swipe_tablayout);
		tabLayout.setupWithViewPager(viewPager);
		// setupTabIcons();

/*
		for (String tab_name : tabs) {
			tabLayout.addTab(tabLayout.newTab().setText(tab_name));
			// actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}
*/

		/**
		 * on swiping the viewpager make respective tab selected
		 * */

/*
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				// actionBar.setSelectedNavigationItem(position);
				tabLayout.setSelectedTabIndicator(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
*/
	}


	private void setupViewPager(ViewPager viewPager) {

		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);

// ViewPagerAdapter 的方式
/*
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFrag(new OneFragment(), "ONE");
		viewPager.setAdapter(adapter);
*/
	}

	@Override
	public void onTabSelected(TabLayout.Tab tab) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(TabLayout.Tab tab) {

	}

	@Override
	public void onTabReselected(TabLayout.Tab tab) {

	}
}
