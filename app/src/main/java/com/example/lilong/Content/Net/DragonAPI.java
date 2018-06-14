package com.example.lilong.Content.Net;



import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.DB.SPHelper;
import com.example.lilong.Tool.Net.BasicAPI;
import com.example.lilong.Tool.Utils.StringUtils;

import org.json.JSONObject;

/**
 * Created by long on 2018-4-12.
 */
public abstract class DragonAPI extends BasicAPI {

    public DragonAPI(){

        long timeStamp = StringUtils.getCurrentTimeStamp();
        if (SPHelper.getString(Builds.SP_USER,"sessionId") == null){
            SPHelper.save(Builds.SP_USER,"sessionId","");
        }
        String sessionId = SPHelper.getString(Builds.SP_USER,"sessionId");
        addParams("timestamp", timeStamp+"");
        addParams("sessionId", sessionId);
    }

    @Override
    public void success(JSONObject data) {
        try {
            requestSuccess(data);

        } catch (Exception e) {
            e.printStackTrace();
            requestError(-1L, "数据解析出错");
        }
    }

    @Override
    public void error(long code, String msg) {
        requestError(code,msg);
    }

    public abstract void requestSuccess(JSONObject data) throws Exception;

    public abstract void requestError(long code,String msg);
}
