package com.example.lilong.Tool.Utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.example.lilong.Tool.APP.App;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.DB.SPHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by long on 2018-4-12.
 * 功能：字符串操作类
 */
public class StringUtils {

    /**
     * 清除小数点后面多余的零
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 在字符串中指定位置添加字符串
     * @param str
     * @param appendStr
     * @param startAppend
     * @return
     */
    public static String appendString(String str, CharSequence appendStr, int startAppend){

        String str1 = str.substring(0, startAppend);
        String str2 = str.substring(startAppend,str.length());
        return str1 + " " + appendStr + " " + str2;
    }

    /**
     * 在字符串中指定位置添加高亮的字符串
     * @param color  高亮的颜色
     * @param str  待添加的字符串
     * @param appendStr  添加的字符串
     * @param startAppend  添加的起始位置
     * @return
     */
    public static CharSequence appendSpanString(int color, String str, String appendStr, int startAppend){
        String appendResult = appendString(str, appendStr, startAppend);
        return SpanStringUtils.getHightLightText(color,appendResult,appendStr);
    }


    /**
     * MD5加密
     * @param str 要加密的字符串
     * @return
     */
    public static String string2MD5(String str) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }


    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {

        if (str != null) {
            str = str.trim();
        }
        return TextUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     *
     * @param str
     * @return
     */
    public static boolean isNoEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 获取时间戳
     * @return
     */
    public static long getCurrentTimeStamp(){
        long timeStamp = System.currentTimeMillis() + SPHelper.getLong(Builds.SP_USER,"checkTime");
        return timeStamp;
    }

    /**
     * 获取手机设备号
     * @return
     */
    public static String getIMEI(Context context){
        String deviceId = "";
        try{
            TelephonyManager tManager = (TelephonyManager) App.getContext().getSystemService(Context
                    .TELEPHONY_SERVICE);
            deviceId = tManager.getDeviceId();
        }catch (SecurityException e){
            TipUtils.showTip("请前往设置中心打开电话的权限");
            // 启动应用的设置
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);

        }
        return deviceId;
    }

    /**
     * 根据秒数获取时间
     * 时间格式为 ----- 时：分：秒
     * @param time
     * @return
     */
    public static String getTimeFromSecond(long time){
        int ss = 1 ;
        int mm = ss * 60 ;
        int hh = mm * 60 ;
        int dd = hh * 24 ;

        long day = time / dd;
        long hour = (time - day * dd) / hh ;
        long minute = (time - day * dd - hour * hh) / mm;
        long second = (time - day * dd - hour * hh - minute * mm) /ss ;

        String hourStr = hour < 10 ? "0" + hour : "" + hour;
        String minuteStr = minute < 10 ? "0" + minute : "" + minute;
        String secondStr = second < 10 ? "0" + second : "" + second;

        return hourStr + ":" + minuteStr + ":" + secondStr ;

    }


    /**
     * 根据毫米数获取时间
     * 时间格式为 ---- 年/月/日 时：分
     * @param time
     * @return
     */
    public static String getDateFromMileSecond1(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = format.format(new Date(time));
        return date;
    }

    /**
     * 根据毫米数获取时间
     * 时间格式为 ---- 年/月/日 时：分
     * @param time
     * @return
     */
    public static String getDateFromMileSecond(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String date = format.format(new Date(time));
        return date;
    }


    /**
     * 根据毫米数获取时间
     * 时间格式为 ---- 年/月/日 时：分
     * @param time
     * @return
     */
    public static String getDate(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String date = format.format(new Date(time));
        return date;
    }

    /**
     * 根据毫米数获取时间
     * 时间格式为 ---- 年/月/日 时：分
     * @param time
     * @return
     */
    public static String getTimeFromMileSecond(long time){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String date = format.format(new Date(time));
        return date;
    }

    /**
     * 根据毫秒数获取年月日
     * 时间格式为 ---- 年/月/日
     * @param time
     * @return
     */
    public static String getYearMonthDay(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(time));
        return date;
    }


    /**
     * 根据毫秒数获取年月
     * 时间格式为 ---- 年/月
     * @param time
     * @return
     */
    public static String getYearMonth(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String date = format.format(new Date(time));
        return date;
    }


    /**
     * 根据毫秒数获取时分
     * 时间格式为 ---- 时：分
     * @param time
     * @return
     */
    public static String getHourSecond(long time){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String date = format.format(new Date(time));
        return date;
    }

    /**
     * 获取日期
     * @param time
     * @return
     */
    public static String getDayFromMileSecond(long time){
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String date = format.format(new Date(time));
        return date;
    }

    /**
     * 获取当前的时间
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String date = format.format(new Date());
        return date;
    }

    /**
     * 获取当前年份
     * @return
     */
    public static String getCurrentYear(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String date = format.format(new Date());
        return date ;
    }


    /**
     * 获取当前月份
     * @return
     */
    public static String getCurrentMonth(){
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String date = format.format(new Date());
        return date ;
    }

    /**
     * 获取当前日
     * @return
     */
    public static String getCurrentDay(){
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String date = format.format(new Date());
        return date ;
    }

    /**
     * 根据当前的时间对图片进行命名
     * @return
     */
    public static String getImageNameFromDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String name = format.format(new Date());
        return name ;
    }

    /**
     * 获取每月第一天是星期几
     * @return 1-7，1是星期天，7是星期六
     */
    public static String getFirstMonthDay(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month - 1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * 根据年份和月份获取该月份有几天
     * @param year
     * @param month
     * @return
     */
    public static int getMonthAllDay(int year,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month - 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取某年某月某一天的时间戳
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long getDayTimestamp(int year,int month,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month - 1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        return calendar.getTimeInMillis();
    }


    /**
     * 将不是整数或浮点数的字符串过滤
     * @param str
     * @return
     */
    public static float stringToFloat(String str){
        str = stringNoNull(str);
        if ("".equals(str)){
            return 0.00f;
        }
        //正则表达式，整数或浮点数
        String regex = "\\d*\\.*\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        boolean isRight = matcher.matches();
        if (isRight){
            return Float.parseFloat(str);
        }else{
            return 0.00f;
        }
    }

    /**
     * 将不是整数或浮点数的字符串过滤
     * @param str
     * @return
     */
    public static double stringToDouble(String str){
        str = stringNoNull(str);
        if ("".equals(str)){
            return 0.00d;
        }
        //正则表达式，整数或浮点数
        String regex = "\\d*\\.*\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        boolean isRight = matcher.matches();
        if (isRight){
            return Double.parseDouble(str);
        }else{
            return 0.00d;
        }
    }

    /**
     * 将不是整数或浮点数的字符串过滤，并且保留两位
     * @param str
     * @return
     */
    public static String stringToDoubleStr(String str){
        double dl = stringToDouble(str);
        return String.format("%.2f",dl);
    }

    /**
     * 将不是整数或浮点数的字符串过滤，并且保留两位
     * @param str
     * @return
     */
    public static String stringToFloatStr(String str){
        float ft = stringToFloat(str);
        return String.format("%.2f",ft);
    }

    /**
     * 保留6位数字，检验地址坐标信息
     * @param str
     * @return
     */
    public static double stringToLatlngStr(String str){
        double dl = stringToDouble(str);
        String formatStr = String.format("%.6f",dl);
        return stringToDouble(formatStr);
    }

    /**
     * 将字符串为空、字符串为"null"的，转化为长度为0的字符串
     * @param str
     * @return
     */
    public static String stringNoNull(String str){
        String resultStr = "";
        if (!TextUtils.isEmpty(str)){
            if (!"null".equals(str)){
                resultStr = str;
            }
        }
        return resultStr;
    }


    /**
     * 判断SIM卡是否可用
     * @param context
     * @return
     */
    public static boolean isSIMReady(Context context){
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Service
                .TELEPHONY_SERVICE);

        return TelephonyManager.SIM_STATE_READY == telephonyManager.getSimState();
    }


    /**
     * 生成成员ID
     * @return
     */
    public static int initMemberId() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String name = format.format(new Date());
        SimpleDateFormat format1 = new SimpleDateFormat("HHmmss");
        String name1 = format1.format(new Date());
        return Integer.getInteger(name + random(4) + name1 + random(4));
    }



    public static int random(int length) {
        StringBuffer index = new StringBuffer();
        int one = (int) (1 + Math.random() * (10 - 1));
        index.append(one);
        for (int i = 0; i < length - 1; i++) {
            one = (int) (Math.random() * (10));
            index.append(one);
        }
        return Integer.parseInt(index.toString());
    }


    /**
     * 从字符串中查找数字字符串
     */
    public static List<String> getPhoneNumbers(String content) {


        List<String> digitList = new ArrayList<String>();


        Pattern p = Pattern.compile("(\\d{5,12})");


        Matcher m = p.matcher(content);
        while (m.find()) {
            String find = m.group(1).toString();
            digitList.add(find);
        }
        return digitList;
    }

}
