package com.example.lilong.Content.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lilong.Content.Activity.WebViewAct;
import com.example.lilong.Content.Model.HomeArticleListModel;
import com.example.lilong.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2018/06/11.
 * 知识体系下的文章列表适配器
 */

public class SystemArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<HomeArticleListModel.DataModel.ObjBean> articleList;//知识体系下的文章的list


    public SystemArticleAdapter(Context context, List<HomeArticleListModel.DataModel.ObjBean> articleList){
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.articleList = articleList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemArticleAdapter.ListViewHolder(mLayoutInflater.inflate(R.layout.item_home_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ListViewHolder){
            final HomeArticleListModel.DataModel.ObjBean article = articleList.get(position);
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
    public int getItemCount() {
        return articleList.size();
    }

    public void setArticleList(List<HomeArticleListModel.DataModel.ObjBean> articleList,boolean isRefresh){
        if (isRefresh){
            this.articleList = articleList;
        }else {
            this.articleList.addAll(articleList);
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

}
