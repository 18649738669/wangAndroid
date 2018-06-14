package com.example.lilong.Content.Net;

import com.android.volley.Request;
import com.example.lilong.Content.Model.HomeArticleListModel;
import com.example.lilong.Content.Model.HomeBannerModel;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.DB.SPHelper;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;

import org.json.JSONObject;

/**
 * Created by long on 2018/06/11.
 * 获取首页的文章列表数据
 */

public class GetArticleListAPI extends DragonAPI {

    GetArticleListAPIListener listener;
    private int pageIndx;

    public GetArticleListAPI(GetArticleListAPIListener listener,int pageIndx){
        this.listener = listener;
        this.pageIndx = pageIndx;
        LogUtils.d("[GetArticleList-params]" + gson.toJson(params));
        new DragonHttp(this).request();
    }

    @Override
    public String getUrl() {
        return Builds.HOST + "/article/list/"+ pageIndx +"/json";
    }

    @Override
    public int getHttpType() {
        // TODO Auto-generated method stub
        return Request.Method.GET;
    }

    @Override
    public void requestSuccess(JSONObject data){
        HomeArticleListModel homeArticleListModel = JSONUtils.parseJson(data.toString(),HomeArticleListModel.class);
        SPHelper.save(Builds.SP_USER,"HomeArticleListModel",data.toString());
        listener.getArticleListSuccess(homeArticleListModel);
    }

    @Override
    public void requestError(long code, String msg) {
        listener.getArticleListError(code,msg);
    }

    public interface GetArticleListAPIListener{
        void getArticleListSuccess(HomeArticleListModel homeArticleListModel);
        void getArticleListError(long code, String msg);
    }
}
