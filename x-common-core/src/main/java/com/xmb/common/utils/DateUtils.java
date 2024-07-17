package com.xmb.common.utils;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式(yyyy年MM月dd日)
     */
    public final static String DATE_PATTERN_CN = "yyyy年MM月dd日";

    /**
     * 时间格式(MM月dd日)
     */
    public final static String DATE_PATTERN_MM_DD_CN = "MM月dd日";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式(yyyyMMddHHmmss)
     */
    public final static String DATE_TIME_PATTERN_WITHOUT_SYMBOL = "yyyyMMddHHmmss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * @param date 日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * LocalDateTime to Date
     * @param localDateTime
     * @return
     */
    public static Date convertFromLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date to LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime convertFromDateToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * 获取一天的开始时间
     * @param date
     * @return
     */
    public static Date getDayStartTime(Date date) {
        String dateFormat = format(date, DATE_TIME_PATTERN);
        dateFormat = dateFormat.substring(0, 11) + "00:00:00";
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        try {
            date = df.parse(dateFormat);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 获取一天的结束时间
     * @param date
     * @return
     */
    public static Date getDayEndTime(Date date) {
        String dateFormat = format(date, DATE_TIME_PATTERN);
        dateFormat = dateFormat.substring(0, 11) + "23:59:59";
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        try {
            date = df.parse(dateFormat);
        } catch (Exception e) {

        }
        return date;
    }

    /**
     * 根据时间范围获得月份集
     * @return
     */
    public static List<String> getRangeSet(Date beginDate, Date endDate){
        /*      Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
          Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
          如果业务数据存在相等的时候，而且相等时也需要做相应的业务判断或处理时，你需要使用：！Date1.after(Date2);*/
        List<String> rangeSet =null;
        SimpleDateFormat sdf = null;
        rangeSet = new java.util.ArrayList<String>();
        sdf = new SimpleDateFormat("yyyy-MM");
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(beginDate);//设置日期起始时间
        while(!dd.getTime().after(endDate)){//判断是否到结束日期
            rangeSet.add(sdf.format(dd.getTime()));
            dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
        }
        return rangeSet;
    }

    /**
     * 转换DayOfWeek为中文
     */
    public static String dayOfWeek2CN(DayOfWeek dayOfWeek) {
        if (DayOfWeek.SUNDAY.compareTo(dayOfWeek) == 0) {
            return "周日";
        } else if (DayOfWeek.MONDAY.compareTo(dayOfWeek) == 0) {
            return "周一";
        } else if (DayOfWeek.TUESDAY.compareTo(dayOfWeek) == 0) {
            return "周二";
        } else if (DayOfWeek.WEDNESDAY.compareTo(dayOfWeek) == 0) {
            return "周三";
        } else if (DayOfWeek.THURSDAY.compareTo(dayOfWeek) == 0) {
            return "周四";
        } else if (DayOfWeek.FRIDAY.compareTo(dayOfWeek) == 0) {
            return "周五";
        } else if (DayOfWeek.SATURDAY.compareTo(dayOfWeek) == 0) {
            return "周六";
        }
        return null;
     }

    public  static final String YEAR = "year";
    public  static final String MONTH = "month";
    public  static final String WEEK = "week";
    public  static final String DAY = "day";
    public  static final String HOUR = "hour";

    public static ThreadLocal<SimpleDateFormat> yyyyMMddHHmmssSSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }
    };
    public static ThreadLocal<SimpleDateFormat> yyyyMMddHHmmss = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * 缺省的日期格式
     */
    public static final String DAFAULT_DATE_FORMAT = "yyyy-MM-dd";

    private static final String DATE_FORMAT = "yyyy-M-d";

    /**
     * 默认日期类型格试.
     *
     *
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DAFAULT_DATE_FORMAT);

    /**
     * 缺省的日期时间格式
     */
    public static final String DAFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式
     */
    private static String DATETIME_FORMAT = DAFAULT_DATETIME_FORMAT;

    private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAT);

    /**
     * 缺省的时间格式
     */
    private static final String DAFAULT_TIME_FORMAT = "HH:mm:ss";
    private static Long uniqueTime=(long) 0;

    /**
     * 时间格式
     */
    private static String TIME_FORMAT = DAFAULT_TIME_FORMAT;

    private static SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);

    private DateUtils()
    {
        // 私用构造主法.因为此类是工具类.
    }

    public static String getStringNowDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 计算date日期相差分钟
     * @param startTime
     * @param EndTime
     * @return
     */
    public static Long getDatedifferenceMinutes(String startTime, String EndTime){
        Long minutes = (Long) ((getDate(EndTime).getTime() - getDate(startTime).getTime()) / (1000 * 60));
        return minutes;
    }

    /**
     * 计算date日期相差毫秒
     * @param startTime
     * @param EndTime
     * @return
     */
    public static Long getDatedifferenceMillisecond(String startTime, String EndTime){
        Long millisecond = ((getDate(EndTime,DAFAULT_DATETIME_FORMAT).getTime() - getDate(startTime,DAFAULT_DATETIME_FORMAT).getTime()) / 1000);
        return millisecond;
    }

    /**
     * 获取格式化实例.
     *
     * @param pattern
     *            如果为空使用DAFAULT_DATE_FORMAT
     * @return
     */
    public static SimpleDateFormat getFormatInstance(String pattern) {
        if (pattern == null || pattern.length() == 0)
        {
            pattern = DAFAULT_DATE_FORMAT;
        }
        return new SimpleDateFormat(pattern);
    }

    public static Date getDateByCalendarType(Date time,int n,int t){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(t, n);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 格式化Calendar
     *
     * @param calendar
     * @return
     */
    public static String formatCalendar(Calendar calendar) {
        if (calendar == null)
        {
            return "";
        }
        return dateFormat.format(calendar.getTime());
    }
    /**
     * 格式化成日期时间
     * @param d
     * @return
     */
    public static String formatDateTime(Date d) {
        if (d == null)
        {
            return "";
        }
        return datetimeFormat.format(d);
    }
    /**
     * 格式化成日期
     * @param d
     * @return
     */
    public static String formatDate(Date d) {
        if (d == null)
        {
            return "";
        }
        return dateFormat.format(d);
    }

    /**
     * 格式化时间
     *
     * @param d
     * @return
     */
    public static String formatTime(Date d) {
        if (d == null)
        {
            return "";
        }
        return timeFormat.format(d);
    }

    /**
     * 格式化整数型日期
     *
     * @param intDate
     * @return
     */
    public static String formatIntDate(Integer intDate) {
        if (intDate == null)
        {
            return "";
        }
        Calendar c = newCalendar(intDate);
        return formatCalendar(c);
    }

    /**
     * 根据指定格式化来格式日期.
     *
     * @param date
     *            待格式化的日期.
     * @param pattern
     *            格式化样式或分格,如yyMMddHHmmss
     * @return 字符串型日期.
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null)
        {
            return "";
        }
        if (pattern == null)
        {
            return formatDate(date);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 取得Integer型的当前日期
     *
     * @return
     */
    public static Integer getIntNow() {
        return getIntDate(getNow());
    }

    /**
     * 取得Integer型的当前日期
     *
     * @return
     */
    public static Integer getIntToday() {
        return getIntDate(getNow());
    }

    /**
     * 取得Integer型的当前年份
     *
     * @return
     */
    public static Integer getIntYearNow() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得Integer型的当前月份
     *
     * @return
     */
    public static Integer getIntMonthNow() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }
    /**
     * 取得Integer型的当前周
     *
     * @return
     */
    public static Integer getIntWeekNow() {
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
    /**
     * 取得Integer型的当前天(月份中)
     *
     * @return
     */
    public static Integer getIntDayNow() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }
    public static String getStringToday() {
        return getIntDate(getNow()) + "";
    }

    /**
     * 根据年月日获取整型日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Integer getIntDate(int year, int month, int day) {
        return getIntDate(newCalendar(year, month, day));
    }

    /**
     * 某年月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Integer getFirstDayOfMonth(int year, int month) {
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的第一天
     *
     * @return
     */
    public static Integer getFirstDayOfThisMonth() {
        Integer year = DateUtils.getIntYearNow();
        Integer month = DateUtils.getIntMonthNow();
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的第一天
     *
     * @param date
     * @return
     * @time:2008-7-4 上午09:58:55
     */
    public static Integer getFistDayOfMonth(Date date) {
        Integer intDate = getIntDate(date);
        int year = intDate / 10000;
        int month = intDate % 10000 / 100;
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Integer getLastDayOfMonth(int year, int month) {
        return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);
    }

    /**
     * 根据Calendar获取整型年份
     *
     * @param c
     * @return
     */
    public static Integer getIntYear(Calendar c) {
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 根据Calendar获取整型日期
     *
     * @param c
     * @return
     */
    public static Integer getIntDate(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year * 10000 + month * 100 + day;
    }

    /**
     * 根据Date获取整型年份
     *
     * @param d
     * @return
     */
    public static Integer getIntYear(Date d) {
        if (d == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntYear(c);
    }
    /**
     * 根据Date获取整型年份
     *
     * @param d
     * @return
     */
    public static Integer getIntMonth(Date d) {
        if (d == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }
    /**
     * 根据Date获取整型年份
     *
     * @param d
     * @return
     */
    public static Integer getIntWeek(Date d) {
        if (d == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int week = c.get(Calendar.WEEK_OF_YEAR);
        return week;
    }
    /**
     * 根据Date获取整型年份
     *
     * @param d
     * @return
     */
    public static Integer getIntDay(Date d) {
        if (d == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }
    /**
     * 根据Date获取整型日期
     *
     * @param d
     * @return
     */
    public static Integer getIntDate(Date d) {
        if (d == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntDate(c);
    }

    /**
     * 根据Integer获取Date日期
     *
     * @param n
     * @return
     */
    public static Date getDate(Integer n) {
        if (n == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(n / 10000, n / 100 % 100 - 1, n % 100);
        return c.getTime();
    }

    public static Date getDate(String date) {
        return getDate(date, null);
    }
    /**
     * 得到指定格式的日期对象
     * @param date 字符串
     * @param format 指定格式  为null则表单日期
     * @return
     */
    public static Date getDate(String date, String format) {
        if (date != null) {
            try {
                if (date.contains("/")) {
                    date = date.replaceAll("/", "-");
                }
                if(format == null) {
                    format = DATE_FORMAT;
                }
                return getFormatInstance(format).parse(date);
            } catch (ParseException e) {
//                 log.error("解析【" + date + "】错误！");
//                throw new PlatformException("日期工具类解析【"+date+"】异常!", PlatformExceptionEnum.JE_CORE_UTIL_DATE_ERROR,new Object[]{date,format},e);
                return null;
            }
        }else {
            return null;
        }
    }

    /**
     * 根据年份Integer获取Date日期
     *
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(Integer year) {
        if (year == null)
        {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(year, 1, 1);
        return c.getTime();
    }

    /**
     * 根据年月日生成Calendar
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Calendar newCalendar(int year, int month, int day) {
        Calendar ret = Calendar.getInstance();
        if (year < 100)
        {
            year = 2000 + year;
        }
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 根据整型日期生成Calendar
     *
     * @param date
     * @return
     */
    public static Calendar newCalendar(int date) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        Calendar ret = Calendar.getInstance();
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 取得Date型的当前日期
     *
     * @return
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 取得Date型的当前日期
     *
     * @return
     */
    public static Date getToday() {
        return DateUtils.getDate(DateUtils.getIntToday());
    }

    /**
     * 整数型日期的加法
     *
     * @param date
     * @param days
     * @return
     */
    public static Integer intDateAdd(int date, int days) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        day += days;

        return getIntDate(year, month, day);
    }

    /**
     * 整数型日期的减法
     *
     * @param date
     * @param days
     * @return
     */
    public static Integer intDateSub(int date, int days) {
        return intDateAdd(date, -days);
    }

    /**
     * 计算两个整型日期之间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer daysBetweenDate(Integer startDate, Integer endDate) {
        if (startDate == null || endDate == null)
        {
            return null;
        }
        Calendar c1 = newCalendar(startDate);
        Calendar c2 = newCalendar(endDate);

        Long lg = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24;
        return lg.intValue();
    }

    /**
     * 计算两个整型日期之间的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer daysBetweenDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
        {
            return null;
        }
        Long interval = endDate.getTime() - startDate.getTime();
        interval = interval / (24 * 60 * 60 * 1000);
        return interval.intValue();
    }

    /**
     * 取得当前日期.
     *
     * @return 当前日期,字符串类型.
     */
    public static String getStringDate() {
        return getStringDate(DateUtils.getNow());
    }

    /**
     * 根据calendar产生字符串型日期
     *
     * @param d
     * @return eg:20080707
     */
    public static String getStringDate(Date d) {
        if (d == null)
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(d);
    }

    public static String getFormatStringDate(Date d) {
        if (d == null)
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }
    @SuppressWarnings("deprecation")
    public static Date clearTime(Date d){
        d.setHours(0);
        d.setMinutes(0);
        d.setSeconds(0);
        return d;
    }
    /**
     * 时间间隔的文字描述
     * @param ms
     * @return
     */
    public static String getTimeDescByMS(Long ms) {
        String timeDesc = "未知时间";
        if(1000 > ms) {
            timeDesc = "小于1秒";
        } else if(1000 <= ms && 60000 > ms) {
            timeDesc = ms / 1000 + "秒";
        } else if(60000 <= ms && 3600000 > ms) {
            timeDesc = ms / 1000 / 60 + "分钟";
        } else if(3600000 <= ms && 86400000 > ms) {
            timeDesc = ms / 1000 / 60 / 60 + "小时";
        }  else if(86400000 <= ms) {
            timeDesc = ms / 1000 / 60 / 60 / 24 + "天";
        }
        return timeDesc;
    }
    /**
     * 时间间隔的文字描述
     * @param ms
     * @return
     */
    public static String getDurationByMS(Long ms) {
        String timeDesc = "未知时间";
        if(1000 > ms) {
            timeDesc = ms+"毫秒";
        } else if(1000 <= ms && 60000 > ms) {
            timeDesc = ms / 1000 + "秒"+ms%1000+"毫秒";
        } else if(60000 <= ms && 3600000 > ms) {
            timeDesc = ms / 1000 / 60 + "分钟"+(ms / 1000)%60+"秒"+ms%1000+"毫秒";;
        } else if(3600000 <= ms){
            timeDesc="大于1小时";
        }
        return timeDesc;
    }
    /**
     * 当前时间是否是每周第一天（周一）
     * @param c
     * @return
     */
    public static boolean isFirstDayOfWeek(Calendar c) {
        if(Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 当前时间是否是每月第一天
     * @param c
     * @return
     */
    public static boolean isFirstDayOfMonth(Calendar c) {
        if(1 == c.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 当前时间是否是每季度第一天
     * @param c
     * @return
     */
    public static boolean isFirstDayOfQuarter(Calendar c) {
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3)

            c.set(Calendar.MONTH, 0);

        else if (currentMonth >= 4 && currentMonth <= 6)

            c.set(Calendar.MONTH, 3);

        else if (currentMonth >= 7 && currentMonth <= 9)

            c.set(Calendar.MONTH, 6);

        else if (currentMonth >= 10 && currentMonth <= 12)

            c.set(Calendar.MONTH, 9);

        if(1 == c.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 当前时间是否是每季度第一天
     * @param c
     * @return
     */
    public static boolean isFirstDayOfHalfYear(Calendar c) {
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 6)

            c.set(Calendar.MONTH, 0);

        else if (currentMonth >= 7 && currentMonth <= 12)

            c.set(Calendar.MONTH, 5);

        if(1 == c.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();



        System.out.println(c.get(Calendar.MONTH));
    }

    /**
     * 当前时间是否是每年第一天
     * @param c
     * @return
     */
    public static boolean isFirstDayOfYear(Calendar c) {
        if(1 == c.get(Calendar.DAY_OF_YEAR)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取当前时间串   格式为  YmdHis
     * @return
     */
    public static String getNowTimeStr(){
        return DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
    }
    /**
     * 得到唯一的时间毫秒值
     * @return
     */
    public static Long getUniqueTime(){
        Long value=new Date().getTime();
        if(value<DateUtils.uniqueTime){
            value=DateUtils.uniqueTime;
        }
        if((value-DateUtils.uniqueTime)==0){
            value+=1;
        }
        DateUtils.uniqueTime=value;
        return value;
    }
    /**
     * 修正以字符串形式表示的日期时间值，如果日期时间不符合格式将修正该值。
     * @param str 以字符串形式表达的日期时间值。
     * @param dateOnly 是否只返回日期部分字符串。
     * @return 修正后的值。
     */
    public static String fixTimestamp(String str, boolean dateOnly) {
        int pos = str.indexOf(' ');
        String datePart, timePart = null, sec[];
        if (pos == -1) {
            datePart = str;
            if (!dateOnly)
                timePart = "00:00:00";
        } else {
            datePart = str.substring(0, pos);
            if (!dateOnly)
                timePart = str.substring(pos + 1);
        }
        sec = datePart.split("-");
        if (sec.length == 3) {
            StringBuilder buf = new StringBuilder(dateOnly ? 10 : 30);
            buf.append(sec[0]);
            buf.append('-');
            if (sec[1].length() == 1)
                buf.append('0');
            buf.append(sec[1]);
            buf.append('-');
            if (sec[2].length() == 1)
                buf.append('0');
            buf.append(sec[2]);
            if (!dateOnly) {
                buf.append(' ');
                buf.append(timePart);
            }
            return buf.toString();
        } else
            return str;
    }
    /**
     * 修正以字符串形式表示的时间值，如果时间不符合格式将修正该值。
     * @param str 以字符串形式表达的时间值。
     * @return 修正后的值。
     */
    public static String fixTime(String str) {
        if (str.indexOf(':') == -1)
            return "00:00:00";
        int b = str.indexOf(' '), e = str.indexOf('.');
        if (b == -1)
            b = 0;
        else
            b++;
        if (e == -1)
            e = str.length();
        return str.substring(b, e);
    }
    /**
     * 获取过去第几天的日期
     *
     * @param before
     * @return
     */
    public static String getBeforeDate(int before) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - before);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
    //根据一个日期获取前后N天的日期
    public static Date getDateByDate(Date time,int n){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DATE, n);
        Date date = calendar.getTime();
        return date;
    }
    //JAVA获取某段时间内的所有日期
    public static List<Date> findDates(Date dStart, Date dEnd) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dStart);
        List dateList = new ArrayList();
        //别忘了，把起始日期加上
        dateList.add(dStart);
        // 此日期是否在指定日期之后
        while (dEnd.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(cStart.getTime());
        }
        return dateList;
    }
    public static List getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List lDate = new ArrayList();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }
    /**
     *
     * 描述: 获取当前周日期的集合
     *
     * @auther: wangmm@ketr.com.cn
     * @date: 2019/2/28 14:21
     */
    public static List<String> weekDays(String pattern) {
        return weekDays(getNow(),pattern);
    }
    public static List<String> weekDays(Date date, String pattern) {
        ArrayList<String> weeks =  Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        weeks.add(sdf.format(cal.getTime()));
        for (int i = 0; i < 6; i++){
            cal.add(Calendar.DATE, 1);
            weeks.add(sdf.format(cal.getTime()));
        }
        return weeks;
    }
    /**
     *
     * @doc 日期转换星期几
     * @param datetime
     *            日期 例:2017-10-17
     * @return String 例:星期二
     * @author lzy
     * @history 2017年10月17日 上午9:55:30 Create by 【lzy】
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    public static int getDays(Date date) {
        int month = getIntMonth(date);
        int year = getIntYear(date);
        int days = 0;
        if (month != 2) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;

            }
        } else {
            // 闰年
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                days = 29;
            else
                days = 28;
        }
        return days;
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @param sdf
     * @return
     */
    public static Date str2Date(String str, SimpleDateFormat sdf) {
        if (null == str || "".equals(str)) {
            return null;
        }
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转换为字符串
     *
     * @param date     日期
     * @param date_sdf 日期格式
     * @return 字符串
     */
    public static String date2Str(Date date, SimpleDateFormat date_sdf) {
        if (null == date) {
            return null;
        }
        return date_sdf.format(date);
    }

}
