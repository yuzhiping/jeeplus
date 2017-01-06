package com.jeeplus.weixin.fastweixin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-06 10:08.
 */
public class DateTimeUtils {

    /**
     * 默认数据模式
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    // ISO8601 Date Type Pattern
    public static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZ";

    /**
     * Default DateTimeZone 00:00
     */
    public static DateTimeZone defultTZ = DateTimeZone.forOffsetHours(0);

    /**
     * 说明：Date to String
     *
     */
    public static String DateToString(Date date) {
        return DateToString(date, DEFAULT_PATTERN);
    }

    public static String DateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    // ISO8601 Date Type
    public static String ISO8601DateToString(Date date) {
        return ISO8601DateToString(date, ISO8601_PATTERN);
    }
    public static String ISO8601DateToString(Date date, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        DateTime dt = new DateTime(date);
        return fmt.print(dt.withZone(defultTZ));
    }
    public static String ISO8601DateToString(DateTime dt) {
        return ISO8601DateToString(dt, ISO8601_PATTERN);
    }
    public static String ISO8601DateToString(DateTime dt, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.print(dt.withZone(defultTZ));
    }


    /**
     * String To Date
     * @param dateStr
     * @return
     */
    public static Date StringToDate(String dateStr) {
        return StringToDate(dateStr, DEFAULT_PATTERN);
    }

    public static Date StringToDate(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // ISO8601
    public static Date ISO8601StringToDate(String dateStr) {
        return ISO8601StringToDateTime(dateStr, ISO8601_PATTERN).toDate();
    }
    public static Date ISO8601StringToDate(String dateStr, String pattern) {
        return ISO8601StringToDateTime(dateStr, pattern).toDate();
    }
    public static DateTime ISO8601StringToDateTime(String dateStr) {
        return ISO8601StringToDateTime(dateStr, ISO8601_PATTERN);
    }
    public static DateTime ISO8601StringToDateTime(String dateStr, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseDateTime(dateStr);
    }

    /**
     * 获取当前日期
     *
     */
    public static String getCurrentDateTimeText(String pattern) {
        if ((pattern==null)||(pattern.equals(""))) {
            pattern = DEFAULT_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentDateTimeText() {
        return getCurrentDateTimeText(DEFAULT_PATTERN);
    }

    /**
     * 获取前一小时时间
     * @param pattern
     * @return
     */
    public static String getPreHourText(String pattern) {
        Calendar c = Calendar.getInstance();
        return getPreHourText(c.getTime(), pattern);
    }

    public static String getPreHourText(Date date, String pattern) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    public static Date getPreHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, -1);
        return c.getTime();
    }

    /** 获取前n小时时间*/
    public static Date getPreHourN(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, 0-n);
        return c.getTime();
    }

    public static Date getNextHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, 1);
        return c.getTime();

    }

    /**
     * 说明：获取当前日前一日
     *
     */
    public static String getPreDayText(String pattern) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    public static Date getPreDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    /** 获取前n天时间*/
    public static Date getPreDayN(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 0-n);
        return c.getTime();
    }

    public static Date getNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();

    }

    /**
     * 获取当前月前一月
     * @param pattern
     * @return
     */
    public static String getPreMonthText(String pattern) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    public static Date getPreMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    /**
     * 获取前n月时间
     * @param date
     * @param n
     * @return
     */
    public static Date getPreMonthN(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0-n);
        return c.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        return c.getTime();

    }

    // 获取前一年
    public static String getPreYearText(String pattern) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    public static Date getPreYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        return c.getTime();
    }

    /**
     * 获取前n年
     * @param date
     * @param n
     * @return
     */
    public static Date getPreYearN(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 0-n);
        return c.getTime();
    }

    // the right date type should be like "2012-12-15T00:00:00+08:00"
    public static void validateDateFormat(String dateString) throws Exception {
        Pattern pattern = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}:\\d{2}:\\d{2}[\\+|\\-]\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(dateString);
        boolean isValidated = matcher.matches();
        if (!isValidated) {
            throw new Exception("'" + dateString + "' is not a validate " +
                    "date string. Please follow this sample:2012-12-15T00:00:00+08:00");
        }
    }

}
