/**
 * DateTimeUtil.java
 * com.uxuexi.util
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.joda.time.DateTime;

/**
 * 日期与时间工具类
 * 

 * @author   庄君祥
 * @Date	 2014-4-3 
 * @see org.joda.time.DateTime
 */
public final class DateTimeUtil {

	public static final String FOLDER_FORMAT = "yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator;

	public static final String CHINESE_FORMAT = "yyyy年MM月dd日";

	/**
	 * 获取当前java.sql.Date
	 * @return 当前java.sql.Date
	 * @see java.util.Date
	 */
	public final static Date nowDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 获取今天日期
	*/
	public final static Date todayDate() {
		String pattern = "yyyy-MM-dd";
		Timestamp time = string2Time(nowStr(pattern), pattern);
		return new Date(time.getTime());
	}

	/**
	 * 获取当前时间的DateTime对象
	 *
	 * @return 当前dateTime对象
	 */
	public final static DateTime now() {
		return new DateTime();
	}

	/**
	 * 获取当前时间的字符串形式
	 *
	 * @param pattern 样式
	 * @return 字符串形式
	 */
	public final static String nowStr(final String pattern) {
		return now().toString(pattern);
	}

	/**
	 * 获取当前时间的字符串形式
	 *
	 * @param 日期格式
	 * @return 字符串形式
	 */
	public final static String format(final Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间的字符串形式
	 *
	 * @param 日期格式
	 * @param pattern 样式
	 * @return 字符串形式
	 */
	public final static String format(final Date date, final String pattern) {
		if (date == null) {
			return "";
		}

		return new DateTime(date).toString(pattern);
	}

	/**
	 * 获取当前时间的字符串形式
	 *
	 * @param 时间
	 * @param pattern 样式
	 * @return 字符串形式
	 */
	public final static String format(final Timestamp time) {
		return format(time, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取当前时间的字符串形式
	 *
	 * @param 时间
	 * @param pattern 样式
	 * @return 字符串形式
	 */
	public final static String format(final Timestamp time, final String pattern) {
		if (time == null) {
			return "";
		}

		return new DateTime(time).toString(pattern);
	}

	/**
	 * 获取当前java.sql.Timestamp
	 * @return 当前java.sql.Timestamp
	 * @see java.util.Date
	 */
	public final static Timestamp nowDateTime() {
		return new Timestamp((new java.util.Date()).getTime());
	}

	/**
	 * 将datetime转换为sql.date对象
	 *
	 * @param dt 待转换的时间
	 * @return Date对象
	 */
	public final static Date toDate(final DateTime dt) {
		return new Date(dt.getMillis());
	}

	/**
	 * 将sql.Date对象转为DateTime
	 * 
	 * @param date 待转换 时间
	 * @return 转换后的DateTime
	*/
	public final static DateTime toDateTime(final Date date) {
		return new DateTime(date.getTime());
	}

	/**
	 * 判断时间是否已经过了对应的分钟
	 * <p>
	 * 如果分钟为<=0,则总是true
	 * @param date 时间
	 * @param minutes 分钟
	 * @return 是否过了对应的分钟
	 */
	public final static boolean past(final Object date, final int minutes) {
		if (minutes <= 0) {
			return true;
		}
		DateTime dt = new DateTime(date);
		DateTime dtNew = dt.plusMinutes(minutes);
		return dtNew.isBeforeNow();
	}

	/**
	 * 是否当前分钟（不用单元测试）
	 * @param time 被校验时间
	 * @return 如果是，则为真
	 */
	public final static boolean sameMinute(final DateTime time) {
		if (time == null) {
			return false;
		}
		DateTime now = now();
		return now.getMinuteOfHour() == time.getMinuteOfHour() && sameHour(time);
	}

	/**
	 * 是否当前小时（不用单元测试）
	 * @param time 被校验的时间
	 * @return 如果是，则为真
	 */
	public final static boolean sameHour(final DateTime time) {
		if (time == null) {
			return false;
		}
		DateTime now = now();
		return now.getHourOfDay() == time.getHourOfDay() && sameDay(time);
	}

	/**
	 * 是否当天（不用单元测试）
	 * @param time
	 * @return 如果是，则为真
	 */
	public final static boolean sameDay(final DateTime time) {
		if (time == null) {
			return false;
		}
		DateTime now = now();
		return now.getDayOfYear() == time.getDayOfYear();
	}

	/**
	 * 根据当前日期返回文件夹结构
	 *
	 * @return yyyy/MM/dd格式的结构
	 */
	public final static String nowDayFolder() {
		return nowStr(FOLDER_FORMAT);
	}

	public final static String nowDayChinese() {
		return nowStr(CHINESE_FORMAT);
	}

	/**
	 * 将字符串类型的日期转换为一个java.sql.Timestamp
	 * @param dateString
	 *            需要转换为java.sql.Timestamp的字符串
	 * @param format 格式
	 * @return java.sql.Timestamp
	 */
	public final static Timestamp string2Time(final String dateString, final String format) {
		DateFormat dateFormat;
		if (isEmpty(format)) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
		} else {
			dateFormat = new SimpleDateFormat(format, Locale.CHINESE);
		}
		dateFormat.setLenient(false);
		java.util.Date date = null;
		try {
			date = dateFormat.parse(dateString);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			throw pEx("String2java.sql.Timestamp出错", e);
		}
	}

	/**
	 * 将字符串类型的日期转换为一个java.sql.Date
	 * @param dateString
	 *            需要转换为java.sql.Date的字符串
	 * @param format 格式
	 * @return java.sql.Date
	 */
	public final static Date string2Date(final String dateString, final String format) {
		DateFormat dateFormat;
		if (isEmpty(format)) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
		} else {
			dateFormat = new SimpleDateFormat(format, Locale.CHINESE);
		}
		dateFormat.setLenient(false);
		java.util.Date date = null;
		try {
			date = dateFormat.parse(dateString);
			return new Date(date.getTime());
		} catch (ParseException e) {
			throw pEx("String2java.sql.Date出错", e);
		}
	}

	/**
	 * 将字符串类型的日期转换为一个org.joda.time.DateTime
	 * @param dateString
	 *            需要转换为org.joda.time.DateTime的字符串
	 * @param format 格式
	 * @return org.joda.time.DateTime
	 */
	public final static DateTime string2DateTime(final String dateString, final String format) {
		DateFormat dateFormat;
		if (isEmpty(format)) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
		} else {
			dateFormat = new SimpleDateFormat(format, Locale.CHINESE);
		}
		dateFormat.setLenient(false);
		java.util.Date date = null;
		try {
			date = dateFormat.parse(dateString);
			return new DateTime(date.getTime());
		} catch (ParseException e) {
			throw pEx("org.joda.time.DateTime", e);
		}
	}

