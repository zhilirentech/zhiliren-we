/**
 * TagUtil.java
 * com.uxuexi.core.web.www.tld.support
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld.support;

import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.common.util.Util;

/**
 * 标签辅助类
 * @author   庄君祥
 * @Date	 2013-12-26 	 
 */
public class TagUtil {
	public static void appendAttr(final StringBuilder content, final String prop, final Object value) {
		if (Util.isEmpty(value)) {
			return;
		}
		content.append(" ").append(prop).append("='").append(value).append("'");
	}

	public static void appendAttrWithEx(final StringBuilder content, final String prop, final String value) {
		if (Util.isEmpty(value)) {
			throw ExceptionUtil.pEx("请设置属性[" + prop + "]");
		}
		appendAttr(content, prop, value);
	}
}
