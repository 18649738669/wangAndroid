package com.example.lilong.Tool.Utils;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aifengbin on 2017/1/24.
 */

public class DateTimeUtil {

    //浮点时间戳转化日期格式(有误差，不再使用)
    public static String ConvertStampToDate(Float time, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        String date = format.format(time);
        return date;
    }

    //日期格式转化为时间戳
    public static String ConvertDateToStamp(String time) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(time);
            Long l = d.getTime();
            timeStamp = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    /**
     * 获取当前时间
     *
     * @param timeType yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getNowTime(String timeType) {
        SimpleDateFormat formatter = new SimpleDateFormat(timeType);
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取某月的最后一天
     * 该方法不准确
     */
    public static int getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month + 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return lastDay;
    }

    // 将字符串转为时间戳
    public static String getTime(String user_time, String type) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 传入时间  得到毫秒
     *
     * @param strTime
     * @param type
     * @return
     */
    public static long getTimeMillis(String strTime, String type) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date d;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnMillis;
    }

    /**
     * 传入两个格式时间  判断是否超过一天
     */
    public static boolean isExceedDay(String lastTime, String nowTime) {
        long lastTimeM = DateTimeUtil.getTimeMillis(lastTime, "yyyy-MM-dd HH:mm:ss");
        long nowTimeM = DateTimeUtil.getTimeMillis(nowTime, "yyyy-MM-dd HH:mm:ss");

        long longExpend = nowTimeM - lastTimeM;  //获取时间差
        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        if (longHours >= 24) {
            return true;
        }
        return false;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(int time, String type) {
        SimpleDateFormat sdr = new SimpleDateFormat(type);
        String times = sdr.format(new Date(time * 1000L));
        return times;
    }


    /**
     * 将字符串转为时间戳
     */
    public static String getStampTime(String user_time, String type) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    // 将字符串转为时间戳
    public static String getStamp(String user_time, String type) {
        String str = null;
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            str = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 将毫秒转化成固定格式的时间
     * 时间格式: yyyy-MM-dd HH:mm:ss
     *
     * @param millisecond
     * @return
     */
    public static String getTimeFromMillisecond(Long millisecond, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String getStampTime() {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String str = String.valueOf(time);
        return str;
    }

    /**
     * 获取某一天的前一天时间戳
     */
    public static String getNowBeforeTime(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        calendar.roll(Calendar.DATE, -1);
        Date d = calendar.getTime();
        long l = d.getTime();
        String str = String.valueOf(l);
        String timeStamp = str.substring(0, 10);
        return timeStamp;
    }

    /**
     * 获取当前时间的前一天
     *
     * @return
     */
    private static Calendar calendar;
    private static int mYear, mMonth, mDay;

    public static float getNowBeforeTime() {
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DATE);
        if (mDay == 1) {//为当月第一天时,获取上月最后一天
            mMonth = calendar.get(Calendar.MONTH);
            calendar.add(Calendar.MONTH, -1);//将当前月设置为上个月
            mDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;//因后面要减一,为避免冲突这里先加一
        }
        float startTime = Float.parseFloat(DateTimeUtil.getStampTime(mYear + "." + mMonth + "." + (mDay - 1), "yyyy.MM.dd"));
        return startTime;
    }

    /**
     * 传入开始时间和结束时间字符串来计算消耗时长
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getTimeExpend(String startTime, String endTime, String type) {
        //传入字串类型 2016/06/28 08:30
        long longStart = getTimeMillis(startTime, type); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime, type);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        //        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数

        return longHours;
    }

    /**
     * 传入开始时间和结束时间字符串来比较大小
     *
     * @param startTime
     * @param endTime
     * @return true 结束时间大  false 开始时间大
     */
    public static boolean getTimeExpendMaxMin(String startTime, String endTime, String type) {
        long longStart = getTimeMillis(startTime, type); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime, type);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        if (longExpend > 0)
            return true;
        else
            return false;
    }

    /**
     * 改变时间格式
     * @param type1 当前时间的格式
     * @param type2 最终改变的格式
     * @param time  传入想要改变的时间
     * @return
     */
    public static String changeTimeFormat(String type1, String type2, String time){
        SimpleDateFormat sdf1 = new SimpleDateFormat(type1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(type2);
        Date d = null ;
        try{
            d = sdf1.parse(time) ;
        }catch(Exception e){
            e.printStackTrace() ;
        }
        return sdf2.format(d);
    }


    /**
     * 判断时间是几天前
     *
     * @return
     */
    public static String isYesterday(String now, String old) {
        int day = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sdf.parse(now);
            Date d2 = sdf.parse(old);
            long cha = d1.getTime() - d2.getTime();

//            Log.e(cha);
//            KLog.e(d1.getTime());
//            KLog.e(d2.getTime());

            day = new Long(cha / (1000 * 60 * 60 * 24)).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (day == 0)
            return "今天";
        else if (day == 1)
            return "昨天";

        return "两天以前";
    }

    /**
     * 将长时间格式字符串转换为时间
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate, String type) {
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 返回当前的周一的日期
     */
    public static String getMonday() {
        long endtime = System.currentTimeMillis();
        int dayofweek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek = 7;
        } else {
            dayofweek--;
        }
        long starttime = endtime - (dayofweek - 1) * 1000 * 60 * 60 * 24;
        String monday = DateTimeUtil.getTimeFromMillisecond(starttime, "yyyy-MM-dd");
        return monday;
    }


    /**
     * 返回当前周天的日期
     */
    public static String getSunday() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String monday = getMonday();
        calendar.setTime(sdf.parse(monday));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        day = day + 7;
        if (day > DateUtils.getCurrentMonthDay()) {
            day = day - DateUtils.getCurrentMonthDay();
            month = month + 1;
            if (month > 12) {
                month = 1;
                year += 1;
            }
        }
        String monthS;
        String dayS;
        if (month < 10) {
            monthS = "0" + month;
        } else {
            monthS = String.valueOf(month);
        }
        if (day < 10) {
            dayS = "0" + day;
        } else {
            dayS = String.valueOf(day);
        }
        return year + "-" + monthS + "-" + dayS;
    }

    /**
     * 返回当月的下一月
     */
    public static String monthEndDay() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthEnd;
        month += 1;
        if (month > 12) {
            month = 1;
            year += 1;
        }
        if (month < 10) {
            monthEnd = year + "-0" + month + "-01";
        } else {
            monthEnd = year + "-" + month + "-01";
        }
        return monthEnd;
    }

}
