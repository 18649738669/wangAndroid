package com.example.lilong.Tool.DB;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lilong.Tool.APP.App;


/**
 * Created by long on 2018-4-12.
 * 功能：封装SharedPreferences类的功能。
 */
public class SPHelper {

    private static boolean defaultBoolean = false;
    private static int defaultInt = 0;
    private static String defaultString = "";
    private static long defaultLong = 0L;
    private static float defaultFloat = 0.0f;

    public static boolean isDefaultBoolean() {
        return defaultBoolean;
    }

    public static void setDefaultBoolean(boolean defaultBoolean) {
        SPHelper.defaultBoolean = defaultBoolean;
    }

    public static int getDefaultInt() {
        return defaultInt;
    }

    public static void setDefaultInt(int defaultInt) {
        SPHelper.defaultInt = defaultInt;
    }

    public static String getDefaultString() {
        return defaultString;
    }

    public static void setDefaultString(String defaultString) {
        SPHelper.defaultString = defaultString;
    }

    public static long getDefaultLong() {
        return defaultLong;
    }

    public static void setDefaultLong(long defaultLong) {
        SPHelper.defaultLong = defaultLong;
    }

    public static float getDefaultFloat() {
        return defaultFloat;
    }

    public static void setDefaultFloat(float defaultFloat) {
        SPHelper.defaultFloat = defaultFloat;
    }


    public static void save(String name, String key, int data){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key,data);
        editor.commit();
    }

    public static void save(String name, String key, String data){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, data);
        editor.commit();
    }

    public static void save(String name, String key, long data){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, data);
        editor.commit();
    }

    public static void save(String name, String key, float data){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, data);
        editor.commit();
    }

    public static void save(String name, String key, boolean data){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }

    public static void removeKey(String name, String key){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
//        editor.clear();
        editor.commit();
    }

    public static String getString(String name, String key){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key,defaultString);
    }

    public static int getInt(String name, String key){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultInt);
    }

    public static float getFloat(String name, String key){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultFloat);
    }

    public static long getLong(String name, String key){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultLong);
    }

    public static boolean getBoolean(String name, String key){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getBoolean(key,defaultBoolean);
    }

    public static boolean hasKey(String name, String key){
        SharedPreferences sp = App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.contains(key);
    }
}
