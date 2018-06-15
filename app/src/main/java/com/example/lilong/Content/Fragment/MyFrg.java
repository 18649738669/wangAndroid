package com.example.lilong.Content.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lilong.Content.Activity.LoginAct;
import com.example.lilong.Content.Model.DataEventBus;
import com.example.lilong.R;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.Activity.BasicFrg;
import com.example.lilong.Tool.DB.SPHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by long on 2018/06/08.
 * 我的页面
 */

public class MyFrg extends BasicFrg {


    Unbinder unbinder;
    @BindView(R.id.vp_my_username)
    TextView tvUsername;
    @BindView(R.id.my_tv_exit)
    TextView tvExit;


    public MyFrg() {
        super(R.layout.vp_frg_my);
    }

    @Override
    public void initView(View view) {
        setTvUsername();
    }

    public void setTvUsername(){
        String username = SPHelper.getString(Builds.SP_USER,"username");
        if (username == null || username.equals("")) {
            tvUsername.setText(R.string.title_activity_login);
            tvExit.setVisibility(View.GONE);
            tvUsername.setEnabled(true);
        }else {
            tvUsername.setText(username);
            tvUsername.setEnabled(false);
            tvExit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);

    }

    @OnClick({R.id.vp_my_username, R.id.my_tv_collection, R.id.my_tv_about, R.id.my_tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vp_my_username:
                LoginAct.startActivity(getContext());
                break;
            case R.id.my_tv_collection:
                break;
            case R.id.my_tv_about:
                break;
            case R.id.my_tv_exit:
                SPHelper.save(Builds.SP_USER,"email","");
                SPHelper.save(Builds.SP_USER,"icon","");
                SPHelper.save(Builds.SP_USER,"password","");
                SPHelper.save(Builds.SP_USER,"username","");
                SPHelper.save(Builds.SP_USER,"id","");
                SPHelper.save(Builds.SP_USER,"type","");
                setTvUsername();
                LoginAct.startActivity(getContext());
                break;
        }
    }

    @Subscribe
    public void onEventBus(DataEventBus eventBus){
        setTvUsername();
    }

}
