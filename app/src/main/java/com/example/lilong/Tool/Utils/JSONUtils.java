package com.example.lilong.Tool.Utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by long on 2018-4-12.
 * 功能：JSON解析的封装
 */
public class JSONUtils {


    private static Gson gson = new Gson();

    public static <T> T parseJson(String response, Class<T> clazz) {
        try {
            return gson.fromJson(response, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject toJSONObject(String str){

        try{
            if(!TextUtils.isEmpty(str)){
                return new JSONObject(str);
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+str+".e-->"+e.getMessage());
            e.printStackTrace();
            return new JSONObject();
        }
        return new JSONObject();
    }

    public static String getString(JSONObject jsonObject, String key){
        try{
            if(jsonObject.has(key)){
                return StringUtils.stringNoNull(jsonObject.getString(key));
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+key+".e-->"+e.getMessage());
            e.printStackTrace();
            return "";
        }

        return "";
    }

    public static long getLong(JSONObject jsonObject, String key){
        try{
            if(jsonObject.has(key)){
                return jsonObject.getLong(key);
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+key+".e-->"+e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static boolean getBoolean(JSONObject jsonObject, String key){
        try{
            if(jsonObject.has(key)){
                return jsonObject.getBoolean(key);
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+key+".e-->"+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static int getInt(JSONObject jsonObject, String key){
        try{
            if(jsonObject.has(key)){
                return jsonObject.getInt(key);
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+key+".e-->"+e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static double getDouble(JSONObject jsonObject, String key){
        try{
            if(jsonObject.has(key)){
                return jsonObject.getDouble(key);
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+key+".e-->"+e.getMessage());
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key){
        try{
            if (jsonObject.has(key)) {
                return jsonObject.getJSONObject(key);
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+key+".e-->"+e.getMessage());
            e.printStackTrace();
            return new JSONObject();
        }
        return new JSONObject();
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String key){
        try{
            if(jsonObject.has(key)){
                return jsonObject.getJSONArray(key);
            }
        }catch (JSONException e){
            LogUtils.d("JSONUtils."+key+".e-->"+e.getMessage());
            e.printStackTrace();
            return new JSONArray();
        }
        return new JSONArray();
    }


}
