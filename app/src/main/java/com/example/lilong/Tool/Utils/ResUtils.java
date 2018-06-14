package com.example.lilong.Tool.Utils;


import com.example.lilong.Tool.APP.App;

/**
 * Created by long on 2018-4-12.
 * 功能：根据资源ID，快捷获取资源
 */
public class ResUtils {
    public static String getString(int resId){

        return App.getContext().getResources().getString(resId);
    }

    public static int getColor(int resId){
        return App.getContext().getResources().getColor(resId);
    }

    public static String getString(int resId , Object... args){
        return String.format(App.getContext().getResources().getString(resId),args);
    }
}