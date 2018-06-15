package com.example.lilong.Content.Model;

/**
 * Created by long on 2018/06/15.
 */

public class DataEventBus {

    private int type;//0修改 1添加

    public DataEventBus(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
