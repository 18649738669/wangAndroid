package com.example.lilong.Content.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lilong.Content.Adapter.HomeAdapter;
import com.example.lilong.Content.Model.HomeArticleListModel;
import com.example.lilong.Content.Model.HomeBannerModel;
import com.example.lilong.Content.Net.GetArticleListAPI;
import com.example.lilong.Content.Net.GetBannerAPI;
import com.example.lilong.R;
import com.example.lilong.Tool.Activity.BasicFrg;
import com.example.lilong.Tool.Utils.TipUtils;
import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.RefreshViews.RefreshListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by long on 2018/06/08.
 * 首页页面
 */

public class HomeFrg extends BasicFrg implements GetBannerAPI.GetBannerAPIListener,
        GetArticleListAPI.GetArticleListAPIListener {


    Unbinder unbinder;
    @BindView(R.id.vp_home_rl)
    RecyclerView vpHomeRl;
    @BindView(R.id.vp_home_refreshLayout)
    SmartRefreshLayout refreshLayout;
    HomeAdapter homeAdapter;//首页适配器
    private List<HomeBannerModel.ObjBean> bannerList = new ArrayList<HomeBannerModel.ObjBean>();//banner列表
    private List<HomeArticleListModel.DataModel.ObjBean> articleList = new ArrayList<HomeArticleListModel.DataModel.ObjBean>();//文章列表
    private int pageIndex = 0;//当前页码
    private int pageCount = 0;//总页码
    public boolean isLoad = true;//是否允许上拉加载
    public HomeFrg() {
        super(R.layout.vp_frg_home);
    }

    @Override
    public void initView(View view) {

        new GetBannerAPI(this);
        initListView();

    }

    /**
     * 初始化首页数据列表
     */
    private void initListView() {
        loadData(pageIndex);
        homeAdapter = new HomeAdapter(getContext(), bannerList, articleList);
        vpHomeRl.setLayoutManager(new LinearLayoutManager(getContext()));
        vpHomeRl.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                new GetBannerAPI(HomeFrg.this);
                loadData(0);
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                if (isLoad) {
                    loadData(pageIndex + 1);
                    refreshlayout.finishLoadMore(2000);
                } else {
                    refreshlayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据
                }
            }
        });
    }

    private void loadData(final int tempPageIndex) {
        if (tempPageIndex == 0) {
            articleList.clear();
        }
        pageIndex = tempPageIndex;
        new GetArticleListAPI(HomeFrg.this, tempPageIndex);
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
     * 获取首页banner数据
     *
     * @param homeBannerModel
     */
    @Override
    public void getBannerSuccess(HomeBannerModel homeBannerModel) {
        bannerList = homeBannerModel.getData();
    }

    @Override
    public void getBannerError(long code, String msg) {

        TipUtils.showTip(msg);
    }

    /**
     * 分页获取首页文章列表数据
     *
     * @param homeArticleListModel
     */
    @Override
    public void getArticleListSuccess(HomeArticleListModel homeArticleListModel) {
        articleList = homeArticleListModel.getData().getDatas();

        homeAdapter.setBannerList(bannerList);
        if (pageIndex == 0){//是否是刷新首页数据
            homeAdapter.setArticleList(articleList,true);
        }else {
            homeAdapter.setArticleList(articleList,false);
        }
        homeAdapter.notifyDataSetChanged();
        pageCount = homeArticleListModel.getData().getPageCount();//总页数
        pageIndex = homeArticleListModel.getData().getCurPage();//当前页数
        if (pageIndex == pageCount) {
            isLoad = false;
        } else {
            isLoad = true;
        }
    }

    @Override
    public void getArticleListError(long code, String msg) {

        TipUtils.showTip(msg);
    }
}
