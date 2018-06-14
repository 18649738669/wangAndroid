package com.example.lilong.Content.Net;

import com.android.volley.Request;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;

import org.json.JSONObject;

/**
 * Created by long on 2018/06/07.
 * 登陆接口
 */

public class LoginAPI extends DragonAPI{

    public LoginAPIListener listener;

    public LoginAPI(LoginAPIListener listener, String userName, String passWord){

        this.listener = listener;
        addParams("username", userName);
        addParams("password", passWord);
        LogUtils.d("[Login-params]" + gson.toJson(params));
        new DragonHttp(this).request();

    }

    @Override
    public int getHttpType() {
        // TODO Auto-generated method stub
        return Request.Method.POST;
    }

    public String getUrl() {
        return Builds.HOST + "/user/login";
    }


    @Override
    public void requestSuccess(JSONObject data) throws Exception {

        JSONObject data1 = JSONUtils.getJSONObject(data,"data");
        listener.loginSuccess(data1);
    }

    @Override
    public void requestError(long code, String msg) {

        listener.loginError(code,msg);
    }

    public interface LoginAPIListener{
        public void loginSuccess(JSONObject content);
        public void loginError(long code, String msg);
    }
}
