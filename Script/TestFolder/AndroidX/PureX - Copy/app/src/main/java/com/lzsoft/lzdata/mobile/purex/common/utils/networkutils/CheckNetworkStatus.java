package com.lzsoft.lzdata.mobile.purex.common.utils.networkutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Abhi on 23 Jun 2017 023.
 * Checks whether the device is connected to internet or not
 */

public class CheckNetworkStatus {
    /*
        currentIpAddress变量是连接mysql数据的ip address
     */
    // public static String currentIpAddress = "192.168.1.102";
    // public static final String CURRENT_IP_ADDRESS = "192.168.0.15";
    // public static final String CURRENT_IP_ADDRESS = "172.20.10.3";

    public static final String CURRENT_IP_ADDRESS = "192.168.1.102";
    // public static String baseUrl= "https://afferent-employee.000webhostapp.com/androidTest/";

    public static String BASE_URL = "http://%s/smarthomeapi/";
    public static String connDbBaseUrl= "http://%s/smarthomeapi/conndb/";
    public static String jsonDbBaseUrl= "http://%s/smarthomeapi/json/";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}