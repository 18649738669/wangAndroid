package com.example.lilong.Content.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lilong.R;
import com.example.lilong.Tool.Activity.BasicFrg;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by long on 2018/06/08.
 * 我的页面
 */

public class MyFrg extends BasicFrg {


    @BindView(R.id.frg_my)
    TextView frgMy;
    Unbinder unbinder;

    public MyFrg() {
        super(R.layout.vp_frg_my);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
