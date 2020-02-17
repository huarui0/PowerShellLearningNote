
/*
http://blog.csdn.net/ttdevs/article/details/9733779

Android Fragement学习笔记(三)----PreferenceFragment的使用
http://blog.csdn.net/eyu8874521/article/details/8250611

Create a Settings Activity
https://gist.github.com/rmnguleria/e2eae0b737c8f9fdc0d5

Using PreferenceFragment to Store User Preferences
http://www.cs.dartmouth.edu/~campbell/cs65/lecture12/lecture12.html

如何给非AppCompatActivity添加Toolbar？--关于5.0新特性兼容5.0以下设备的探索
http://www.cnblogs.com/littlepanpc/p/4528494.html

Beginners Android Studio Tutorial – 57 PreferanceFragment Passing Settings Data
https://www.youtube.com/watch?v=f31ERWzFGTE

*/

package com.huarui.mobile.sunshine;

import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

/**
 * Created by wanglai on 11/16/2016.
 */

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
        setContentView(R.layout.activity_settings);

        // getFragmentManager().beginTransaction()
        // .replace(android.R.id.content, new PrefsFragement()).commit();
        getFragmentManager().beginTransaction()
                .replace(R.id.llPF, new SettingsFragment()).commit();

/*
//分解方式写法：
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PrefsFragment settingsFragment = new PrefsFragment();
        fragmentTransaction.add(R.id.llPF,settingsFragment,"SETTINGS_FRAGMENT");

*/
    }

    public static class SettingsFragment extends PreferenceFragment implements OnPreferenceChangeListener {

        /**
         * 需要针对每个Preference(如下面的ListPreference)设置OnPreferenceChangeListener,
         * 并且在Class声明中要implements Preference.OnPreferenceChangeListener
         */

        ListPreference lp;//创建一个ListPreference对象

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences_settingstest);

/* 通过一般性的方法：setAllPreferenceListener来设置
            //初始化这个ListPreference对象
            // lp = (ListPreference) findPreference(getString(R.string.key_str));
            lp = (ListPreference) findPreference("myListPreference");

            //设置获取ListPreference中发生的变化
            lp.setOnPreferenceChangeListener(this);

            */
/**让ListPreference中的摘要内容（即summary）显示为当前ListPreference中的实体对应的值
 * 这个方法的作用是为了当下一次打开这个程序时会显示上一次的设置的summary(摘要)
 * 如果没有添加这个方法，当再次打开这个程序时，它将不会显示上一次程序设置的值，而
 * 是显示默认值*//*

            lp.setSummary(lp.getEntry());
*/
            setAllPreferenceListener();
        }

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

        private void setAllPreferenceListener(Preference preference) {
            if (preference instanceof PreferenceScreen) {
                PreferenceScreen ps = (PreferenceScreen) preference;
                int cnt = ps.getPreferenceCount();
                System.out.println("getPreferenceCount(PreferenceScreen):" + cnt);
                for (int i = 0; i < cnt; ++i) {
                    Preference p = ps.getPreference(i);
                    setAllPreferenceListener(p);
                }
            } else if (preference instanceof PreferenceCategory) {
                PreferenceCategory pc = (PreferenceCategory) preference;
                int cnt = pc.getPreferenceCount();
                System.out.println("getPreferenceCount(PreferenceCategory):" + cnt);
                for (int i = 0; i < cnt; ++i) {
                    Preference p = pc.getPreference(i);
                    setAllPreferenceListener(p);
                }
            } else {
                bindPreferenceSummaryToValue(preference);
            }
            setLayoutResource(preference);
        }


        public void setAllPreferenceListener() {
            PreferenceScreen ps = getPreferenceScreen();
            setAllPreferenceListener(ps);

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
            /*
            Exception:
                java.lang.ClassCastException: java.lang.Boolean cannot be cast to java.lang.String
            Solution:
                http://stackoverflow.com/questions/16329426/preference-cannot-cast-java-lang-boolean-to-string
            */
            System.out.println("preference.getKey():" + preference.getKey());
            Object result = null;

            if (preference instanceof CheckBoxPreference) {
                if (result instanceof Boolean) {
                    //handle boolean
                    System.out.println("preference.getKey():" + "Boolean");
                } else if (result instanceof String) {
                    //handle String
                    System.out.println("preference.getKey():" + "String");
                } else {
                    result = preference.getKey();
                    System.out.println("result:" + result);
                }
                // Trigger the listener immediately with the preference's
                // current value.
                onPreferenceChange(
                        preference,
                        PreferenceManager.getDefaultSharedPreferences(
                                preference.getContext()).getBoolean(preference.getKey(), true));
            } else {
                if (result instanceof Boolean) {
                    //handle boolean
                    System.out.println("preference.getKey():" + "Boolean");
                } else if (result instanceof String) {
                    //handle String
                    System.out.println("preference.getKey():" + "String");
                } else {
                    result = preference.getKey();
                    System.out.println("result:" + result);
                }
                onPreferenceChange(preference,
                        PreferenceManager
                                .getDefaultSharedPreferences(preference.getContext())
                                .getString(preference.getKey(), ""));
            }
        }
/*
方法1：针对ListPreference，不推荐！
方法2：针对所有的Preference，推荐使用！
*/
/*
        //让所选择的项显示出来,获取变化并显示出来
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            // TODO Auto-generated method stub
            if (preference instanceof ListPreference) {
                //把preference这个Preference强制转化为ListPreference类型
                ListPreference listPreference = (ListPreference) preference;
                //获取ListPreference中的实体内容
                CharSequence[] entries = listPreference.getEntries();
                //获取ListPreference中的实体内容的下标值
                int prefIndex = listPreference.findIndexOfValue((String) newValue);
                //把listPreference中的摘要显示为当前ListPreference的实体内容中选择的那个项目
                listPreference.setSummary(entries[prefIndex]);
            }
            return true;
        }
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
                    preference.setSummary(listPreference.getEntries()[prefIndex]);
                }
            } else {
                // For other preferences, set the summary to the value's simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    }
}
