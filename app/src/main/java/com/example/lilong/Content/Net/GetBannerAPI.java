package com.example.lilong.Content.Net;

import com.android.volley.Request;
import com.example.lilong.Content.Model.HomeBannerModel;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.DB.SPHelper;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by long on 2018/06/11.
 * 获取首页的banner数据
 */

public class GetBannerAPI extends DragonAPI {

    GetBannerAPIListener listener;

    public GetBannerAPI(GetBannerAPIListener listener){
        this.listener = listener;
        LogUtils.d("[GetBanner-params]" + gson.toJson(params));
        new DragonHttp(this).request();
    }

    @Override
    public String getUrl() {
        return Builds.HOST + "/banner/json";
    }

    @Override
    public int getHttpType() {
        // TODO Auto-generated method stub
        return Request.Method.GET;
    }

    @Override
    public void requestSuccess(JSONObject data){
        HomeBannerModel homeBannerModel = JSONUtils.parseJson(data.toString(),HomeBannerModel.class);
        SPHelper.save(Builds.SP_USER,"HomeBannerModel",data.toString());
        listener.getBannerSuccess(homeBannerModel);
    }

    @Override
    public void requestError(long code, String msg) {
        listener.getBannerError(code,msg);
    }

    public interface GetBannerAPIListener{
        void getBannerSuccess(HomeBannerModel homeBannerModel);
        void getBannerError(long code,String msg);
    }
}
