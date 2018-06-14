package com.example.lilong.Content.Net;

import com.android.volley.Request;
import com.example.lilong.Content.Model.HomeArticleListModel;
import com.example.lilong.Content.Model.SystemDataModel;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.DB.SPHelper;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;

import org.json.JSONObject;

/**
 * Created by long on 2018/06/11.
 * 获取知识体系的列表数据
 */

public class GetSystemListAPI extends DragonAPI {

    GetSystemListAPIListener listener;

    public GetSystemListAPI(GetSystemListAPIListener listener){
        this.listener = listener;
        LogUtils.d("[GetSystemList-params]" + gson.toJson(params));
        new DragonHttp(this).request();
    }

    @Override
    public String getUrl() {
        return Builds.HOST + "/tree/json";
    }

    @Override
    public int getHttpType() {
        // TODO Auto-generated method stub
        return Request.Method.GET;
    }

    @Override
    public void requestSuccess(JSONObject data){
        SystemDataModel systemListModel = JSONUtils.parseJson(data.toString(),SystemDataModel.class);
        SPHelper.save(Builds.SP_USER,"SystemDataModel",data.toString());
        listener.getSystemListSuccess(systemListModel);
    }

    @Override
    public void requestError(long code, String msg) {
        listener.getSystemListError(code,msg);
    }

    public interface GetSystemListAPIListener{
        void getSystemListSuccess(SystemDataModel systemListModel);
        void getSystemListError(long code, String msg);
    }
}