	/**
	 * 判断前面一个日期是否大于后台一个日期（精确到天数）
	 * @param sourceDate 源日期
	 * @param targetDate 目标日期
	*/
	public final static boolean ge(final Date sourceDate, final Date targetDate) {
		if (isEmpty(sourceDate)) {
			return false;
		}
		if (isEmpty(targetDate)) {
			return false;
		}
		DateTime sourceDateTime = toDateTime(sourceDate);
		DateTime targetDateTime = toDateTime(targetDate);
		int sourceDays = sourceDateTime.getDayOfYear();
		int sourceYear = sourceDateTime.getYear();
		int targetDays = targetDateTime.getDayOfYear();
		int targetYear = targetDateTime.getYear();
		if (sourceYear > targetYear) {
			return true;
		}
		if (sourceYear == targetYear && sourceDays >= targetDays) {
			return true;
		}
		return false;
	}

	/**
	 * 获取昨天（DateTime）
	 */
	public final static DateTime yesterday() {
		DateTime now = now();
		return now.minusSeconds(now.getSecondOfDay() + 1);
	}

	/**
	 * 获取昨天（Date）
	 */
	public final static Date yesterdayDate() {
		return toDate(yesterday());
	}

	/**
	 * 获取明天（DateTime）
	 */
	public static final DateTime tomorrow() {
		DateTime now = now();
		return now.plusDays(1);
	}
	
	/**
	 * 获取明天（Date）
	 */
	public static final Date tomorrowDate() {
		return toDate(tomorrow());
	}

	/**
	 * 获取当前时间戳
	 */
	public final static long millis() {
		return now().getMillis();
	}

