package com.example.lilong.Tool.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by long on 2018-4-12.
 * 功能：fragment基类
 */
public abstract class BasicFrg  extends Fragment {
    public Context context;
    public int layoutId;
    public View view;

    public BasicFrg(int layoutId){
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(layoutId, container,false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        initView(view);
        view.setClickable(true); //防止点击穿透
        return view;
    }


    public abstract void initView(View view);
}