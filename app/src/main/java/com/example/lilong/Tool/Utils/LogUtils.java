package com.example.lilong.Tool.Utils;

import android.util.Log;

/**
 * Created by long on 2018-4-12.
 * 功能：日志输出工具类
 */
public class LogUtils {
    /**
     * 定义输出日志的TAG
     */
    public static final String TAG = "Tomato";

    /**
     * Verbose级别的日志
     * @param msg
     */
    public static void v(String msg){
        Log.v(TAG,msg);
    }

    /**
     * Debug级别的日志
     * @param msg
     */
    public static void d(String msg){
        if (TAG == null || TAG.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
            Log.d(TAG, msg);
        }else {
            while (msg.length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.d(TAG, logContent);
            }
            Log.d(TAG, msg);// 打印剩余日志
        }
    }

    /**
     * Info级别的日志
     * @param msg
     */
    public static void i(String msg){
        Log.i(TAG,msg);
    }

    /**
     * Warn级别的日志
     * @param msg
     */
    public static void w(String msg){
        Log.w(TAG,msg);
    }

    /**
     * Error级别的日志
     * @param msg
     */
    public static void e(String msg){
        Log.e(TAG,msg);
    }
}