	/**
	 * 显示时间
	 *
	 * @param date 要处理的时间 
	 * @param now 现在的时间戳
	 * @return 显示结果
	*/
	public final static String showDate(final Timestamp time) {
		try {
			long diff = (System.currentTimeMillis() - time.getTime()) / 1000;
			if (diff < 60) {
				return "刚刚";
			}
			if (diff < 60 * 60) {
				return diff / 60 + "分钟前";
			}
			if (diff < 24 * 60 * 60) {
				return diff / 3600 + "小时前";
			}
			return format(time, "yyyy-MM-dd");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取指定的日期同月的首日
	 *
	 * @param dateTime 指定日期
	 * @return 同月首日
	 */
	public final static DateTime firstDayOfThisMonth(final DateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		int days = dateTime.getDayOfMonth();
		return dateTime.minusDays(days - 1);
	}

	/**
	 * 获取指定的日期同月的末日
	 *
	 * @param dateTime 指定日期
	 * @return 同月末日
	 */
	public final static DateTime lastDayOfThisMonth(final DateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		DateTime nextMonthDateTime = dateTime.plusMonths(1);
		int days = nextMonthDateTime.getDayOfMonth();
		return nextMonthDateTime.minusDays(days);
	}

	/**
	 * 获取指定的日期同周的首日
	 *
	 * @param dateTime 指定日期
	 * @return 同周首日
	 */
	public final static DateTime firstDayOfThisWeek(final DateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		DateTime lastDateTime = lastDayOfThisWeek(dateTime);
		return lastDateTime.minusDays(6);
	}

	/**
	 * 获取指定的日期同周的末日
	 *
	 * @param dateTime 指定日期
	 * @return 同周末日
	 */
	public final static DateTime lastDayOfThisWeek(final DateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		DateTime nextDateTime = dateTime.plusWeeks(1);
		int days = nextDateTime.getDayOfWeek();
		return nextDateTime.minusDays(days);
	}

	/**
	 * 计算两个时间相差多少天
	 *
	 * @param firstTime
	 * @param secondTime
	 * @return 天数
	 */
	public static final long daysOfTwoTime(final DateTime firstTime, final DateTime secondTime) {
		if (firstTime == null || secondTime == null) {
			return 0;
		}
		long seconds = 0;
		if (firstTime.isAfter(secondTime.getMillis())) {
			seconds = (firstTime.getMillis() - secondTime.getMillis()) / 1000;
		} else {
			seconds = (secondTime.getMillis() - firstTime.getMillis()) / 1000;
		}
		return seconds / 86400;
	}

	/**
	 * 在当前日期后增加一年时间
	 * 
	 * 例如：
	 * 今天是2014-01-04,
	 * 顺延一年后就是2015-01-04
	 */
	@SuppressWarnings("deprecation")
	public static final Date lagOneYear() {
		Date date = nowDate();
		date.setYear(date.getYear() + 1);
		return date;
	}

	/**
	 * 秒数转化为时间
	 * <p>
	 * <ul>
	 * 		<li>1.如果seconds>=86400（一天以上）或seconds<=0,  返回""</li>
	 *		<li>2.最大显示864399 -> 23小时59分钟59秒</li>
	 * 		<li>3.如果seonds转化后小时、分钟、秒其中一项为空，则该项不显示。例：120 -> 2分钟</li>
	 * </ul>
	 *
	 * @param seconds 需要转化的秒数
	 * @return 显示结果
	*/
	public static String duration2time(final int seconds) {
		if (seconds >= 86400 || seconds <= 0) {
			return "";
		}

		int h = (seconds / 3600 % 24);
		int m = (seconds / 60 % 60);
		int s = (seconds % 60);
		StringBuffer sb = new StringBuffer();
		sb.append(h <= 0 ? "" : (h + "小时")).append(m <= 0 ? "" : (m + "分钟")).append(s <= 0 ? "" : (s + "秒"));
		return sb.toString();
	}

	/**
	 * 获取从当前日期开始的固定天数后的日期
	 * 
	 * @param	day	需要增加的天数
	 */
	public static Date getAfterNumDay(final int day) {
		Calendar cal = Calendar.getInstance(Locale.CHINESE);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
		return toDate(new DateTime(cal.getTimeInMillis()));
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 *
	 * @param createTime
	 * @return TODO(这里描述每个参数,如果有返回值描述返回值,如果有异常描述异常)
	*/
	public static DateTime toDateTime(final Timestamp createTime) {
		return new DateTime(createTime.getTime());

	}

}