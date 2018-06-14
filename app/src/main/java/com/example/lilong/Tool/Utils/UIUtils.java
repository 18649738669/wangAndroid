package com.example.lilong.Tool.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import com.example.lilong.Tool.APP.App;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by long on 2018-4-12.
 * 功能：UI操作的工具类
 */
public class UIUtils {

    /**
     * 根据手机分辨率从dp转化为px单位
     *
     * @param dp
     * @return
     */
    public static int dp2Px(double dp) {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 根据手机分辨率从px转化为dp单位
     *
     * @param px
     * @return
     */
    public static int px2Dp(int px) {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将sp转化为px的单位
     *
     * @param sp
     * @return
     */
    public static int sp2px(double sp) {
        final float scale = App.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (scale * sp + 0.5f);
    }

    /**
     * 执行延时任务
     *
     * @param task
     * @param delayedTime
     */
    public static void postDelayed(Runnable task, int delayedTime) {
        App.getMainHandler().postDelayed(task, delayedTime);
    }

    /**
     * 删除指定的Runnable对象
     *
     * @param task
     */
    public static void removeCallbacks(Runnable task) {
        App.getMainHandler().removeCallbacks(task);
    }


    /**
     * 设置窗体的透明度
     *
     * @param activity
     * @param alpha
     */
    public static void setWindowAlpha(Activity activity, float alpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams w_lp = window.getAttributes();
        w_lp.alpha = alpha;
        window.setAttributes(w_lp);
    }


    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        // 设置progressDialog风格
        progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);//圆形
        progressDialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);//水平
        // 设置mProgressDialog提示
        progressDialog.setMessage("加载中...");
        // 设置在点击Dialog外是否取消Dialog进度条
        progressDialog.setCanceledOnTouchOutside(true);
        // 是否可以按回退键取消
        progressDialog.setCancelable(true);
        return progressDialog;
    }

    public static void closeProgressDialog(Activity activity, ProgressDialog progressDialog) {

        if (progressDialog != null && !activity.isFinishing()) {
            progressDialog.dismiss();
        }

    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getWinWidth() {
        WindowManager windowManager = (WindowManager) App.getContext().getSystemService(Context
                .WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getWinHeight() {
        WindowManager windowManager = (WindowManager) App.getContext().getSystemService(Context
                .WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }

    /**
     * 设置屏幕亮度
     *
     * @param paramInt
     */
    public static void saveScreenBrightness(int paramInt) {
        try {
            Settings.System.putInt(App.getContext().getContentResolver(), Settings.System
                    .SCREEN_BRIGHTNESS, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    /**
     * 获取当前屏幕亮度
     *
     * @return
     */
    public static int getScreenBrightness() {
        int screenBrightness = 255;
        try {
            screenBrightness = Settings.System.getInt(App.getContext().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception localException) {

        }
        return screenBrightness;
    }

    /**
     * 唤醒屏幕及解锁
     */
    public static void wakeUpAndUnlock() {
        KeyguardManager km = (KeyguardManager) App.getContext().getSystemService(Context
                .KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unlock");

        kl.disableKeyguard();  //解锁

        PowerManager pm = (PowerManager) App.getContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock pw = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");

        pw.acquire();   //点亮屏幕
        pw.release();   //释放资源

    }


    /**
     * 获取版本号
     *
     * @return
     */
    public static String getVersion() {
        try {
            PackageManager manager = App.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(App.getContext().getPackageName(), 0);
            String version = info.versionName;
            LogUtils.d("当前版本--》" + version);
            return version;

        } catch (Exception e) {
            return "0";
        }
    }


    /**
     * 比较app版本
     *
     * @param versionServer
     * @param versionLocal
     * @return 1表示有新版本，0表示当前已经是最新版本，-1表示其他情况
     */
    public static int VersionComparison(String versionServer, String versionLocal) {
        String version1 = versionServer;
        String version2 = versionLocal;
        if (version1 == null || version1.length() == 0 || version2 == null || version2.length() == 0)
            throw new IllegalArgumentException("Invalid parameter!");

        int index1 = 0;
        int index2 = 0;
        while (index1 < version1.length() && index2 < version2.length()) {
            int[] number1 = getValue(version1, index1);
            int[] number2 = getValue(version2, index2);

            if (number1[0] < number2[0]) {

                return -1;
            } else if (number1[0] > number2[0]) {
                return 1;
            } else {
                index1 = number1[1] + 1;
                index2 = number2[1] + 1;
            }
        }
        if (index1 == version1.length() && index2 == version2.length())
            return 0;
        if (index1 < version1.length())
            return 1;
        else
            return -1;
    }


    /**
     * 将版本号转化为数字
     *
     * @param version
     * @param index
     * @return
     */
    public static int[] getValue(String version, int index) {
        int[] value_index = new int[2];
        StringBuilder sb = new StringBuilder();
        while (index < version.length() && version.charAt(index) != '.') {
            sb.append(version.charAt(index));
            index++;
        }
        value_index[0] = Integer.parseInt(sb.toString());
        value_index[1] = index;

        return value_index;
    }

    /**
     * 打开已经安装好的apk
     */
    public static void openApk(Context context, String url) {
        PackageManager manager = context.getPackageManager();
        // 这里的是你下载好的文件路径
        PackageInfo info = manager.getPackageArchiveInfo(Environment.getExternalStorageDirectory().getAbsolutePath()
                + url, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            Intent intent = manager.getLaunchIntentForPackage(info.applicationInfo.packageName);
            App.getContext().startActivity(intent);
        }
    }

    public static boolean isServiceWorked(String className) {
        ActivityManager myManager = (ActivityManager) App.getContext().getSystemService(
                Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(Integer.MAX_VALUE);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName()
                    .equals(className)) {
                return true;
            }
        }
        return false;
    }

//    public static void startPhoneService(Context context) {
//        if (!UIUtils.isServiceWorked(PhoneStateService.class.getCanonicalName())) {
//            Intent PhoneStateService = new Intent(context, PhoneStateService.class);
//            App.getContext().startService(PhoneStateService);
//        }
//    }

    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public static int getAppSatus(Context context, String pageName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }

    public static String getEMUI() {
        Class<?> classType = null;
        String buildVersion = null;
        try {
            classType = Class.forName("android.os.SystemProperties");
            Method getMethod = classType.getDeclaredMethod("get", new Class<?>[]{String.class});
            buildVersion = (String) getMethod.invoke(classType, new Object[]{"ro.build.version.emui"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildVersion;
    }

}