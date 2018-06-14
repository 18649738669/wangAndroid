package com.example.lilong.Content.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.lilong.Content.CustomUi.NoScrollViewPager;
import com.example.lilong.Content.Fragment.HomeFrg;
import com.example.lilong.Content.Fragment.MyFrg;
import com.example.lilong.Content.Fragment.SystemFrg;
import com.example.lilong.R;
import com.example.lilong.Tool.Activity.BasicAct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by long on 2018/06/08.
 * 主页面
 */

public class HomeAct extends BasicAct {

    @BindView(R.id.tool_bar_friends_circle_title)
    TextView title;
    @BindView(R.id.home_vp)
    NoScrollViewPager homeVp;
    @BindView(R.id.home_tv_home)
    TextView tvHome;
    @BindView(R.id.home_tv_system)
    TextView tvSystem;
    @BindView(R.id.home_tv_my)
    TextView tvMy;

    HomeFrg homeFrg;
    SystemFrg systemFrg;
    MyFrg myFrg;
    List<Fragment> pagerViewList = new ArrayList<Fragment>(); //所需展示的页面集合
    int vp_index = 0;//当前加载的页面下标

    public HomeAct() {
        super(R.layout.act_home, R.string.title_activity_home, false, TOOLBAR_TYPE_NO_TOOLBAR, R.color.common_blue);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, HomeAct.class);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        homeFrg = new HomeFrg();
        systemFrg = new SystemFrg();
        myFrg = new MyFrg();
        pagerViewList.add(homeFrg);
        pagerViewList.add(systemFrg);
        pagerViewList.add(myFrg);
        homeVp.setAdapter(new PagerAdater(getSupportFragmentManager()));
        homeVp.setCurrentItem(vp_index);
        homeVp.setNoScroll(true);//禁止ViewPager左右滑动
        setTvHome(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tool_bar_friends_circle_right_text, R.id.home_tv_home, R.id.home_tv_system, R.id.home_tv_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tool_bar_friends_circle_right_text:
                break;
            case R.id.home_tv_home:
                setTvHome(true);
                setTvSystem(false);
                setTvMy(false);
                vp_index = 0;
                homeVp.setCurrentItem(vp_index);
                break;
            case R.id.home_tv_system:
                setTvHome(false);
                setTvSystem(true);
                setTvMy(false);
                vp_index = 1;
                homeVp.setCurrentItem(vp_index);
                break;
            case R.id.home_tv_my:
                setTvHome(false);
                setTvSystem(false);
                setTvMy(true);
                vp_index = 2;
                homeVp.setCurrentItem(vp_index);
                break;
        }
    }

    /**
     * 设置home的文字颜色以及图片
     * @param bool
     */
    public void setTvHome(boolean bool){
        Drawable img;
        if (bool){
            tvHome.setTextColor(Color.argb(255,0,170,239));
            img = getResources().getDrawable(R.drawable.home2);
            title.setText(R.string.title_activity_home);
        }else {
            tvHome.setTextColor(Color.LTGRAY);
            img = getResources().getDrawable(R.drawable.home);
        }
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
        tvHome.setCompoundDrawables(null, img, null, null); //设置上图标
    }
    /**
     * 设置system的文字颜色以及图片
     * @param bool
     */
    public void setTvSystem(boolean bool){
        Drawable img;
        if (bool){
            tvSystem.setTextColor(Color.argb(255,0,170,239));
            img = getResources().getDrawable(R.drawable.serve2);
            title.setText(R.string.title_activity_system);
        }else {
            tvSystem.setTextColor(Color.LTGRAY);
            img = getResources().getDrawable(R.drawable.serve);
        }
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
        tvSystem.setCompoundDrawables(null, img, null, null); //设置上图标
    }
    /**
     * 设置my的文字颜色以及图片
     * @param bool
     */
    public void setTvMy(boolean bool){
        Drawable img;
        if (bool){
            tvMy.setTextColor(Color.argb(255,0,170,239));
            img = getResources().getDrawable(R.drawable.my2);
            title.setText(R.string.title_activity_my);
        }else {
            tvMy.setTextColor(Color.LTGRAY);
            img = getResources().getDrawable(R.drawable.my);
        }
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
        tvMy.setCompoundDrawables(null, img, null, null); //设置上图标
    }

    /**
     * viewpager 的适配器
     */
    class PagerAdater extends FragmentPagerAdapter {


        public PagerAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pagerViewList.get(position);
        }

        @Override
        public int getCount() {
            return pagerViewList.size();
        }
    }
}
