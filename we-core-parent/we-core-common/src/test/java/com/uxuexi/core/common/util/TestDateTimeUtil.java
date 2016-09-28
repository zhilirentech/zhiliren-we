package com.uxuexi.core.common.util;

import java.sql.Date;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.uxuexi.core.common.util.DateTimeUtil;

/**

 * @Date	 2013-2-1
 * @version  5.3.0
 */
public class TestDateTimeUtil {

	@Test(enabled = false)
	public void testNowDate() {
	}

	@Test(enabled = false)
	public void testTodayDate() {
	}

	@Test(enabled = false)
	public void testNow() {
	}

	@Test
	public void testNowStr() {
		DateTimeUtil.nowStr("yyyy-MM-HH hh:mm:ss");
	}

	@Test(enabled = false)
	public void testNowDateTime() {
	}

	@Test(enabled = false)
	public void testToDate() {
	}

	@Test
	public void testToDateTime() {
		DateTimeUtil.toDateTime(new Date(0));
	}

	@Test
	public void testPast() {
		Date date = new Date(0);
		DateTimeUtil.past(date, 30);
	}

	@Test(enabled = false)
	public void testSameMinute() {
	}

	@Test(enabled = false)
	public void testSameHour() {
	}

	@Test(enabled = false)
	public void testSameDay() {
	}

	@Test(enabled = false)
	public void testNowDayFolder() {
	}

	@Test
	public void testString2Time() {
		DateTimeUtil.string2Time("2013-12-22 12:20:59", "yyyy-MM-dd");
	}

	@Test
	public void testString2Date() {
		DateTimeUtil.string2Date("2013-12-22 12:20:59", "yyyy-MM-dd hh:mm");
	}

	@Test
	public void testGe() {
		Date date = new Date(0);
		DateTimeUtil.ge(date, date);
	}

	@Test(enabled = false)
	public void testYesterday() {
	}

	@Test(enabled = false)
	public void testYesterdayDate() {
	}

	@Test
	public void testDayOfThisMonth() {
		DateTime dateTime = new DateTime(2013, 2, 15, 23, 56, 14, 666);
		Assert.assertEquals("2013-02-01", DateTimeUtil.firstDayOfThisMonth(dateTime).toString("yyyy-MM-dd"));
		Assert.assertEquals("2013-02-28", DateTimeUtil.lastDayOfThisMonth(dateTime).toString("yyyy-MM-dd"));
		dateTime = new DateTime(2012, 4, 1, 23, 56, 14, 666);
		Assert.assertEquals("2012-04-01", DateTimeUtil.firstDayOfThisMonth(dateTime).toString("yyyy-MM-dd"));
		Assert.assertEquals("2012-04-30", DateTimeUtil.lastDayOfThisMonth(dateTime).toString("yyyy-MM-dd"));
		dateTime = new DateTime(2011, 7, 31, 23, 56, 14, 666);
		Assert.assertEquals("2011-07-01", DateTimeUtil.firstDayOfThisMonth(dateTime).toString("yyyy-MM-dd"));
		Assert.assertEquals("2011-07-31", DateTimeUtil.lastDayOfThisMonth(dateTime).toString("yyyy-MM-dd"));
	}

	@Test
	public void testDayOfThisWeek() {
		DateTime dateTime = new DateTime(2013, 12, 30, 10, 56, 14, 666);
		Assert.assertEquals("2013-12-30", DateTimeUtil.firstDayOfThisWeek(dateTime).toString("yyyy-MM-dd"));
		Assert.assertEquals("2014-01-05", DateTimeUtil.lastDayOfThisWeek(dateTime).toString("yyyy-MM-dd"));
		dateTime = new DateTime(2013, 12, 27, 23, 56, 14, 666);
		Assert.assertEquals("2013-12-23", DateTimeUtil.firstDayOfThisWeek(dateTime).toString("yyyy-MM-dd"));
		Assert.assertEquals("2013-12-29", DateTimeUtil.lastDayOfThisWeek(dateTime).toString("yyyy-MM-dd"));
		dateTime = new DateTime(2014, 1, 31, 23, 56, 14, 666);
		Assert.assertEquals("2014-01-27", DateTimeUtil.firstDayOfThisWeek(dateTime).toString("yyyy-MM-dd"));
		Assert.assertEquals("2014-02-02", DateTimeUtil.lastDayOfThisWeek(dateTime).toString("yyyy-MM-dd"));
	}

	@Test
	public void testDaysOfTwoTime() throws Exception {
		DateTime firstTime = DateTimeUtil.string2DateTime("2013-11-15", "yyyy-MM-dd");
		DateTime secondTime = DateTimeUtil.string2DateTime("2013-11-16", "yyyy-MM-dd");
		Assert.assertEquals(1, DateTimeUtil.daysOfTwoTime(firstTime, secondTime));
		DateTime firstTime1 = DateTimeUtil.string2DateTime("2013-10-10", "yyyy-MM-dd");
		DateTime secondTime1 = DateTimeUtil.string2DateTime("2013-11-16", "yyyy-MM-dd");
		Assert.assertEquals(37, DateTimeUtil.daysOfTwoTime(firstTime1, secondTime1));
		DateTime firstTime2 = DateTimeUtil.string2DateTime("2012-10-10", "yyyy-MM-dd");
		DateTime secondTime2 = DateTimeUtil.string2DateTime("2013-11-16", "yyyy-MM-dd");
		Assert.assertEquals(402, DateTimeUtil.daysOfTwoTime(firstTime2, secondTime2));
	}

	@Test
	public void testDuration2time() throws Exception {
		Assert.assertEquals("2分钟", DateTimeUtil.duration2time(120));
		Assert.assertEquals("1小时5秒", DateTimeUtil.duration2time(3605));
		Assert.assertEquals("", DateTimeUtil.duration2time(86400));
		Assert.assertEquals("23小时59分钟59秒", DateTimeUtil.duration2time(86399));
	}
}
