/**
 * CookieUtil.java
 * com.uxuexi.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.Util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 操作cookie的工具类
 * 

 * @author	  庄君祥
 * @Date	 2014-4-3  	 
 */
public final class CookieUtil {

	/**
	 * 获取http请求中指定的cookie值
	 *
	 * @param 	cookieName	cookie名称
	 * @param 	req			req请求对象
	 * @return 	cookie值
	 */
	public static String getCookieValue(final String cookieName, final HttpServletRequest req) {
		Cookie cookie = getCookie(cookieName, req);
		return cookie == null ? "" : cookie.getValue();
	}

	/**
	 * 获取http请求中指定的cookie
	 *
	 * @param 	cookieName	cookie名称
	 * @param 	req			req请求对象
	 * @return 	cookie对象
	 */
	protected static Cookie getCookie(final String cookieName, final HttpServletRequest req) {
		if (isEmpty(cookieName) || req == null) {
			return null;
		}
		Cookie[] cookies = req.getCookies();
		if (isEmpty(cookies)) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * 添加cookie
	 * 
	 * @param 	key		cookie的key
	 * @param 	val		cookie的value
	 * @param	domain	cookie存储的域
	 * @param	time	cookie存储时间
	 * @param 	resp	应答对象
	 * @return	返回cookie对象
	*/
	public static void addCookie(final String key, final String val, final String domain, final int time,
			final HttpServletResponse resp) {
		Cookie cookie = new Cookie(key, val);
		if (!isEmpty(domain)) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		if (time > 0) {
			cookie.setMaxAge(time);
		}
		resp.addCookie(cookie);
	}

	/**
	 * 移除cookie
	 * 
	 * @param 	cookieName 	cookie名称
	 * @param	domain		cookie存储的域
	 * @param	req			req请求
	 * @param	resp		resp应答
	 * @param 	req http请求
	 */
	public static void rmCookie(final String cookieName, final String domain, final HttpServletRequest req,
			final HttpServletResponse resp) {
		Cookie cookie = getCookie(cookieName, req);
		if (cookie != null) {
			if (!isEmpty(domain)) {
				cookie.setDomain(domain);
			}
			cookie.setPath("/");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
	}
}
