package com.example.lilong.Tool.Utils;

import com.facebook.stetho.common.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 作者： huangRZ  日期： 2017/2/28. email：917647409@qq.com
 * Explain：
 */

public class DateUtils {

    private static String year_str;
    private static String year_month_str;
    private static String year_month_day_str;
    private static String year_month_day_hour;
    private static String month_str;
    private static String day_str;
    private static String hour_str;

    //获取 YYYY 字符串
    public static String getCurrYearString() {
        subCurrTimeString();
        return year_str;
    }

    //获取 YYYY-MM 字符串
    public static String getCurrYearMonthString() {
        subCurrTimeString();
        return year_month_str;
    }

    //获取 YYYY-MM-DD 字符串
    public static String getCurrYearMonthDayString() {
        subCurrTimeString();
        return year_month_day_str;
    }

    //获取 YYYY-MM-DD HH 字符串
    public static String getCurrYearMonthDayHourString() {
        subCurrTimeString();
        return year_month_day_hour;
    }

    //取 MM
    public static String getCurrMonthString() {
        subCurrTimeString();
        return month_str;
    }

    //取 DD
    public static String getCurrDayString() {
        subCurrTimeString();
        return day_str;
    }

    //取 HH
    public static String getCurrHourString() {
        subCurrTimeString();
        return hour_str;
    }

    //拼接字符串
    private static void subCurrTimeString() {

        String date_str = getCurrentTime();

        //取 年
        year_str = date_str.substring(0, date_str.indexOf("-"));

        // 取月
        month_str = date_str.substring(date_str.indexOf("-") + 1, date_str.indexOf("-") + 3);

        //取 日
        day_str = date_str.substring(date_str.lastIndexOf("-") + 1, date_str.lastIndexOf("-") + 3);

        //取 时
        hour_str = date_str.substring(date_str.indexOf(":") - 2, date_str.indexOf(":"));

        //取 年-月
        year_month_str = date_str.substring(0, date_str.indexOf("-") + 3);

        //取 年-月-日
        year_month_day_str = date_str.substring(0, date_str.lastIndexOf("-") + 3);

        //取 年-月-日 小时
        year_month_day_hour = date_str.substring(0, date_str.indexOf(":"));
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E", Locale.CHINA);
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }

    /***********************************************************************************************/

    public static String getDayFromDate(String date) {
        return date.substring(date.lastIndexOf("-") + 1);
    }

    public static String getHourFromDate(String date) {
        return date.substring(date.lastIndexOf(" ") + 1);
    }

    /***********************************************************************************************/

    //返回 当前日 往后 第七天的日期，即一周前的日期（包括当前日）  格式：YYYY-MM-DD
    public static String getWeekStartDayToCurrDay() {

        if (Integer.valueOf(getCurrDayString()) < 7) {
            int lastMonth = Integer.valueOf(getCurrMonthString()) - 1;
            int daysOfLastMonth = getDaysByYearMonth(Integer.valueOf(getCurrYearString()), lastMonth);
            if (lastMonth < 10) {
                return getCurrYearString() + "-0" + lastMonth + "-" + String.valueOf(daysOfLastMonth - 6 + Integer.valueOf(getCurrDayString()));
            } else {
                return getCurrYearString() + "-" + lastMonth + "-" + String.valueOf(daysOfLastMonth - 6 + Integer.valueOf(getCurrDayString()));
            }
        } else {
            int dayOffset = Integer.valueOf(getCurrDayString()) - 6;
            if (dayOffset < 10) {
                return getCurrYearMonthString() + "-0" + dayOffset;
            } else {
                return getCurrYearMonthString() + "-" + dayOffset;
            }
        }
    }

    //获取 当前日 前七天（包括当前日） 日期数组  用作 图表的X轴显示
    public static String[] getWeekDaysToCurrDay() {
        String[] days = new String[7];
        int currDay;
        if (Integer.valueOf(getCurrDayString()) < 7) {
            int lastMonth = Integer.valueOf(getCurrMonthString()) - 1;
            int daysOfLastMonth = getDaysByYearMonth(Integer.valueOf(getCurrYearString()), lastMonth);
            currDay = Integer.valueOf(getCurrDayString());

            for (int i = 0; currDay - i > 0; i++){
//                if (i == currDay - 1) {
//                    days[6 - i] = Integer.valueOf(getCurrMonthString()) + "/" + (currDay - i);
//                } else {
                    days[6 - i] = "" + (currDay - i);
//                }
            }
            for (int i = 0; i + daysOfLastMonth - 6 + currDay <= daysOfLastMonth; i++) {
//                if (i == 0) {
//                    days[i] = lastMonth + "/" + (i + daysOfLastMonth - 6 + currDay) + "";
//                } else {
                    days[i] = i + daysOfLastMonth - 6 + currDay + "";
//                }
            }

            LogUtil.e("days : " + Arrays.toString(days));

        } else {
            int days_start = Integer.valueOf(getCurrDayString()) - 6;
            for (int i = 0; i < 7; i++) {
//                if (i == 0) {
//                    days[i] = Integer.valueOf(getCurrMonthString()) + "/" + (days_start + i);
//                } else {
                    days[i] = "" + (days_start + i);
//                }
            }
        }
        return days;
    }

    /*************************************************************************************************/

}
