package com.example.lilong.Content.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lilong.Content.Activity.WebViewAct;
import com.example.lilong.Content.Model.HomeArticleListModel;
import com.example.lilong.Content.Model.HomeBannerModel;
import com.example.lilong.R;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2018/06/11.
 * 首页列表适配器
 */

public class HomeAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_VIEWPAGER,
        ITEM_TYPE_LISTVIEW
    }

    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<HomeBannerModel.ObjBean> bannerList;//首页banner的list
    private List<HomeArticleListModel.DataModel.ObjBean> articleList;//首页文章的list
    //当前索引位置
    int index = 0;
    //是否需要轮播标志
    boolean isContinue = true;
    //定时器，用于实现轮播
    Timer timer;

    ViewPager homeViewPager;
    TextView homeViewPagerTitle;
    TextView homeViewPagerNumber;
    public HomeViewPagerAdapter pagerAdapter;

    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    index++;
                    if (homeViewPager != null)
                        homeViewPager.setCurrentItem(index);
            }
        }
    };

    public HomeAdapter(Context context,List<HomeBannerModel.ObjBean> bannerList,
                       List<HomeArticleListModel.DataModel.ObjBean> articleList){
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.bannerList = bannerList;
        this.articleList = articleList;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HomeAdapter.ITEM_TYPE.ITEM_TYPE_VIEWPAGER.ordinal()) {
            return new HomeAdapter.ViewPagerHolder(mLayoutInflater.inflate(R.layout.item_home_viewpager, parent, false));
        }else {
            return new HomeAdapter.ListViewHolder(mLayoutInflater.inflate(R.layout.item_home_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewPagerHolder){
            homeViewPager = ((ViewPagerHolder) holder).homeViewPager;
            homeViewPagerTitle = ((ViewPagerHolder) holder).homeViewPagerTitle;
            homeViewPagerNumber = ((ViewPagerHolder) holder).homeViewPagerNumber;
            setBannerAdpater();
        }else if (holder instanceof ListViewHolder){
            final HomeArticleListModel.DataModel.ObjBean article = articleList.get(position - 1);
            ((ListViewHolder) holder).homeListViewTitle.setText(article.getTitle());
            ((ListViewHolder) holder).homeListViewImage.setImageResource(R.mipmap.icon_author);
//            Glide.with(getContext()).load(article.getLink()).into(((ListViewHolder) holder).homeListViewImage);
            ((ListViewHolder) holder).homeListViewAuthor.setText("作者：" + article.getAuthor());
            ((ListViewHolder) holder).homeListViewTime.setText("时间：" + article.getNiceDate());
            ((ListViewHolder) holder).homeListViewType.setText("分类：" + article.getType());
            ((ListViewHolder) holder).homeLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebViewAct.startActivity(context,article.getTitle(),article.getLink());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return ITEM_TYPE.ITEM_TYPE_VIEWPAGER.ordinal();
            default:
                return ITEM_TYPE.ITEM_TYPE_LISTVIEW.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size() + 1;
    }

    public void setBannerList(List<HomeBannerModel.ObjBean> bannerList){
        this.bannerList = bannerList;
        setBannerAdpater();
    }

    public void setBannerAdpater(){
        if (pagerAdapter == null && bannerList.size() > 0){
            pagerAdapter = new HomeViewPagerAdapter(context,bannerList);
            homeViewPager.setAdapter(pagerAdapter);
            homeViewPager.addOnPageChangeListener(onPageChangeListener);
            timer = new Timer();//创建Timer对象
            //执行定时任务
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //首先判断是否需要轮播，是的话我们才发消息
                    if (isContinue) {
                        mHandler.sendEmptyMessage(1);
                    }
                }
            }, 5000, 5000);//延迟5秒，每隔5秒发一次消息
        }else {
            if (pagerAdapter != null && pagerAdapter.getBannerSize() == 0){
                pagerAdapter.setBannerList(bannerList);
            }
        }
    }

    public void setArticleList(List<HomeArticleListModel.DataModel.ObjBean> articleList,boolean isRefresh){
        if (isRefresh){
            this.articleList = articleList;
        }else {
            this.articleList.addAll(articleList);
        }
    }

    public static class ViewPagerHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.home_item_vp)
        ViewPager homeViewPager;
        @BindView(R.id.home_item_vp_title)
        TextView homeViewPagerTitle;
        @BindView(R.id.home_item_vp_number)
        TextView homeViewPagerNumber;

        ViewPagerHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.home_item_lv_image)
        RoundedImageView homeListViewImage;
        @BindView(R.id.home_item_lv_title)
        TextView homeListViewTitle;
        @BindView(R.id.home_item_lv_author)
        TextView homeListViewAuthor;
        @BindView(R.id.home_item_lv_time)
        TextView homeListViewTime;
        @BindView(R.id.home_item_lv_type)
        TextView homeListViewType;
        @BindView(R.id.home_item_ll)
        LinearLayout homeLl;

        ListViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    /**
     * 根据当前选中的页面设置对应的标题以及序号
     */
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            index = position;//当前位置赋值给索引
            int pos = 0;
            if(bannerList.size() != 0){
                pos = index % bannerList.size();
            }

            /**
             * 设置对应位置的标题以及序号
             *
             * @param i 当前位置
             */

            homeViewPagerTitle.setText(bannerList.get(pos).getTitle());
            homeViewPagerNumber.setText(pos + "/" + bannerList.size());

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
