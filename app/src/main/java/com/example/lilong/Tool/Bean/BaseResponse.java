package com.example.lilong.Tool.Bean;


import com.google.gson.annotations.Expose;

/**
 * Created by long on 18/5/10.
 */
public class BaseResponse {

    @Expose
    private String msg;
    @Expose
    private int code;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
