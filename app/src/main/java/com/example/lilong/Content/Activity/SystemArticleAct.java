package com.example.lilong.Content.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.lilong.Content.Adapter.SystemArticleAdapter;
import com.example.lilong.Content.Model.HomeArticleListModel;
import com.example.lilong.Content.Net.GetSystemArticleListAPI;
import com.example.lilong.R;
import com.example.lilong.Tool.Activity.BasicAct;
import com.example.lilong.Tool.Utils.TipUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2018/06/15.
 * 知识体系下的文章列表
 */

public class SystemArticleAct extends BasicAct implements GetSystemArticleListAPI.GetSystemArticleListAPIListener {


    @BindView(R.id.tool_bar_title)
    TextView title;
    @BindView(R.id.system_article_list_rl)
    RecyclerView systemArticleListRl;
    @BindView(R.id.system_article_list_refreshLayout)
    SmartRefreshLayout refreshLayout;

    SystemArticleAdapter systemArticleAdapter;
    private List<HomeArticleListModel.DataModel.ObjBean> articleList = new ArrayList<HomeArticleListModel.DataModel.ObjBean>();//文章列表
    private int pageIndex = 0;//当前页码
    private int pageCount = 0;//总页码
    private int id = 0;
    public boolean isLoad = true;//是否允许上拉加载


    public SystemArticleAct() {
        super(R.layout.act_system_article_list, R.string.title_activity_system_article, true, TOOLBAR_TYPE_DEFAULT, R.color.common_blue);
    }

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, SystemArticleAct.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id",0);
        initListView();
    }

    /**
     * 初始化首页数据列表
     */
    private void initListView() {
        loadData(pageIndex);
        systemArticleAdapter = new SystemArticleAdapter(this, articleList);
        systemArticleListRl.setLayoutManager(new LinearLayoutManager(this));
        systemArticleListRl.setAdapter(systemArticleAdapter);
        systemArticleAdapter.notifyDataSetChanged();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
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
        new GetSystemArticleListAPI(this, tempPageIndex,id);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 获取知识体系下的文章列表
     *
     * @param homeArticleListModel
     */
    @Override
    public void getSystemArticleListSuccess(HomeArticleListModel homeArticleListModel) {
        articleList = homeArticleListModel.getData().getDatas();
        if (pageIndex == 0){//是否是刷新首页数据
            systemArticleAdapter.setArticleList(articleList,true);
        }else {
            systemArticleAdapter.setArticleList(articleList,false);
        }
        systemArticleAdapter.notifyDataSetChanged();
        pageCount = homeArticleListModel.getData().getPageCount();//总页数
        pageIndex = homeArticleListModel.getData().getCurPage();//当前页数
        if (pageIndex == pageCount) {
            isLoad = false;
        } else {
            isLoad = true;
        }
    }

    @Override
    public void getSystemArticleListError(long code, String msg) {

        TipUtils.showTip(msg);
    }
}
