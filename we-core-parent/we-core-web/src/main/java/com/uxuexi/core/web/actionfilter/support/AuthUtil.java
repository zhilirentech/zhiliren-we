/**
 * AuthUtil.java
 * com.uxuexi.web.common.actionfilter.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.actionfilter.support;

import java.lang.reflect.Method;

import com.uxuexi.core.common.util.EncryptUtil;

/**
 * 权限校验工具类
 * @author   庄君祥
 * @Date	 2013-11-13 	 
 */
public class AuthUtil {
	/**
	 * 获取key
	 *
	 * @param method 方法类
	 * @return 权限唯一key
	*/
	public static String getAuthKey(final Method method) {
		if (method == null) {
			return "";
		}
		return getAuthKey(method.getDeclaringClass().getName(), method.getName());

	}

	/**
	 * 获取key
	 *
	 * @param moduleName 类名
	 * @param methodName 方法名
	 * @return 权限唯一key
	*/
	public static String getAuthKey(final String moduleName, final String methodName) {
		StringBuilder sb = new StringBuilder(moduleName).append("_").append(methodName);
		return EncryptUtil.encrypt(sb.toString());
	}
}
