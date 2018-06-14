package com.example.lilong.Tool.APP;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDexApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.lilong.R;
import com.facebook.stetho.Stetho;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.scwang.smartrefresh.layout.SmartRefreshLayout.*;

/**
 * Created by long on 2018-4-12.
 */
public class App extends MultiDexApplication {


    public static Context context;
    public static App app;
    public static RequestQueue requestQueue;
    public static Handler mainHandler;

    public static ImageLoader imageLoader;

    public static Handler getMainHandler(){
        return mainHandler;
    }

    public static Context getContext() {

        return context;
    }

    public static ImageLoader getImageLoader(){return imageLoader;}

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        mainHandler = new Handler();


//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(initConfiguration());

        /**
         * 初始化极光推送SDK
         */
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
//        builder.statusBarDrawable = R.mipmap.logo;
//        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
//        builder.notificationDefaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE ;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
//        JPushInterface.setPushNotificationBuilder(1, builder);

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        //测试
//        CrashReport.initCrashReport(getApplicationContext(), "238fbecfd1", true);

        //Stetho初始化
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );

        //设置可以访问Https
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null,null,null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60000L, TimeUnit.MILLISECONDS)
                .readTimeout(60000L, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    public ImageLoaderConfiguration initConfiguration(){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(2)
                .memoryCache(new WeakMemoryCache())
                .build();

        return configuration;
    }


    public static App getApp(){
        if(app == null){
            synchronized (App.class){
                if(app == null){
                    app = new App();
                }
            }
        }
        return app;
    }


    /**
     * 获取网络请求的消息队列
     * @return
     */
    public static RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.common_blue, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

}
