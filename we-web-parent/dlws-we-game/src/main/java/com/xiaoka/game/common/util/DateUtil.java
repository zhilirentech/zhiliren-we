package com.xiaoka.game.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

import com.uxuexi.core.common.util.DateTimeUtil;

/**
 * 关于Calendar：
 * set(int field, int value) - 是用来设置"年/月/日/小时/分钟/秒/微秒"等
 * 		field 的定义在 Calendar 中，分别用来代表年，月，日等...
 * 		注意：1,月份是从0开始计数，要设置8月，参数应给7。从一个Calendar对象中取月份，最大值只能取到11，代表12月
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
 * 使用变量的时候，前缀m表示是成员变量(member)，p表示方法的参数(parameter)，v表示变量(variable)
 */
public class DateUtil {
	
	private static GregorianCalendar gregorianCalendar = null; 
	
	static {
		gregorianCalendar = new GregorianCalendar(Locale.CHINA);
		gregorianCalendar.setLenient(true);
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
	}
	
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
	
	private static StringTokenizer mTokenizer ;
	
	
	 /**返回指定的日期加上n个月后的日期*/
    public static Date addMonth(Date date,Integer months) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date) ;
	    c.add(Calendar.MONTH, months);
	    return c.getTime();  
    }
    
    /**返回指定的日期加上n年后的日期*/
    public static Date addYear(Date date,Integer year) { 
	    Calendar c = Calendar.getInstance();
	    c.setTime(date) ;
	    c.add(Calendar.YEAR, year); 
	    return c.getTime();  
    }
    
    /**返回指定的日期加上n天后的日期*/
    public static Date addDay(Date date,Integer day) { 
	    Calendar c = Calendar.getInstance();
	    c.setTime(date) ;
	    
	    c.set(Calendar.DATE, c.get(Calendar.DATE) + day);
	    return c.getTime();  
    }
    public static void main(String[] args) {
    	
    	Date addDay = addDay(DateTimeUtil.nowDate(), -1) ;
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(addDay));  
	}
}
