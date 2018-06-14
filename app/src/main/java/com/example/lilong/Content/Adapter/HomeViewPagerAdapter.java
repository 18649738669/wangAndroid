package com.example.lilong.Content.Adapter;

/**
 * Created by long on 2018/06/11.
 */

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lilong.Content.Model.HomeBannerModel;
import com.example.lilong.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.lilong.Tool.APP.App.getContext;

/**
 * viewpager 的适配器
 */
public class HomeViewPagerAdapter extends PagerAdapter {

    private List<HomeBannerModel.ObjBean> beans;

    //存放viewpager图片的数组
    List<ImageView> mList = new ArrayList<ImageView>();
    public HomeViewPagerAdapter(List<HomeBannerModel.ObjBean> bannerList){
        this.beans = bannerList;
    }

    public int getBannerSize(){
        return beans.size();
    }

    public void setBannerList(List<HomeBannerModel.ObjBean> bannerList){
        this.beans = bannerList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (beans.size() == 0){
            position = 0;
        }else {
            position = position % beans.size();
        }
        ImageView imageView = new ImageView(getContext());
        if (beans.size() != 0){
            Glide.with(getContext()).load(beans.get(position).getImagePath()).into(imageView);
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        mList.add(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (position < mList.size()){
            container.removeView(mList.get(position));
        }
    }
}