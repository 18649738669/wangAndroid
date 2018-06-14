package com.example.lilong.Content.Net;

import com.android.volley.VolleyError;
import com.example.lilong.Content.Utils.ActivityCollector;
import com.example.lilong.Tool.Net.BasicHttp;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;
import com.example.lilong.Tool.Utils.TipUtils;


import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by long on 2018-4-12.
 */
public class DragonLogHttp extends BasicHttp {

    private DragonAPI basicAPI;

    public DragonLogHttp(DragonAPI basicAPI){
        super(basicAPI);
        this.basicAPI = basicAPI;
    }

    @Override
    public void onResponse(JSONObject response) {
        LogUtils.d("response-->" + response);
        try{
            int code = JSONUtils.getInt(response, "code");
            String msg = JSONUtils.getString(response,"msg");
            JSONArray content = JSONUtils.getJSONArray(response,"content");
            if (code == 0){
                basicAPI.requestSuccess(response);
            }else if (code == 605) {

                TipUtils.showTip("登录超时，请重新登录");
                ActivityCollector.finishAll();
//                LoginAct.startNewLoginAct();

            }else if (code < 0){

                basicAPI.requestError(code,msg);
            }
        }catch (Exception e){
            e.printStackTrace();
            basicAPI.requestError(0, "服务器繁忙，请稍后再试4");
        }

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        basicAPI.requestError(0, "服务器繁忙，请稍后再试5");
    }
}
