package com.jeeplus.weixin.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.http.client.utils.DateUtils;

import com.mysql.jdbc.TimeUtil;

/**
 * @author lzh
 * @version 1.0 创建时间：2011-10-12 下午03:20:23 类说明 日期计算
 */

public class DateUtil {

	public DateUtil() {
	}

	

	// 获取下一周的日期

	public Date nextWeek(Date currentDate) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.DATE, 7);// 在日期上加7天

		return cal.getTime();

	}

	// 获取本周日的日期

	public Date getSunday(Date monday) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(monday);

		cal.add(GregorianCalendar.DATE, 6);// 在日期上加6天

		return cal.getTime();

	}

	// 获取下一月的日期

	public Date nextMonth(Date currentDate) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.MONTH, 1);// 在月份上加1

		return cal.getTime();

	}
	
	//获取上一个月的日期
	public Date previousMonth(Date currentDate){
		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.MONTH, -1);// 在月份上加1
		
		return cal.getTime();
	}

	/**  
	* 得到某年某月的第一天  
	*   
	* @param year  
	* @param month  
	* @return  
	*/ 
	public Date getFirstDayOfMonth(int year, int month) {  
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR, year);  
		cal.set(Calendar.MONTH, month-1);  
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));  
		return cal.getTime();
	}      

	/**  
	* 得到某年某月的最后一天  
	*   
	* @param year  
	* @param month  
	* @return  
	*/  
	public Date getLastDayOfMonth(int year, int month) {  
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR, year);  
		cal.set(Calendar.MONTH, month-1);  
		cal.set(Calendar.DAY_OF_MONTH, 1);  
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	    cal.set(Calendar.DAY_OF_MONTH, value);
	    return cal.getTime();
	} 
	
	/**
	 * 得到某年某月的某一周的第一天
	 * author:wsr
	 */
	public Date getFirstDayOfWeek(int year, int month,int week) {
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR, year);  
		cal.set(Calendar.MONTH, month-1); 
		for(int i=1;i<8;i++){
			cal.set(Calendar.DATE,i);
			if(cal.get(Calendar.DAY_OF_WEEK)-1==1){//判断当前天如果是周一
				break;
			}
		}
		cal.set(Calendar.DATE,((week-1)*7)+cal.get(Calendar.DATE));
		return cal.getTime();
	} 
	
	public Date getFirstDayOfWeek2(int year, int month,int week) {
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR, year);  
		cal.set(Calendar.MONTH, month-1); 
		cal.set(Calendar.DATE,((week-1)*7)+1);
		return cal.getTime();
	} 
	/*
	 * 得到某年某月的某一周的最后一天
	 * author:wsr
	 */
	public Date getLastDayOfWeek(int year, int month,int week) {
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR, year);  
		cal.set(Calendar.MONTH, month-1); 
		for(int i=1;i<8;i++){
			cal.set(Calendar.DATE,i);
			if(cal.get(Calendar.DAY_OF_WEEK)-1==1){//判断当前天如果是周一
				break;
			}
		}
		cal.set(Calendar.DATE,((week-1)*7)+cal.get(Calendar.DATE)+6);
		return cal.getTime();
	}     
	public Date getLastDayOfWeek2(int year, int month,int week) {
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR, year);  
		cal.set(Calendar.MONTH, month-1); 
		int day=cal.getActualMaximum(Calendar.DATE);
		if(week==4){
		cal.set(Calendar.DATE,day);
		}else{
			cal.set(Calendar.DATE,week*7);
		}
		return cal.getTime();
	}    
	
	// 获取下一年的日期

	public Date nextYear(Date currentDate) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.YEAR, 1);// 在年上加1

		return cal.getTime();

	}

	
	/**
	 * 时间。是否在此范围内 不在返回真 在。假
	 * 
	 * @param date
	 *            时间
	 * @param fromDate
	 *            开始时间
	 * @param toDate
	 *            结束时间
	 * @return
	 */
	public static Boolean compare(Date date, Date fromDate, Date toDate) {
		Calendar date1 = Calendar.getInstance();
		date1.setTime(date);
		Calendar from = Calendar.getInstance();
		from.setTime(fromDate);
		Calendar to = Calendar.getInstance();
		to.setTime(toDate);

		if (date1.after(from) && date1.before(to)) {
			return false;
		}
		return true;

	}

	/**
	 * 开始时间特殊处理一下
	 * 
	 * @param beginTime
	 * @return
	 */
	public static Date getBeginTime(Date beginTime) {
		Calendar date1 = Calendar.getInstance();
		date1.setTime(beginTime);
		date1.set(Calendar.MINUTE, 00);
		date1.set(Calendar.HOUR_OF_DAY, 00);
		date1.set(Calendar.SECOND, 00);
		return date1.getTime();
	}

	/**
	 * 结束时间特殊处理一下
	 * 
	 * @param endTime
	 * @return
	 */
	public static Date getEndTime(Date endTime) {
		Calendar date1 = Calendar.getInstance();
		date1.setTime(endTime);
		date1.set(Calendar.MINUTE, 59);
		date1.set(Calendar.HOUR_OF_DAY, 23);
		date1.set(Calendar.SECOND, 59);
		return date1.getTime();
	}

	/**
	 * 返回当前日期
	 */
	public static String getCurrentDate() {
		Calendar date1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date1.getTime());
	}
	/**
	 * 返回当月第一天
	 */
	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}
	public static String getFirstDayOfYear(){
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.set(Calendar.MONTH, 0);// 设为1月
		str = sdf.format(lastDate.getTime());
		return str;
	}
	/**
	 *为真前一天 为假后一天
	 */
	public static String getCurrentDate(boolean bool) {
		Calendar date1 = Calendar.getInstance();
		if (bool) {
			date1.add(Calendar.DATE, - 1);
		} else {
			date1.add(Calendar.DATE,1); 
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date1.getTime());
	}
	/**
	 * 为真前一天 为假后一天
	 * 
	 * @param bool
	 * @param currentDate
	 * @return
	 */
	public static String getCurrentDate(boolean bool, String currentDate) {
		Calendar date1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		date1.setTime(date);

		if (bool) {
			date1.add(Calendar.DATE, - 1);
		} else {
			date1.add(Calendar.DATE,1); 
		}

		return sdf.format(date1.getTime());
	}

	/**
	 * 为真当月第一天  为假下个月第一天
	 * 
	 * @param bool
	 * @param currentDate
	 * @return
	 */
	public static String getCurrentMonth(boolean bool,String currentDate) {
		Calendar date1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		date1.setTime(date);
		date1.set(Calendar.DATE, 1); 
		
		if (bool) {
			
		} else {
			date1.add(Calendar.MONTH, 1);
			
		}

		return sdf.format(date1.getTime());
	}
	/**
	 * 为真当年第一天  为假下年第一天
	 * 
	 * @param bool
	 * @param currentDate
	 * @return
	 */
	public static String getCurrentYear(boolean bool,String currentDate) {
		Calendar date1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		date1.setTime(date);
		date1.set(Calendar.DATE, 1); 
		date1.set(Calendar.MONTH, 1);
		if (bool) {
			
		} else {
			date1.add(Calendar.YEAR, 1);
		}

		return sdf.format(date1.getTime());
	}


	/**
	 * 用途：以指定的格式格式化日期字符串
	 * 
	 * @param pattern
	 *            字符串的格式
	 * @param currentDate
	 *            被格式化日期
	 * @return String 已格式化的日期字符串
	 * @throws NullPointerException
	 *             如果参数为空
	 */
	public static String formatDate(Object currentDate, String pattern) {
		if (currentDate == null || pattern == null) {
			throw new NullPointerException("The arguments are null !");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(currentDate);
	}

	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (Exception e) {

			throw new IllegalArgumentException("日期转成时间报错");
		}
		return date;
	}
	
	
    /**
     * 比较两个日期大小
     * @param date1 日期字符串
     * @param pattern1 日期格式
     * @param date2 日期字符串
     * @param pattern2 日期格式
     * @return boolean 若是date1比date2小则返回true
     * @throws ParseException
     */
    public static boolean compareMinDate(String date1, String pattern1,
                                         String date2, String pattern2) throws ParseException
    {
    	try{
			Date d1 = parseDate(date1, pattern1);
			Date d2 = parseDate(date2, pattern2);
        return d1.before(d2);
    	}
         catch(Exception ex)
         {
             return false;
         }
    }

    /**
     * 比较两个日期大小
     * @param date1 Date
     * @param date2 Date
     * @return boolean 若是date1比date2小则返回true
     */
    public static boolean compareMinDate(Date date1,String pattern1, Date date2, String pattern2)
    {
        try
        {
            return DateUtil.compareMinDate(DateUtil.formatDate(date1,pattern1),pattern1,
                                           DateUtil.formatDate(date2, pattern2),pattern2);
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
	public static void main(String[] args) {

		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		DateUtil test = new DateUtil();

		// Date

		Date currentDate = DateUtils.parseDate("2012-7-1");

		System.out.println("当前日期是：" + df.format(currentDate));

		System.out.println("一周后的日期是：" + df.format(test.nextWeek(currentDate)));

		System.out.println("一月后的日期是：" + df.format(test.nextMonth(currentDate)));
		
		System.out.println("上月后的日期是：" + df.format(test.previousMonth(currentDate)));
		
		System.out.println("一年后的日期是：" + df.format(test.nextYear(currentDate)));

		// Timestamp

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		System.out.println("当前日期是：" + df.format(currentTime));

		System.out.println("一周后的日期是：" + df.format(test.nextWeek(currentTime)));

		System.out.println("一月后的日期是：" + df.format(test.nextMonth(currentTime)));

		System.out.println("一年后的日期是：" + df.format(test.nextYear(currentTime)));

		// 另一种计算方式，这种方式计算月和年的日期比较困难

		Timestamp nextTime = new Timestamp(currentTime.getTime() + 7 * 24 * 60
				* 60 * 1000);

		System.out.println("当前日期是：" + df.format(currentTime));

		System.out.println("一周后的日期是：" + df.format(nextTime));

		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.YEAR, 2012);  
		cal.set(Calendar.MONTH, 12-1); 
		for(int i=1;i<8;i++){
			cal.set(Calendar.DATE,i);
			if(cal.get(Calendar.DAY_OF_WEEK)-1==1){//判断当前天如果是周一
				break;
			}
		}
		Integer week=5;
		cal.set(Calendar.DATE,((week-1)*7)+cal.get(Calendar.DATE)+6);
	
		System.out.println(cal.get(Calendar.DATE));
		
		System.out.println(cal.getTime());
		
		System.out.println(test.getLastDayOfWeek2(2013,10,4).toLocaleString());
		System.out.println(test.getFirstDayOfWeek2(2013,10,4).toLocaleString());
			*/
		
		System.out.println(DateUtil.formatDate(new Date(), "yyyymmddhhmmss"));
	}

}
