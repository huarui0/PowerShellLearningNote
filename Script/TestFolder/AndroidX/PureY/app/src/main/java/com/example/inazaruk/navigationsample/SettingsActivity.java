package com.example.inazaruk.navigationsample;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.lzsoft.lzdata.mobile.purex.R;

public class SettingsActivity extends PreferenceActivity {

  @SuppressWarnings("deprecation")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.inazaruk_simplenavigation_settings);
  }
}
