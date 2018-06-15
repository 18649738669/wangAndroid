package com.example.lilong.Content.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.lilong.Content.Adapter.SystemTypeAdapter;
import com.example.lilong.Content.Model.SystemDataModel;
import com.example.lilong.R;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.Activity.BasicAct;
import com.example.lilong.Tool.DB.SPHelper;
import com.example.lilong.Tool.Utils.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2018/06/15.
 * 知识体系分类页面
 */

public class SystemTypeAct extends BasicAct {


    @BindView(R.id.tool_bar_title)
    TextView title;
    @BindView(R.id.system_type_list_rl)
    RecyclerView systemTypeListRl;


    SystemTypeAdapter systemTypeAdapter;
    private List<SystemDataModel.ObjBean.Children> childrenList = new ArrayList<SystemDataModel.ObjBean.Children>();

    public SystemTypeAct() {
        super(R.layout.act_system_type_list, R.string.title_activity_system_type, true, TOOLBAR_TYPE_DEFAULT, R.color.common_blue);

    }

    public static void startActivity(Context context, int index) {
        Intent intent = new Intent(context, SystemTypeAct.class);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        int index = getIntent().getIntExtra("index",0);
        String str = SPHelper.getString(Builds.SP_USER,"SystemDataModel");
        SystemDataModel systemDataModel = JSONUtils.parseJson(str,SystemDataModel.class);
        childrenList = systemDataModel.getData().get(index).getChildren();
        title.setText(systemDataModel.getData().get(index).getName());
        systemTypeAdapter = new SystemTypeAdapter(this, childrenList);
        systemTypeListRl.setLayoutManager(new LinearLayoutManager(this));
        systemTypeListRl.setAdapter(systemTypeAdapter);
        systemTypeAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
