/**
 * DateCms.java
 * com.uxuexi.core.cms.interfaces.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.cms.interfaces.impl;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.web.cms.ParseUtil;

/**
 * cms引擎的日期处理类
 * 处理 <cms:date uid="2013-12-13 12:31:54"/>
 * 转化为 xx秒前，xx分钟前，xx天前
 * @author   庄君祥
 * @Date	 2013-12-20 	 
 */
public class DateCms extends AbstractCms {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(DateCms.class);

	@Override
	protected String getType() {
		return "date";
	}

	@Override
	protected void changeMatch(final HttpServletRequest req, final Set<String> needDeal,
			final Map<String, String> result) {
		long now = DateTimeUtil.millis();
		for (String s : needDeal) {
			Map<String, String> props = ParseUtil.getProps(s);
			String uid = props.get("uid");
			result.put(s, showDate(uid, now));
		}
	}

	/**
	 * 显示时间
	 *
	 * @param date 要处理的时间 
	 * @param now 现在的时间戳
	 * @return 显示结果
	*/
	private String showDate(final String date, final long now) {
		try {
			DateTime dt = DateTimeUtil.string2DateTime(date, null);
			long time = dt.getMillis();
			long diff = now - time;
			if (diff < 60 * 1000) {
				return "刚刚";
			}
			if (diff < 60 * 60 * 1000) {
				return new StringBuilder(diff / (60 * 1000) + "").append("分钟前").toString();
			}
			if (diff < 24 * 60 * 60 * 1000) {
				return new StringBuilder(diff / (60 * 1000 * 60) + "").append("小时前").toString();
			}
			if (diff < (30L * 24L * 60L * 60L * 1000L)) {
				return new StringBuilder(diff / (24L * 60L * 60L * 1000L) + "").append("天前").toString();
			}
			if (diff < (12L * 30L * 24L * 60L * 60L * 1000L)) {
				return new StringBuilder(diff / (30L * 24L * 60L * 60L * 1000L) + "").append("月前").toString();
			}
			if (diff < (100L * 12L * 30L * 24L * 60L * 60L * 1000L)) {
				return new StringBuilder(diff / (12L * 30L * 24L * 60L * 60L * 1000L) + "").append("年前").toString();
			}
			return DateTimeUtil.format(DateTimeUtil.toDate(dt), "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			logger.error("日期格式异常", e); //$NON-NLS-1$
			return "";
		}
	}
}
