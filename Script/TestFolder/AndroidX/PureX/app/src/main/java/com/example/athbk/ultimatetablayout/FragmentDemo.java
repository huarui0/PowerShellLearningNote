package com.example.athbk.ultimatetablayout;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lzsoft.lzdata.mobile.purex.R;

/**
 * Created by athbk on 8/25/17.
 */

public class FragmentDemo extends Fragment {

    public static FragmentDemo newInstance() {
        Bundle args = new Bundle();
        FragmentDemo fragment = new FragmentDemo();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_athbk_ultimate_tab_layout_demo, container, false);
        return v;
    }
}
