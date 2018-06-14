package com.example.lilong.Content.Utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by long on 2018-4-12.
 * 功能：活动管理器，管理Activity的添加和移除
 */
public class ActivityCollector {

    public static List<Activity> activityList = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 回到首页
     */
    public static void goBackHomeAct() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing() &&
                    !"road.controller.activity.HomeAct".equals(activity.getLocalClassName())) {
                activity.finish();
            }
        }
    }

    /**
     * 回到取消原因页面
     */
    public static void goBackReasonAct() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing() && !"road.controller.activity.TaskCancelReasonAct".equals(activity.getLocalClassName()) &&
                    !"road.controller.activity.HomeAct".equals(activity.getLocalClassName())) {
                activity.finish();
            }
        }
    }


    public static boolean isFinishAll() {
        boolean isFinish = true;

        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                isFinish = false;
            }
        }

        return isFinish;
    }


}
