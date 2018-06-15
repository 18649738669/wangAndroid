package com.example.lilong.Content.Utils;

import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by long on 2018/05/08.
 * 为webview 配置
 */

public class WebViewSetting {

    public void setSetting(final WebView webView){
        final WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);  //开启javascript
        webSettings.setDomStorageEnabled(true);  //开启DOM
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码
        // web页面处理
        webSettings.setAllowFileAccess(true);// 支持文件流
        //支持获取手势焦点，输入用户名、密码或其他
        webView.requestFocusFromTouch();
        webSettings.setUseWideViewPort(true);// 调整到适合webview大小
        webSettings.setLoadWithOverviewMode(true);//调整到适合webview大小
        webSettings.setSupportZoom(false);  //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。
        webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //设置无缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        //在当前webview中返回
        webView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });
        //在Android 5.0上 允许加载 Http 与 Https 混合内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
}
