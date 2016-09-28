package com.uxuexi.core.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * 关于Calendar：
 * set(int field, int value) - 是用来设置"年/月/日/小时/分钟/秒/微秒"等
 * 		field 的定义在 Calendar 中，分别用来代表年，月，日等...
 * 		注意：        1,月份是从0开始计数，要设置8月，参数应给7。从一个Calendar对象中取月份，最大值只能取到11，代表12月
 * 			  2,set方法不会马上刷新内部记录
 * 			  3,设置 Calendar 为 Lenient false 时，它会依据特定的月份检查出错误的赋值。如：
 * 				cal.setLenient(false);
 *				cal.set(2000, 1, 32, 0, 0, 0);
 *			  4，Calendar 序列化的时候，如果serialize GregorianCalendar 时没有保存所有的信息，
 *				 当它被恢复到内存中，又缺少足够的信息时，Calendar 会被恢复到 EPOCH 的起始值。
 *               Calendar 对象由两部分构成：字段和相对于 EPOC 的微秒时间差。字段信息是由微秒时间差计算出的，
 *               而 set() 方法不会强制 Calendar 重新计算字段。这样字段值就不对了。可以通过调用get方法解决
 *               
 *            5，add(field, -value)方法：如果需要减去值，那么使用负数值就可以了
 *               calendar.add(Calendar.MONTH, 1) ;//在calendar代表的日期上加上1个月
 */
public class DateUtil {
	
	/**4位年*/
	public static final String FORMAT_YYYY = "yyyy" ; 
	
	/**年月，4位年，2位月*/
	public static final String FORMAT_YYYYMM = "yyyyMM" ; 
	
	/**年月日格式*/
	public static final String FORMAT_YYYYMMDD = "yyyyMMdd" ;
	
	/**时分秒格式*/
	public static final String FORMAT_HHMMSS = "HHmmss" ;
	
	/**两位的天*/
	public static final String FORMAT_DD = "dd" ;
	
	/**年月日时分秒格式*/
	public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss" ;
	public static final String FORMAT_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss" ;
	
	public static final SimpleDateFormat FORMAT_FULL =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	public static final SimpleDateFormat FORMAT_DEFAULT_DATE =new SimpleDateFormat("yyyy-MM-dd"); 
    public static final SimpleDateFormat FORMAT_DEFAULT_TIME = new SimpleDateFormat("HH:mm:ss"); 
	
	/**年-月-日格式*/
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd" ;
	
	/**默认的日期分隔符*/
	public static final String DATE_SEPARATOR_DEFAULT = "-" ;
	
	/**日期分隔符：斜杠‘/’*/
	public static final String DATE_SEPARATOR_SLASH = "/" ;
	
	/**默认的时间分隔符，冒号*/
	public static final String TIME_SEPARATOR_DEFAULT = ":" ;
	
	/**一天代表的毫秒数*/
	public static final int MILLIS_OF_DAY = 1000*60*60*24 ;
    
    /**根据日期字符串中的数字个数判断使用对应的日期格式化方式*/
	private static String getFormatPattern(String dateString){
		int len = dateString.length() ;
		String formatPattern = null ;
		
		switch(len){
		case 2:
			formatPattern =  FORMAT_DD ;
			break ;
		case 4:
			formatPattern = FORMAT_YYYY ;
			break ;
		case 6:
			formatPattern = FORMAT_YYYYMM ;
			break ;
		case 8:
			formatPattern = FORMAT_YYYYMMDD ;
			break ;
		case 14:
			formatPattern = FORMAT_YYYYMMDDHHMMSS ;
			break ;
		default:
			throw new IllegalArgumentException("日期格式不正确") ;
		}
		return formatPattern ;
	}
	
    /**==========================================日期转换格式化等=========================================================*/
	
	/**
	 * 处理给出的日期字符串，将其换算为正确的日期字符串，使用给定的格式返回，比如:
	 * 日期："2012-01-32"
	 * 格式："yyyy/MM/dd"
	 * 返回: "2012/02/01"
	 */
	public static String format(String dateString,String formatPattern){
		Calendar calendar = parse2Calendar(dateString) ;
        return format(calendar,formatPattern);
    }
	
