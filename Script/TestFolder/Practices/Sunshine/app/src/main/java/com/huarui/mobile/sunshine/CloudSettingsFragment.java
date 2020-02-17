
/*
Reference to
    用户选项和设置用户界面
    http://blog.csdn.net/liyuanjinglyj/article/details/45936555
*/
package com.huarui.mobile.sunshine;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by wanglai on 11/15/2016.
 */

public class CloudSettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make sure default values are applied.  In a real app, you would
        // want this in a shared function that is used to retrieve the
        // SharedPreferences wherever they are needed.
        PreferenceManager.setDefaultValues(getActivity(),
                R.xml.advanced_preferences, false);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragmented_preferences);

/*
        上面代码不仅实现了上述功能而且实现了SharedPreferences.OnSharedPreferenceChangeListener接口，
        当偏好设置发生改变时，就可以根据改变的Key处理相应的事件，
        当然光实现这个接口是不能监听偏好设置的变化的，还要注册后方能监听。
        getPreferenceScreen().getSharedPreferences() .registerOnSharedPreferenceChangeListener(this);
        当然有注册就必须注销。
        getPreferenceScreen().getSharedPreferences() .unregisterOnSharedPreferenceChangeListener(this);
*/

        getPreferenceScreen().getSharedPreferences() .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences() .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i("CloudSettingsFragment", "liyuanjinglyj");
        Log.i("CloudSettingsFragment",key);
        boolean flag=false;
        if("network.wifiOnly".equals(key)){
            flag=sharedPreferences.getBoolean(key,false);
        }
        if(flag){
            Toast.makeText(getActivity(), "打开了WIFI", Toast.LENGTH_LONG).show();
        }
    }
}
