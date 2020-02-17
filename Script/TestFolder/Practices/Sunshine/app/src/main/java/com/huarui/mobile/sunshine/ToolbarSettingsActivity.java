package com.huarui.mobile.sunshine;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class ToolbarSettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*  Move to CloudsSettingsFragment.java
        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general);
*/

        // getFragmentManager().beginTransaction().replace(android.R.id.content, new CloudSettingsFragment()).commit();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            onCreatePreferenceActivity();
        } else {
            onCreatePreferenceFragment();
        }

/*
// For all preferences, attach an OnPreferenceChangeListener so the UI summary can be
// updated when the preference changes.
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_units_key)));
*/
    }


    /**
     * Wraps legacy {@link #onCreate(Bundle)} code for Android < 3 (i.e. API lvl
     * < 11).
     */
    @SuppressWarnings("deprecation")
    private void onCreatePreferenceActivity() {
        addPreferencesFromResource(R.xml.preference_toolbarsetting);

    }

    /**
     * Wraps {@link #onCreate(Bundle)} code for Android >= 3 (i.e. API lvl >=
     * 11).
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void onCreatePreferenceFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new ToolbarSettingsFragment())
                .commit();
    }

    /**
     * Attaches a listener so the summary is always updated with the preference value.
     * Also fires the listener once, to initialize the summary (so it shows up before the value
     * is changed.)
     */
    private void bindPreferenceSummaryToValue(Preference preference) {
// Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);
// Trigger the listener immediately with the preference's
// current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();
        if (preference instanceof ListPreference) {
// For list preferences, look up the correct display value in
// the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
// For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        ;
    }

/*
    刷新Summary步骤：
    1.继承的PreferenceFragment(extends PreferenceFragment)需要实现Preference.OnPreferenceChangeListener接口(implements Preference.OnPreferenceChangeListener)
    2.重载(@Override) onPreferenceChange
*/

    public static class ToolbarSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_toolbarsetting);

            PreferenceScreen ps = getPreferenceScreen();
            int cnt = ps.getPreferenceCount();
            System.out.println("getPreferenceCount:" + cnt);

            //2、读取所有数据

/*
            String content = "";
            Map<String, ?> allContent = (Map<String, ?>)ps.getPreferenceManager();
            //注意遍历map的方法
            for (Map.Entry<String, ?> entry : allContent.entrySet()) {
                content += (entry.getKey() + entry.getValue());
            }
            System.out.println(content);
*/
            for (int i = 0; i < cnt; ++i) {
                Preference preference = ps.getPreference(i);

                //1、根据key值读取单一数据
                /*
                String content = preference.getString("name", "lujinhong");
                */

                //设置获取Preference中发生的变化
/*
// 改为用 bindPreferenceSummaryToValue method
                preference.setOnPreferenceChangeListener(this);
                if (preference instanceof ListPreference) {
                    ListPreference lp = (ListPreference) preference;
                    lp.setSummary("HeHe:" + lp.getEntry());
                } else {
                    // For other preferences, set the summary to the value's simple string representation.
                    String stringValue = PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), "").toString();
                    preference.setSummary("HiHi:" + stringValue);
                }
*/

                bindPreferenceSummaryToValue(preference);
            }

        }

/*
        private void setLayoutResource(Preference preference) {
            if (preference instanceof PreferenceScreen) {
                PreferenceScreen ps = (PreferenceScreen) preference;
                ps.setLayoutResource(R.layout.preference_screen);
                int cnt = ps.getPreferenceCount();
                for (int i = 0; i < cnt; ++i) {
                    Preference p = ps.getPreference(i);
                    setLayoutResource(p);
                }
            } else if (preference instanceof PreferenceCategory) {
                PreferenceCategory pc = (PreferenceCategory) preference;
                pc.setLayoutResource(R.layout.preference_category);
                int cnt = pc.getPreferenceCount();
                for (int i = 0; i < cnt; ++i) {
                    Preference p = pc.getPreference(i);
                    setLayoutResource(p);
                }
            } else {
                preference.setLayoutResource(R.layout.preference);
            }
        }
*/

        /**
         * Attaches a listener so the summary is always updated with the preference value.
         * Also fires the listener once, to initialize the summary (so it shows up before the value
         * is changed.)
         */
        private void bindPreferenceSummaryToValue(Preference preference) {
            // Set the listener to watch for value changes.
            preference.setOnPreferenceChangeListener(this);

            // Trigger the listener immediately with the preference's
            // current value.
            onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }

        /*
        方法1：针对ListPreference，不推荐！
        //注：方法1见 SettingActivity中！
        方法2：针对所有的Preference，推荐使用！
        */
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list (since they have separate labels/values).
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    preference.setSummary("选择一个背景色(Choose a backgroud color):" + listPreference.getEntries()[prefIndex]);
                }
            } else {
                // For other preferences, set the summary to the value's simple string representation.
                preference.setSummary("HyHy:" + stringValue);
            }
            return true;
        }
    }
}
