package com.example.lilong.Content.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.lilong.Content.Utils.WebViewSetting;
import com.example.lilong.R;
import com.example.lilong.Tool.Activity.BasicAct;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2018/06/15.
 */

public class WebViewAct extends BasicAct {


    @BindView(R.id.tool_bar_title)
    TextView title;
    @BindView(R.id.webview)
    WebView webview;

    public WebViewAct() {
        super(R.layout.act_webview, R.string.title_activity_webview, true, TOOLBAR_TYPE_DEFAULT, R.color.common_blue);
    }

    public static void startActivity(Context context,String title,String url) {
        Intent intent = new Intent(context, WebViewAct.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        String url = getIntent().getStringExtra("url");
        title.setText(getIntent().getStringExtra("title"));
        webview.requestFocus();//请求获得焦点
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        new WebViewSetting().setSetting(webview);
        //加载需要显示的网页
        webview.loadUrl(url);
        //设置Web视图
        webview.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
