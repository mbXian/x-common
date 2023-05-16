package com.xmb.common.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

}
