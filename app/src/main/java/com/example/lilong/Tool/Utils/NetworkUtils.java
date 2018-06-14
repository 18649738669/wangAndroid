package com.example.lilong.Tool.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by long on 2018-4-12.
 * 功能：获取网路状态的工具类
 */
public class NetworkUtils {

    /**
     * 判断当前是否有网络连接
     * @param context
     * @return
     */
    public static boolean isNetworkConnect(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            return networkInfo.isAvailable();
        }
        return false;
    }


    /**
     * 判断当前是否是手机数据流量网络
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context){
        if(context != null){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            NetworkInfo moibleInfo = connectivityManager.getNetworkInfo(ConnectivityManager
                    .TYPE_MOBILE);
            if(moibleInfo != null){
                return moibleInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断当前是否是WiFi网络环境
     * @param context
     * @return
     */
    public static boolean isWIFIConnected(Context context){
        if(context != null){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager
                    .TYPE_WIFI);

            if(wifiInfo != null){
                return wifiInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWiFiActive(Context inContext) {
        Context context = inContext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}