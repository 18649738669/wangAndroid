package com.example.lilong.Tool.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by long on 2018-4-12.
 * 功能：ListView的适配器基类，需要实现抽象方法Bind
 */
public abstract class BasicAdapter extends BaseAdapter {

   public List<?> data;

    public int resourceId;
//    public ImageLoader imageLoader;
    public Context context;

    public BasicViewHolder viewHolder;

    public BasicAdapter(List<?> data, int resourceId){
        this.data = data;
        this.resourceId = resourceId;
//        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resourceId,parent,false);
            viewHolder = BasicViewHolder.getViewHoler(convertView);
        }else{
            viewHolder = (BasicViewHolder)convertView.getTag();
        }
        bind(viewHolder,position);
        return convertView;
    }

    public abstract void bind(BasicViewHolder viewHolder,int position);

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public void clearData(){
        if(data == null){
            return;
        }
        data.clear();
    }
}
