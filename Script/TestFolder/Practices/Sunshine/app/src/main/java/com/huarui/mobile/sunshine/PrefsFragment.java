package com.huarui.mobile.sunshine;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by wanglai on 10/18/2016.
 */

public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragmented_preferences);
    }
}