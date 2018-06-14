package com.example.lilong.Content.Net;

import android.util.Log;

import com.android.volley.Request;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;

import org.json.JSONObject;

/**
 * Created by long on 2018/06/07.
 * 注册接口
 */

public class RegisterAPI extends DragonAPI{

    public RegisterAPIListener listener;

    public RegisterAPI(RegisterAPIListener listener,String userName,String passWordTwo,String passWord){

        this.listener = listener;
        addParams("username", userName);
        addParams("password", passWord);
        addParams("repassword", passWordTwo);
        LogUtils.d("[Register-params]" + gson.toJson(params));
        new DragonHttp(this).request();

    }

    @Override
    public int getHttpType() {
        // TODO Auto-generated method stub
        return Request.Method.POST;
    }

    public String getUrl() {
        return Builds.HOST + "/user/register";
    }


    @Override
    public void requestSuccess(JSONObject data) throws Exception {

        JSONObject data1 = JSONUtils.getJSONObject(data,"data");
        listener.registerSuccess(data1);
    }

    @Override
    public void requestError(long code, String msg) {

        listener.registerError(code,msg);
    }

    public interface RegisterAPIListener{
        public void registerSuccess(JSONObject content);
        public void registerError(long code,String msg);
    }
}