	/**处理给出的日期字符串，将其换算为正确的日期字符串，使用默认的yyyyMMddHHmmss的形式返回*/
	public static String format(String dateString){
		Calendar calendar = parse2Calendar(dateString) ; 
        return format(calendar);
    }
	
	/**将Calendar转为yyyyMMdd形式的字符串*/
	public static String format(Calendar calendar){
		DateFormat dateFormat = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS) ;
        return dateFormat.format(calendar.getTime());
    }
	
	/**将Calendar转为指定形式的字符串*/
	public static String format(Calendar calendar,String formatPattern){
		DateFormat dateFormat = new SimpleDateFormat(formatPattern) ;
        return dateFormat.format(calendar.getTime());
    }
	
	public static String Date2String(Date date) {
		return FORMAT_FULL.format(date);
	}
	
	/**
	 * 将日期字符串转为日期类型,系统自动根据所提供的日期字符串中数字的个数选择格式化方式
	 * @param dateStr   日期字符串
	 * @return 
	 */
	public static Date string2Date(String dateStr){
		if(Util.isEmpty(dateStr)){
			throw new IllegalArgumentException("日期字符串不能为空") ;
		}else{
			dateStr = StringUtil.drawNumber(dateStr) ;
		}
		Date date = null ;
		DateFormat dateFormat = new SimpleDateFormat(getFormatPattern(dateStr)) ;
		try {
			date = dateFormat.parse(dateStr) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }
	
	public static Date string2Date(String dateStr,String formatPattern){
		if(Util.isEmpty(dateStr)){
			throw new IllegalArgumentException("日期字符串不能为空") ;
		}
		Date date = null ;
		DateFormat dateFormat = new SimpleDateFormat(formatPattern) ;
		try {
			date = dateFormat.parse(dateStr) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }
	
	/**
	 * 将字符串表示的日期转化为Calendar
	 */
	public static Calendar parse2Calendar(String dateString){
		//只保留数字
		dateString = StringUtil.drawNumber(dateString) ;
		String formatPattern = getFormatPattern(dateString) ;
		return parse2Calendar(dateString,formatPattern) ;
	}
	
	/**日期字符串转为日历*/
	public static Calendar parse2Calendar(String dateString,String formatPattern){
		Date date = string2Date(dateString,formatPattern) ;
		return parse2Calendar(date) ; 
	}
	
	/**日期转为日历*/
	public static Calendar parse2Calendar(Date date){
		Calendar c = Calendar.getInstance();
	    c.setTime(date) ;
		return c ;
	}
	
	/**
	 * 获取DateTime对象（org.joda.time.DateTime）
	 *
	 * @return dateTime对象
	 */
	public final static DateTime dateTime(Date date) {
		return new DateTime(date);
	}
	
	/**==========================================BETWEEN=========================================================*/
	
	//years between
	
	/**两个指定日期之间间隔的年数*/
	public static int yearsBetween(String datePre,String dateLatter){
		datePre = StringUtil.drawNumber(datePre) ;
		dateLatter = StringUtil.drawNumber(dateLatter) ;
		
		if(datePre.length() != dateLatter.length()){
			throw new IllegalArgumentException("两个日期的格式要相同") ;
		}
		
		String formatPattern = getFormatPattern(datePre) ;
		return yearsBetween(datePre,dateLatter,formatPattern) ; 
	}
	
	/**两个指定日期之间间隔的年数*/
	public static int yearsBetween(String datePre,String dateLatter,String formatPattern){
		Calendar pre = parse2Calendar(datePre,formatPattern) ;
		Calendar later = parse2Calendar(dateLatter,formatPattern) ;
		
		return yearsBetween(pre,later) ; 
	}
	
	/**两个指定日期之间间隔的年数*/
	private static int yearsBetween(Calendar pre,Calendar later){
		return fieldsBetween(pre,later,Calendar.YEAR) ;
	}
	
	//months between
	
	/**两个指定日期之间间隔的月数*/
	public static int monthsBetween(String datePre,String dateLatter){
		datePre = StringUtil.drawNumber(datePre) ;
		dateLatter = StringUtil.drawNumber(dateLatter) ;
		
		if(datePre.length() != dateLatter.length()){
			throw new IllegalArgumentException("两个日期的格式要相同") ;
		}
		
		String formatPattern = getFormatPattern(datePre) ;
		return monthsBetween(datePre,dateLatter,formatPattern) ; 
	}
	
	/**两个指定日期之间间隔的月数*/
	public static int monthsBetween(String datePre,String dateLatter,String formatPattern){
		Calendar pre = parse2Calendar(datePre,formatPattern) ;
		Calendar later = parse2Calendar(dateLatter,formatPattern) ;
		return monthsBetween(pre,later) ; 
	}
	
	/**两个指定日期之间间隔的月数*/
	private static int monthsBetween(Calendar pre,Calendar later){
		return fieldsBetween(pre,later,Calendar.MONTH) ;
	}
	
	//days between
	
	/**
	 * 返回两个日期之间相差的天数，如果第1个日期小于第二个日期，将返回正数，否则返回负数。
	 * 使用yyyyMMdd格式化日期
	 */
	public static int daysBetween(String datePre,String dateLatter){
		datePre = StringUtil.drawNumber(datePre) ;
		dateLatter = StringUtil.drawNumber(dateLatter) ;
		
		if(datePre.length() != dateLatter.length()){
			throw new IllegalArgumentException("两个日期的格式要相同") ;
		}
		
		String formatPattern = getFormatPattern(datePre) ;
		return daysBetween(datePre,dateLatter,formatPattern) ; 
	}
	
	/**
	 * 返回两个日期之间相差的天数，如果第1个日期小于第二个日期，将返回正数，否则返回负数。
	 * 使用指定的格式formatPattern，格式化日期
	 */
	public static int daysBetween(String datePre,String dateLatter,String formatPattern){
		Calendar pre = parse2Calendar(datePre,formatPattern) ;
		Calendar later = parse2Calendar(dateLatter,formatPattern) ;
		return daysBetween(pre,later) ; 
	}
	
	/**
	 * 返回两个Calendar之间相差的天数,使用数学的方式计算
	 */
	public static int daysBetween(Calendar pre,Calendar later){
		long millis = millisBetween(pre,later) ;
		return (int) (millis/MILLIS_OF_DAY) ;
	}
	
	/**
	 * 返回两个日期之间相差的天数，如果第1个日期小于第二个日期，将返回正数，否则返回负数。
	 * 使用yyyyMMdd格式化日期
	 */
	public static int daysBetween(Date datePre,Date dateLatter){
		String pre = FORMAT_FULL.format(datePre) ;
		String later = FORMAT_FULL.format(dateLatter) ;
		return daysBetween(pre,later) ; 
	}
	
	//millis between
	
	/**两个日期之间相差的毫秒数*/
	public static long millisBetween(Date datePre,Date dateLatter){
		String pre = FORMAT_FULL.format(datePre) ;
		String later = FORMAT_FULL.format(dateLatter) ;
		return millisBetween(pre,later) ;
	}
	
	/**两个日期之间相差的毫秒数*/
	public static long millisBetween(String datePre,String dateLatter){
		Calendar pre = parse2Calendar(datePre) ;
		Calendar later = parse2Calendar(dateLatter) ;
		return millisBetween(pre,later) ;
	}
	
	/**两个日期之间相差的毫秒数*/
	public static long millisBetween(Calendar pre,Calendar later){
		long preMillis = pre.getTimeInMillis() ;
		long latterMillis = later.getTimeInMillis() ;
		long millis = latterMillis - preMillis ;
		return millis ;
	}
	
	/**判断一个日期是否在某个区间*/
	public static boolean dateBetween(Date date,Date pre,Date later){
		return (date.after(pre) && date.before(later)) ; 
	}
	
	/**判断一个日期是否在某个区间*/
	public static boolean dateBetween(Date date,String preStr,String laterStr){
		Date pre = string2Date(preStr) ;
		Date later = string2Date(laterStr) ;
		return dateBetween(date, pre, later) ;  
	}
	
	private static int fieldsBetween(Calendar pre,Calendar later,int dateType){
		Calendar calBefore,calAfter ;
		
		//用于判断应该返回正数还是负数
		boolean positive = false ;
		
		if(pre.before(later)){
			positive  = true ;
			calBefore = pre ;
			calAfter = later ;
		}else{
			calBefore = later ;
			calAfter = pre ;
		}
		
		int fieldCount = 0;  
		while(calBefore.before(calAfter)){
			calBefore.add(dateType, 1) ;
			fieldCount++ ;
		}
		
		if(positive)
			return fieldCount ;
		else
			return -fieldCount ;
	}
	
	/**==========================================获取日期=========================================================*/
	
	//now
	
	/**
	 * 获取当前日期（java.util.Date）
	 */
	public static Date nowDate(){
		return new java.util.Date() ;
	}
	
	/**
	 * 获取当前时间的DateTime对象（org.joda.time.DateTime）
	 *
	 * @return 当前dateTime对象
	 */
	public final static DateTime nowDateTime() {
		return new DateTime();
	}
	
	/**
	 * 获取当前java.sql.Timestamp
	 * @return 当前java.sql.Timestamp
	 * @see java.util.Date
	 */
	public final static Timestamp nowTimeStamp() {
		return new Timestamp(nowDate().getTime());
	}
	
	/**
	 * 当前日期字符串（yyyy-MM-dd HH:mm:ss）
	 */
	public static String nowDateTimeString(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE,0);
		return FORMAT_FULL.format(c.getTime()); 
	} 
	
	/**
	 * 当前日期字符串（yyyy-MM-dd）
	 */
	public static String nowDateString(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE,0);
		return FORMAT_DEFAULT_DATE.format(c.getTime()); 
	} 
	
	/**
	 * 当前时间字符串（HH:mm:ss）
	 */
	public static String nowTimeString(){ 
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE,0);
		return FORMAT_DEFAULT_TIME.format(c.getTime());
	} 
	
	//年月日
	
	/**
	 * 获取一个日期的年份
	 */
	public static int getYear(Date date){
		Calendar c = parse2Calendar(date); 
		return getDateType(c,Calendar.YEAR) ; 
	}
	
	/**
	 * 获取一个日期的年份
	 */
	public static int getYear(Calendar calendar){
		return getDateType(calendar,Calendar.YEAR) ; 
	}
	
	/**
	 * 获取一个日期的年份
	 */
	public static int getYear(String dateString){
		Calendar calendar = parse2Calendar(dateString) ;
		return getYear(calendar) ; 
	}
	
	//月
	
	/**
	 * 获取一个日期的月份
	 */
	public static int getMonth(Date date){
		Calendar c = parse2Calendar(date); 
		return getDateType(c,Calendar.MONTH) + 1 ; 
	}
	
	/**
	 * 获取一个日期的月份
	 */
	public static int getMonth(Calendar calendar){
		return getDateType(calendar,Calendar.MONTH) + 1 ; 
	}
	
	/**
	 * 获取一个日期的月份
	 */
	public static int getMonth(String dateString){
		Calendar calendar = parse2Calendar(dateString) ;
		return getMonth(calendar) ; 
	}
	
	//日
	
	/**
	 * 获取一个日期的天(在当月的号数)
	 */
	public static int getDay(Date date){
		Calendar c = parse2Calendar(date); 
		return getDateType(c,Calendar.DAY_OF_MONTH) ; 
	}
	
	/**
	 * 获取一个日期的天(在当月的号数)
	 */
	public static int getDay(String dateString){
		Calendar calendar = parse2Calendar(dateString) ;
		return getDay(calendar) ; 
	}
	
	/**
	 * 获取一个日期的天(在当月的号数)
	 */
	public static int getDay(Calendar calendar){
		return getDateType(calendar,Calendar.DAY_OF_MONTH) ; 
	}
	
	private static int getDateType(Calendar calendar,int dateType){
		return calendar.get(dateType) ; 
	} 
	
	/**
	 * 获取当前年份
	 */
	public static int getCurrentYear(){
		return getYear(nowDate()) ;  
	}
	
	/**
     * 取得日期所在周的第一天(星期一)
     * @param date
     * @return
     */
	public static Date getFirstWeekDay(Date date) {
		Calendar c = parse2Calendar(date); 
	    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    return c.getTime();
	}
	    
    /**
     * 取得日期所在周的最后一天(星期天)
     * @param date
     * @return
     */
    public static Date getLastWeekDay(Date date) {
    	Calendar c = parse2Calendar(date); 
    	c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	return c.getTime();
    }
	 
    /**
     * 取得日期所在月的第一天
     * @param date
     * @return
     */
    public static Date getFirstMonthDay(Date date) {
    	Calendar c = parse2Calendar(date); 
	    int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
	    c.add(Calendar.DAY_OF_MONTH, 1 - dayOfMonth);
	    return c.getTime();
    }
    
    /**
     * 取得日期所在月的最后一天
     * @param date
     * @return
     */
    public static Date getLastMonthDay(Date date) { 
    	Calendar c = parse2Calendar(date); 
	    int dayOfMonth = c.get(Calendar.DAY_OF_MONTH); 
	    int maxDaysOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	    c.add(Calendar.DAY_OF_MONTH, maxDaysOfMonth - dayOfMonth);
	    return c.getTime();
    }
    
    /**==========================================加减操作=========================================================*/
    
    /**
     * 取得日期前/后n个月所在月的第一天
     * @param date  日期
     * @param delta 相差的月数，正数表示往后，负数表示往前
     * @return
     */
    public static Date getFirstDayOfMonthDelta(Date date,Integer delta) {
    	Calendar c = parse2Calendar(date); 
	    c.add(Calendar.MONTH, delta);
	    Date consultDate = c.getTime() ;     //date前/后n月的日期   
	    return getFirstMonthDay(consultDate); 
    }
    
    /**
     * 取得日期前/后n个月所在月的最后一天
     * @param date  日期
     * @param delta 相差的月数，正数表示往后，负数表示往前
     * @return
     */
    public static Date getLastDayOfMonthDelta(Date date,Integer delta) { 
    	Calendar c = parse2Calendar(date); 
	    c.add(Calendar.MONTH, delta);
	    Date consultDate = c.getTime() ;     //date前/后n月的日期   
	    return getLastMonthDay(consultDate); 
    }
    
    /**
     * 取得日期前/后n周的第一天
     * @param date  日期
     * @param delta 相差的周数，正数表示往后，负数表示往前
     * @return
     */
    public static Date getFirstDayOfWeekDelta(Date date,Integer delta) {
    	Calendar c = parse2Calendar(date); 
	    c.add(Calendar.WEEK_OF_MONTH, delta);
	    Date consultDate = c.getTime() ;     //date前/后n周的日期   
	    return getFirstWeekDay(consultDate); 
    }
    
    /**
     * 取得日期前/后n周的最后一天
     * @param date  日期
     * @param delta 相差的周数，正数表示往后，负数表示往前
     * @return
     */
    public static Date getLastDayOfWeekDelta(Date date,Integer delta) {
    	Calendar c = parse2Calendar(date); 
	    c.add(Calendar.WEEK_OF_MONTH, delta);
	    Date consultDate = c.getTime() ;     //date前/后n周的日期   
	    return getLastWeekDay(consultDate);  
    }
    
    /**返回指定的日期加上n年后的日期*/
    public static Date addYear(Date date,Integer year) { 
    	Calendar c = parse2Calendar(date); 
	    c.add(Calendar.YEAR, year); 
	    return c.getTime();  
    }
    
    /**返回指定的日期加上n个月后的日期*/
    public static Date addMonth(Date date,Integer months) {
	    Calendar c = parse2Calendar(date); 
	    c.add(Calendar.MONTH, months);
	    return c.getTime();  
    }
    
    /**返回指定的日期加上n天后的日期*/
    public static Date addDay(Date date,Integer day) { 
    	Calendar c = parse2Calendar(date); 
	    c.set(Calendar.DATE, c.get(Calendar.DATE) + day);
	    return c.getTime();  
    }
    
}
