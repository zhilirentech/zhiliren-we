/**
 * IocUtil.java
 * cn.vko.util
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import org.nutz.ioc.Ioc;

import com.uxuexi.core.common.util.ExceptionUtil;

/**
 * 用于持有ioc对象的工具类
 * <p>
 * 持有ioc并对ioc的方法进行封装
 * @author   彭文杰
 * @Date	 2012-4-24	 
 */
public class IocUtil {
	private static Ioc INSTANCE;

	public static void setIoc(final Ioc ioc) {
		INSTANCE = ioc;
	}

	public static Ioc get() {
		return INSTANCE;
	}

	public static <T> T get(final Class<T> type) {
		if (INSTANCE == null) {
			throw ExceptionUtil.pEx("IocUtil未被初始化!");
		}
		return INSTANCE.get(type);
	}

	public static <T> T get(final Class<T> type, final String name) {
		if (INSTANCE == null) {
			throw ExceptionUtil.pEx("IocUtil未被初始化!");
		}
		return INSTANCE.get(type, name);
	}

}
