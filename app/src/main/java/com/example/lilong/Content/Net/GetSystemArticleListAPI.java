package com.example.lilong.Content.Net;

import com.android.volley.Request;
import com.example.lilong.Content.Model.HomeArticleListModel;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.DB.SPHelper;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;

import org.json.JSONObject;

/**
 * Created by long on 2018/06/11.
 * 获取知识体系的文章列表数据
 */

public class GetSystemArticleListAPI extends DragonAPI {

    GetSystemArticleListAPIListener listener;
    private int pageIndx;
    private int id;

    public GetSystemArticleListAPI(GetSystemArticleListAPIListener listener, int pageIndx,int id){
        this.listener = listener;
        this.pageIndx = pageIndx;
        this.id = id;
        LogUtils.d("[GetArticleList-params]" + gson.toJson(params));
        new DragonHttp(this).request();
    }

    @Override
    public String getUrl() {
        return Builds.HOST + "/article/list/"+ pageIndx +"/json?cid="+id;
    }

    @Override
    public int getHttpType() {
        // TODO Auto-generated method stub
        return Request.Method.GET;
    }

    @Override
    public void requestSuccess(JSONObject data){
        HomeArticleListModel homeArticleListModel = JSONUtils.parseJson(data.toString(),HomeArticleListModel.class);
        SPHelper.save(Builds.SP_USER,"SystemArticleListModel",data.toString());
        listener.getSystemArticleListSuccess(homeArticleListModel);
    }

    @Override
    public void requestError(long code, String msg) {
        listener.getSystemArticleListError(code,msg);
    }

    public interface GetSystemArticleListAPIListener{
        void getSystemArticleListSuccess(HomeArticleListModel homeArticleListModel);
        void getSystemArticleListError(long code, String msg);
    }
}
