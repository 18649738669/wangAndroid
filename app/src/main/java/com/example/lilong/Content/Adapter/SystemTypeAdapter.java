package com.example.lilong.Content.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lilong.Content.Activity.SystemArticleAct;
import com.example.lilong.Content.Model.SystemDataModel;
import com.example.lilong.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2018/06/13.
 * 知识体系的分类列表适配器
 */

public class SystemTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<SystemDataModel.ObjBean.Children> dataModelList;

    public SystemTypeAdapter(Context context, List<SystemDataModel.ObjBean.Children> dataModelList){
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.dataModelList = dataModelList;
    }

    public void setDataModelList(List<SystemDataModel.ObjBean.Children> dataModelList){
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemTypeAdapter.SystemHolder(mLayoutInflater.inflate(R.layout.item_system_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof SystemHolder){

            final SystemDataModel.ObjBean.Children children = dataModelList.get(position);
            ((SystemHolder) holder).systemTitle.setText(children.getName());
            ((SystemHolder) holder).systemContent.setVisibility(View.GONE);
            ((SystemHolder) holder).systemLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SystemArticleAct.startActivity(context,children.getId());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public static class SystemHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.system_item_lv_title)
        TextView systemTitle;
        @BindView(R.id.system_item_lv_content)
        TextView systemContent;
        @BindView(R.id.system_item_lv_ll)
        LinearLayout systemLl;

        SystemHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
