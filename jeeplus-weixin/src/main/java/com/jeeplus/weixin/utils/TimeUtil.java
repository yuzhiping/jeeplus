package com.jeeplus.weixin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author lzh
 * @version 1.0
 */
public class TimeUtil{
  public TimeUtil(){
  }

  //日期时间的输出样式字符串
  public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_TIME_PATTERN2 = "yyyyMMddHHmmss";
  public static final String DATE_TIME_PATTERN_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
  public static final String DATE_PATTERN = "yyyy-MM-dd";
  public static final String DATE_PATTERN2 = "yy-MM-dd";
  public static final String DATE_PATTERN3 = "yyyyMMdd";
  public static final String DATE_PATTERN4 = "yyMMdd";
  public static final String TIME_PATTERN = "HH:mm:ss";
  public static final String DAY_PATTERN2 = "MM";

  //时间日期输出是样的初始化对象\
  
  public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_PATTERN);
  public static final SimpleDateFormat DATE_TIME_FORMAT2 = new SimpleDateFormat(DATE_TIME_PATTERN2);
  public static final SimpleDateFormat DATE_TIME_FORMAT_SSS = new SimpleDateFormat(DATE_TIME_PATTERN_SSS);
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
  public static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat(DATE_PATTERN2);
  public static final SimpleDateFormat DATE_FORMAT3 = new SimpleDateFormat(DATE_PATTERN3);
  public static final SimpleDateFormat DATE_FORMAT4 = new SimpleDateFormat(DATE_PATTERN4);
  public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_PATTERN);
  
  /**
   * 获取当前时间指定偏移类的时间对象
   * @param field    偏移的域�? 以Calendar常量取�?�， �? Calendar.DATE
   * @param offset   正负数偏移量
   * @return
   */
  public static Date getOffsetDate(int field, int offset){
  	Calendar cal = Calendar.getInstance();
  	cal.setTime(new Date());
  	cal.add(field, offset);
  	return cal.getTime();
  }
  
  /**
   * 获取日期对象的指定域�? 
   * @param dt        日期对象，如果为空将使用当前时间
   * @param field     指定域， 以Calendar常量取�??
   * @param offset    正负数偏移量，不偏移�?0
   * @return
   */
  public static int getDateField(Date dt, int field, int offset){
  	Calendar cal = Calendar.getInstance();
  	cal.setTime(dt==null? new Date() : dt);
  	if(offset!=0);
  		cal.add(field, offset);
  	return cal.get(field);
  }
  
  public static int getDateField(Date dt, int field){
  	return getDateField(dt, field, 0);
  }
  
  public static int getDateField(int field){
  	return getDateField(null, field, 0);
  }
  
  public static int getDateField(int field, int offset){
  	return getDateField(null, field, offset);
  }


  /** 

       * 计算两个日期之间相差的天数 

       * @param date1 

       * @param date2 

       * @return 

       */  

      public static int daysBetween(Date date1,Date date2)  

      {  

          Calendar cal = Calendar.getInstance();  

          cal.setTime(date1);  

          long time1 = cal.getTimeInMillis();               

          cal.setTime(date2);  

          long time2 = cal.getTimeInMillis();       

          long between_days=(time2-time1)/(1000*3600*24);  
         return Integer.parseInt(String.valueOf(between_days));         

      }  

  
  /**
   * 格式化SQL中的字串参数(�?'号改�?'')
   * @param str
   * @return
   */
  public static String formatSQLString(String str){
  	if(str==null)
  		return "";
  	return str.replaceAll("'", "''");
  }
  
  /**
   * 格式化为默认格式(yyyy-MM-dd HH:mm:ss)的日�?+时间字符�?
   * @param date Date
   * @param pattern String   指定的格式字符串
   * @return String          date或pattern为空,返回空串
   */
  public static String formatDate(Date date, String pattern){
    if(date==null || pattern==null)
      return "";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
  }

  /**
   * 格式化为默认格式(yyyy-MM-dd)的日期字符串
   * @param date Date
   * @return String
   */
  public static String formatDate(Date date){
    if(date == null)
      return "";
    return DATE_FORMAT.format(date);
  }
  
  /**
   * 格式化为默认格式(yy-MM-dd)的日期字符串
   * @param date Date
   * @return String
   */
  public static String formatDate2(Date date){
	    if(date == null)
	      return "";
	    return DATE_FORMAT2.format(date);
	  }
  
  /**
   * 格式化为默认格式(yyyyMMdd)的日期字符串
   * @param date Date
   * @return String
   */
  public static String formatDate3(Date date){
	  if(null == date){
		  return "";
	  }
	  return DATE_FORMAT3.format(date);
  }
  
  
  /**
   * 格式化为默认格式(yyyyMMdd)的日期字符串
   * @param date Date
   * @return String
   */
  public static String formatDate4(Date date){
	  if(null == date){
		  return "";
	  }
	  return DATE_FORMAT4.format(date);
  }
  
  /**
   * 格式化为默认格式(yyyy-MM-dd HH:mm:ss)的日�?+时间字符�?
   * @param date Date
   * @return String
   */
  public static String formatDateTime(Date date){
    if(date==null)
      return "";
    return DATE_TIME_FORMAT.format(date);
  }
  
  /**
   * 格式化为默认格式(yyyyMMdd HH:mm:ss)的日�?+时间字符�?
   * @param date Date
   * @return String
   */
  public static String formatDateTime2(Date date){
    if(date==null)
      return "";
    return DATE_TIME_FORMAT2.format(date);
  }

  /**
   * 格式化为默认格式(HH:mm:ss)的时间字符串
   * @param date Date
   * @return String
   */
  public static String formatTime(Date date){
    if(date==null)
      return "";
    return TIME_FORMAT.format(date);
  }
    
  
  /**
   * 格式化为默认格式(yyyy-MM-dd HH:mm:ss.sss)的日�?+时间字符�?
   * @param date Date
   * @return String
   */
  public static String formatDateTime_sss(Date date){
    if(date==null)
      return "";
    return DATE_TIME_FORMAT_SSS.format(date);
  }

  /**
   * 将字符串转化为日期对�?,应用格式 yyyy-MM-dd
   * @param dateStr String
   * @return Date
   */
  public static Date parseDate(String dateStr){
    try{
      return DATE_FORMAT.parse(dateStr);
    } catch(ParseException ex){
      return null;
    }
  }

  /**
   * 将字符串转化为日期对�?,应用指定的格�?
   * @param dateStr String   日期字符�?
   * @param pattern String   格式字符�?
   * @return Date
   */
  public static Date parseDate(String dateStr, String pattern){
    try{
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.parse(dateStr);
    } catch(Exception ex){
      return null;
    }
  }

  /**
   * 将字符串转化为日期对�?,应用格式 yyyy-MM-dd HH:mm:ss
   * @param dateStr
   * @return Date
   */
  public static Date parseDateTime(String dateStr){
    try{
      return DATE_TIME_FORMAT.parse(dateStr);
    } catch(ParseException ex){
      return null;
    }
  }
  
  /**
   * 将字符串转化为日期对�?,应用格式 yyyy-MM-dd HH:mm:ss
   * @param dateStr
   * @return Date
   */
  public static Date parseDateTime2(String dateStr){
    try{
      return DATE_TIME_FORMAT2.parse(dateStr);
    } catch(ParseException ex){
      return null;
    }
  }

  /**
   * 将字符串转化为日期对�?,应用格式 HH:mm:ss
   * @param dateStr String
   * @return Date
   */
  public static Date parseTime(String dateStr){
    try{
      return TIME_FORMAT.parse(dateStr);
    } catch(ParseException ex){
      return null;
    }
  }
  
  public static String weekByDate(String dateStr){
	  Calendar cal = Calendar.getInstance();
      cal.setTime(TimeUtil.parseDate(dateStr));          
      int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
      //if(w==0)w=7;
      //System.out.println(w);
      if(w == 1){
    	  return "一";
      }else if(w == 2){
    	  return "二";
      }else if(w == 3){
    	  return "三";
      }else if(w == 4){
    	  return "四";
      }else if(w == 5){
    	  return "五";
      }else if(w == 6){
    	  return "六";
      }else if(w == 0){
    	  return "日";
      }
      
      return "";
  }
  
  public static Boolean weekendByDate(String dateStr){
	  Calendar cal = Calendar.getInstance();
      cal.setTime(TimeUtil.parseDate(dateStr));          
      int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
      if(w == 6 || w == 0){
    	  return true;
      }else {
          return false;
      }
  }
  /**
   * 任务触发时间
   * 预约时间 减去 5分钟
   * 预约报警
   * @return
   */
  public static String timetriggerReservation(Date date){
	  //Date date1 = new Date(date.getTime()-5*60*1000);
	  Calendar calendar = GregorianCalendar.getInstance(Locale.CHINA); 
	  calendar.setTimeInMillis(date.getTime()-5*60*1000);
	  String year = String.valueOf(calendar.get(Calendar.YEAR));
	  String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
	  String day = String.valueOf(calendar.get(Calendar.DATE));
	  String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
	  String minute = String.valueOf(calendar.get(Calendar.MINUTE));
	  String second = String.valueOf(calendar.get(Calendar.SECOND));
	  //0 25 20 13 5 ? 2011
	  return "0 " + minute + " " + hour + " " + day + " " + month + " ? " + year;
  }
  
  /**
   * 日期减去5分钟返回
   * @param date
   * @return
   */
  public static Date timetriggerReservationDate(Date date){
	  //Date date1 = new Date(date.getTime()-5*60*1000);
	  Calendar calendar = GregorianCalendar.getInstance(); 
	  calendar.setTimeInMillis(date.getTime()-5*60*1000);
	  return calendar.getTime();
  }
  
  
  /**
   * 当前日期的前三个月日期字符串
   * @return
   */
  public static String getNowBeginThree(){
	  GregorianCalendar calendar = new GregorianCalendar();
	  calendar.set(calendar.get(GregorianCalendar.YEAR),calendar.get(GregorianCalendar.MONTH), 1 );
      calendar.add(GregorianCalendar.DATE,  - 1 );      
      int  month = calendar.get(GregorianCalendar.MONTH) - 1 ;
      return calendar.get(GregorianCalendar.YEAR) + "-" + month + "-" + new Date().getDate();
  }
  
  public   static  String begin = "" ;
  public   static  String end = "" ;
  public   static  String now = new  Date( new  Date().getTime()).toString();

  public   static   void  calcToday(String begin,String end,String now,GregorianCalendar calendar)  {
      
      begin = now;
      end = now;
      System.out.println( " begin: " + begin);
      System.out.println( " end: " + end);
      System.out.println( " ---------------------- " );    
  } 
  
   public   static   void  calcYesterday(String begin,String end,String now,GregorianCalendar calendar) {

  
      calendar.add(GregorianCalendar.DATE,  - 1 );
      begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
      end = begin;
      System.out.println( " begin: " + begin);
      System.out.println( " end: " + end);
      System.out.println( " ---------------------- " );    
  } 
  
   public   static   void  calcThisWeek(String begin,String end,String now,GregorianCalendar calendar){
      end = now;
       int  minus = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 2 ;
       if (minus < 0 ){
          System.out.println( " 本周还没有开始，请查询上周 " );
          System.out.println( " ---------------------- " );
      } else  {
  
      calendar.add(GregorianCalendar.DATE,  - minus);
      begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
      System.out.println( " begin: " + begin);
      System.out.println( " end: " + end);
      System.out.println( " ---------------------- " );
      } 
  } 
  
   public   static   void  calcLastWeek(String begin,String end,String now,GregorianCalendar calendar) {
       int  minus = calendar.get(GregorianCalendar.DAY_OF_WEEK) + 1 ;
      calendar.add(GregorianCalendar.DATE, - minus);
      end = new  java.sql.Date(calendar.getTime().getTime()).toString();
      calendar.add(GregorianCalendar.DATE, - 4 );
      begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
      System.out.println( " begin: " + begin);
      System.out.println( " end: " + end);
      System.out.println( " ---------------------- " );
  } 

   public   static   void  calcThisMonth(String begin,String end,String now,GregorianCalendar calendar){
      end = now;
       int  dayOfMonth = calendar.get(GregorianCalendar.DATE);
      calendar.add(GregorianCalendar.DATE,  - dayOfMonth + 1 );
      begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
      System.out.println( " begin: " + begin);
      System.out.println( " end: " + end);
      System.out.println( " ---------------------- " );
  } 
   public   static   void  calcLastMonth(String begin,String end,String now,GregorianCalendar calendar){
      
      calendar.set(calendar.get(GregorianCalendar.YEAR),calendar.get(GregorianCalendar.MONTH), 1 );
      calendar.add(GregorianCalendar.DATE,  - 1 );
      //end = new  java.sql.Date(calendar.getTime().getTime()).toString();
      
      int  month = calendar.get(GregorianCalendar.MONTH) - 1 ;
      int d = new  Date().getDate();
      begin = calendar.get(GregorianCalendar.YEAR) + "-" + month + "-" + d;
              
      System.out.println( " begin: " + begin);
      //System.out.println( " end: " + end);
      System.out.println( " ---------------------- " );
  } 
   
   public static List<Date> getdate(Date date){ 
	   Calendar c = Calendar.getInstance();
	   c.setTime(date); 
	   int today = c.get(Calendar.DAY_OF_WEEK);
	   List<Date> list = new ArrayList<Date>(); 

	   for(int i=1;i<today-1;i++){ 
	   c.add(Calendar.DAY_OF_WEEK,-today+i+1);
	   c.getTime(); 
	   list.add(c.getTime()); 
	   c.setTime(date); 
	   } 
	   list.add(date); 
	   for(int i=1;i<7-today+2;i++){ 
	   c.add(Calendar.DAY_OF_WEEK,i);
	   c.getTime(); 
	   list.add(c.getTime()); 
	   c.setTime(date); 
	   } 
	   return list; 
   } 
   
   /**
    * 获取月份长度
    * @param year
    * @param month
    * @return
    */
   public static int getMothLen(int year,int month){
	   Calendar cal = Calendar.getInstance();
	   cal.set(Calendar.YEAR,year);
	   cal.set(Calendar.MONTH, month - 1);//Java月份才0开始算
	   int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
	   return dateOfMonth;
   }
  
   public static String getCDate(Date date,String parrent){
	   SimpleDateFormat   sdf   =   new   SimpleDateFormat(parrent);
	   return sdf.format(date);
   }
   
   
   	public 	static String   getMonthMaxDate(String str ){
   		
   		int month = Integer.parseInt(str.substring(5));
   		switch(month){
   		case 1:
   		case 3:
   		case 5:
   		case 7:
   		case 8:
   		case 10:
   		case 12:
   		return "31";
   		case 4:
   		case 6:
   		case 9:
   		case 11:
   		return "30";
   		case 2:
   		int year = Integer.parseInt(str.substring(0,3));
   		if((year%4==0 && year%100!=0) || year%400==0)
   		return "29";
   		else
   		return "28";
   		}
   		return "0";
   		
   		
   		
   	}
   	public static void main2(String[] args) {
		System.out.println(getMothLen(2010,2));
		System.out.println(getCDate(new Date(),"dd"));
	}

  /*public static void main(String arg[]){
	  //Date   date   =   new   Date(); 
	  //SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"   ); 
	  //sdf.format(   date   );
	  //System.out.println(sdf.format(   date   ));
	  
	  // TODO Auto-generated method stub
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	// System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	//   Date   date   =   new   Date();  
	 //  SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd   HH:ss");  
	 //  System.out.println("time   now:"+df.format(date));  
	 ///  Calendar   canlandar   =   Calendar.getInstance();  
	 //  canlandar.setTime(date);  
	 //  canlandar.add(canlandar.DATE,-1*(canlandar.get(canlandar.DAY_OF_MONTH)));  
	 //  canlandar.add(canlandar.MONTH,1);  
	 //  System.out.println("month   end:"+df.format(canlandar.getTime()));
	// 今天 
      //calcToday(begin,end,now, new  GregorianCalendar());
       // 昨天 
      //calcYesterday(begin,end,now, new  GregorianCalendar());
       // 本周 
      //calcThisWeek(begin,end,now, new  GregorianCalendar());
       // 上周 
      //calcLastWeek(begin,end,now, new  GregorianCalendar());
       // 本月 
      //calcThisMonth(begin,end,now, new  GregorianCalendar());
       // 上月 
      //calcLastMonth(begin,end,now, new  GregorianCalendar());
     // System.out.println("abc = " + getNowBeginThree());
      //TimeUtil.timetrigger(new Date());
     // System.out.println(TimeUtil.timetriggerReservation(new Date()));
      
      //System.out.println(TimeUtil.formatDateTime(new Date(System.currentTimeMillis()-5*60*1000)));
      Date date = new Date();
      Calendar calendar = GregorianCalendar.getInstance(Locale.CHINA); 
	  calendar.setTimeInMillis(date.getTime()+ 2*24*60*60*1000 + 12*60*60*1000);
	  String year = String.valueOf(calendar.get(Calendar.YEAR));
	  String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
	  String day = String.valueOf(calendar.get(Calendar.DATE));
	  String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
	  String minute = String.valueOf(calendar.get(Calendar.MINUTE));
	  String second = String.valueOf(calendar.get(Calendar.SECOND));
	  //0 25 20 13 5 ? 2011
	  System.out.println("0 " + minute + " " + hour + " " + day + " " + month + " ? " + year);
	  
	  System.out.println(TimeUtil.formatDateTime2(new Date()));
	  System.out.println(TimeUtil.formatDateTime(TimeUtil.parseDateTime2("20110831142803")));
	  
	  //
	 //方案一
	  //Calendar calendar = Calendar.getInstance();
	  calendar.setTime(TimeUtil.parseDate("2011-10-01"));
	  calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
	  1);
	  calendar.roll(Calendar.DATE, false);
	  System.out.println("aa = " + calendar.get(Calendar.DATE));

	  // ////////////方案二

	  Calendar c = Calendar.getInstance();
	  int d = c.getActualMaximum(Calendar.DAY_OF_MONTH);

	  System.out.println("max days is: " + d);
	  
	  
	  String dateTemp = "2011-9-23"; 
	  List<Date> list = getdate(java.sql.Date.valueOf(dateTemp)); 
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	  Object[] ff = new Object[]{sdf.format(list.get(0)),sdf.format(list.get(1)),sdf.format(list.get(2)),sdf.format(list.get(3)),sdf.format(list.get(4)),sdf.format(list.get(5)),sdf.format(list.get(6))};
	  System.out.println(sdf.format(list.get(0))+","+sdf.format(list.get(1))+","+sdf.format(list.get(2))+","+sdf.format(list.get(3))+","+sdf.format(list.get(4))+","+sdf.format(list.get(5))+","+sdf.format(list.get(6)));

	  //Date dat = new java.util.Date();
      java.util.Calendar cal = java.util.Calendar.getInstance();
      cal.setTime(TimeUtil.parseDate("2011-10-02"));          
      int w=cal.get(java.util.Calendar.DAY_OF_WEEK)-1;
      if(w==0)w=7;
      System.out.println(w);
      
      
      
      System.out.println(TimeUtil.weekByDate("2011-9-23"));

  }*/
   public static void main(String[] args) {
	//System.out.println(formatDate(new Date(TimeZone.getDefault()))));
	   
}
  
}
