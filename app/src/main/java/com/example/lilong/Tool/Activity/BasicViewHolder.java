package com.example.lilong.Tool.Activity;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by long on 2018-4-12.
 * 功能：自定义ViewHolder
 */
public class BasicViewHolder {

    /**
     * 稀疏数组，存放view
     */
    private SparseArray<View> viewHoler;
    private View view;

    /**
     * 私有构造方法，初始化ViewHolder，并将其设置为view的tag
     * @param view
     */
    private BasicViewHolder(View view){
        this.view = view;
        viewHoler = new SparseArray<View>();
        view.setTag(viewHoler);
    }


    public static BasicViewHolder getViewHoler(View view) {
        BasicViewHolder basicViewHolder = (BasicViewHolder)view.getTag();

        if(basicViewHolder == null){

            basicViewHolder = new BasicViewHolder(view);

            view.setTag(basicViewHolder);
        }
        return basicViewHolder;
    }

    /**
     * 根据资源ID查找ViewHolder中的组件
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View>T get(int id){
        View childView = viewHoler.get(id);
        if(childView == null){
            childView = view.findViewById(id);
            viewHoler.put(id, childView);
        }
        return (T)childView;
    }


    /**
     * 根据资源ID查找RelativeLayout
     * @param id
     * @return
     */
    public RelativeLayout getRelativeLayout(int id){
        return get(id);
    }

    /**
     * 根据资源ID查找LinearLayout
     * @param id
     * @return
     */
    public LinearLayout getLinearLayout(int id){
        return get(id);
    }

    /**
     * 根据资源ID查找TextView
     * @param id
     * @return
     */
    public TextView getTextView(int id){
        return get(id);
    }

    /**
     * 根据资源ID查找ImageView
     * @param id
     * @return
     */
    public ImageView getImageView(int id){
        return get(id);
    }

    /**
     * 根据资源ID查找ListView
     * @param id
     * @return
     */
    public ListView getListView(int id){
        return get(id);
    }

    /**
     * 根据资源ID查找Button
     * @param id
     * @return
     */
    public Button getButton(int id){
        return get(id);
    }
}
