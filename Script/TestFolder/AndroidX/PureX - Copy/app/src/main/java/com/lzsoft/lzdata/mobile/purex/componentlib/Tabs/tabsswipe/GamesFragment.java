package com.lzsoft.lzdata.mobile.purex.componentlib.Tabs.tabsswipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.lzsoft.lzdata.mobile.purex.R;

public class GamesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tabs_swipe_games, container, false);
		
		return rootView;
	}
}
