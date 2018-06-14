package com.example.lilong.Content.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lilong.Content.Adapter.SystemAdapter;
import com.example.lilong.Content.Model.SystemDataModel;
import com.example.lilong.Content.Net.GetSystemListAPI;
import com.example.lilong.R;
import com.example.lilong.Tool.Activity.BasicFrg;
import com.example.lilong.Tool.Utils.TipUtils;
import com.nestrefreshlib.RefreshViews.RefreshListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by long on 2018/06/08.
 * 知识体系页面
 */

public class SystemFrg extends BasicFrg implements GetSystemListAPI.GetSystemListAPIListener {


    Unbinder unbinder;
    SystemAdapter systemAdapter;
    @BindView(R.id.vp_system_rl)
    RecyclerView vpSystemRl;
    @BindView(R.id.vp_system_refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<SystemDataModel.ObjBean> systemList = new ArrayList<SystemDataModel.ObjBean>();
    public boolean isLoad = true;//是否允许上拉加载

    public SystemFrg() {
        super(R.layout.vp_frg_system);
    }

    @Override
    public void initView(View view) {
        initListView();
    }

    /**
     * 初始化首页数据列表
     */
    private void initListView() {
        loadData();
        systemAdapter = new SystemAdapter(getContext(), systemList);
        vpSystemRl.setLayoutManager(new LinearLayoutManager(getContext()));
        vpSystemRl.setAdapter(systemAdapter);
        systemAdapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadData();
                refreshlayout.finishRefresh(2000);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if (isLoad) {
                    loadData();
                    refreshlayout.finishLoadMore(2000,true,true);//传入false表示加载失败
                } else {
                    refreshlayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据
                }
            }
        });
    }

    private void loadData() {
        new GetSystemListAPI(SystemFrg.this);
        isLoad = false;
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

    /**
     * 获取知识体系的列表数据
     *
     * @param systemListModel
     */
    @Override
    public void getSystemListSuccess(SystemDataModel systemListModel) {
        systemList = systemListModel.getData();
        systemAdapter.setDataModelList(systemList);
        systemAdapter.notifyDataSetChanged();
    }

    @Override
    public void getSystemListError(long code, String msg) {

        TipUtils.showTip(msg);
    }
}
