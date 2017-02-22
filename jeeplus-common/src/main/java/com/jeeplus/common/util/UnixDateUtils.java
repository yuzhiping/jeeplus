package com.jeeplus.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * unix时间戳
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-22 14:25.
 */
public class UnixDateUtils {
    /**
     * 当前时间戳
     *
     * @return 10位的数字
     */
    public static long getCurrentDate() {
        long date = System.currentTimeMillis() / 1000;
        return date;
    }



    /**
     * 输入一个时间,获取该时间的时间戳
     * @param dateString 日期字符串(yyyy-MM-dd HH:mm:ss)
     * @return long类型的10位数字
     * @throws ParseException
     */
    public static long dateToUnixTime(String dateString) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(dateString);// HH:mm:ss
        long temp = date1.getTime();// JAVA的时间戳长度是13位
        return temp / 1000;
    }

    /**
     * 格式化unix 时间戳成日期字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param time
     *            10位
     * @return yyyy-MM-dd HH:mm:ss的字符串
     */
    public static String formatUnixDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
                time * 1000));
    }
}
