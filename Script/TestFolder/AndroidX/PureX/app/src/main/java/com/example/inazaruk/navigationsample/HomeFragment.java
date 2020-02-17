package com.example.inazaruk.navigationsample;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzsoft.lzdata.mobile.purex.R;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View v = inflater.inflate(R.layout.inazaruk_simplenavigation_home, container, false);
    return v;
  }
}
