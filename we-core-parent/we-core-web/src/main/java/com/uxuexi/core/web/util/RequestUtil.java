/**
 * RequestUtil.java
 * com.uxuexi.web.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.exception.ITimeoutException;
import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.common.util.HtmlUtil;
import com.uxuexi.core.common.util.JsonUtil;
import com.uxuexi.core.common.util.StringUtil;

/**
 * http请求工具类
 * 

 * @author   庄君祥
 * @Date	 2013-1-21 	 
 * @version  5.1.0
 */
public class RequestUtil {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
	/**
	 * request存放中转的错误信息的标识key(<strong><font color="red">error</font></strong>)
	 */
	public static final String REDIRECT_ERROR_KEY = "error";

	/**
	 * request存放中转的原URL的标识(<strong><font color="red">from</font></strong>)
	 */
	public static final String REDIRECT_FROM_KEY = "destinationUrl";
	private static final String CALLBACK_FOMART = "{0}({1})";

	/**
	 * 根据req请求，判断是否需要对str进行jsonp处理，
	 * 将处理后的结果返回，并处理响应的头部信息
	 *
	 * @param req 请求对象
	 * @param resp 响应对象
	 * @param str 字符串
	 * @param needToJson 是否需要再次json
	 * @return 转换的后的字符串
	 */
	public static String toJsonP(final HttpServletRequest req, final HttpServletResponse resp, final String str,
			final boolean needToJson) {
		if (!isAjaxP(req)) {
			return str;
		}
		String callback = req.getParameter("callback");
		String result = StringUtil.format(CALLBACK_FOMART, callback, needToJson ? JsonUtil.toJson(str) : str);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("application/x-javascript");
		return result;
	}

	/**
	 * 获取URL中相对路径
	 * 
	 * @param request 请求
	 * @return 例如，URL：http://www.we.cn/myhome.html，返回结果：/myhome.html<br>
	 * 			或者，URL：http://www.we.cn/，返回：/
	 */
	public static String getRelativeUrl(final HttpServletRequest request) {
		if (isEmpty(request)) {
			return "";
		}
		return request.getRequestURI().substring(request.getContextPath().length());
	}

	/**
	 * 从http请求中获取url
	 * 
	 * @param request 请求
	 * @return url
	 */
	public static String getUrl(final HttpServletRequest request) {
		if (isEmpty(request)) {
			return "";
		}
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURL());
		String queryString = request.getQueryString();
		if (!isEmpty(queryString)) {
			url.append("?").append(queryString);
		}
		return url.toString();
	}

	/**
	 * 获取客户端ip地址
	 * <p>
	 * 取X-Forwarded-For中第一个非unknown的有效IP字符串
	 * @param request 请求
	 * @return 客户端ip地址
	 */
	public static String getIpAddr(final HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String ip = request.getHeader("x-forwarded-for");
		if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 校验请求是否为ajax
	 * TODO ajax判断有问题。。
	 * @param request 请求
	 * @return 是为真
	 */
	public static boolean isAjax(final HttpServletRequest request) {
		if (request == null) {
			return false;
		}
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			return true;
		}
		if (request.getRequestURI().endsWith(".json")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断请求是不是jsonp方式
	 *
	 * @param req 请求
	 * @return 是
	 */
	public static boolean isAjaxP(final HttpServletRequest request) {
		if (!isAjax(request)) {
			return false;
		}
		String callback = request.getParameter("callback");
		if (!isEmpty(callback)) {
			return true;
		}
		return false;
	}

	/**
	 * 写入流
	 * <p>
	 * 默认字符编码是UTF-8
	 * @param resp http响应
	 * @param content 内容
	 * @param contentType 内容的类型
	 */
	public static void safeWrite(final HttpServletResponse resp, final String content, final String contentType) {
		if (resp == null) {
			return;
		}
		try {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType(contentType);
			resp.getWriter().write(content);
			resp.getWriter().flush();
		} catch (IOException e) {
			throw pEx("写入流失败", e);
		}
	}

	/**
	 * 获取cookie
	 * @param request 请求
	 * @param cookieName cookie名称
	 * @return 记录信息的cookie
	 */
	public final static Cookie getCookie(final HttpServletRequest request, final String cookieName) {
		if (isEmpty(cookieName) || request == null) {
			return null;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (eq(cookieName, cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * 记录cookie
	 * 
	 * @param response 响应
	 * @param cookie 需要记录的cookie
	 */
	public static void addCookie(final HttpServletResponse response, final Cookie cookie) {
		if (response == null) {
			return;
		}
		if (isEmpty(cookie)) {
			return;
		}
		response.addCookie(cookie);
	}

	/**
	 * 转向登录页面
	 *
	 * @param request 请求
	 * @param resp 响应
	 * @param loginUrl 登录页
	 * @param th 异常信息
	 */
	public static void redirectTo(final HttpServletRequest request, final HttpServletResponse resp, final String url,
			final Throwable th) {
		if (resp == null) {
			return;
		}
		StringBuilder redirectUrl = new StringBuilder(url);
		String oldUrl = getUrl(request);
		if (redirectUrl.indexOf("?") > -1) {
			redirectUrl.append("&");
		} else {
			redirectUrl.append("?");
		}
		redirectUrl.append(REDIRECT_FROM_KEY).append("=").append(HtmlUtil.urlEncode(oldUrl));
		if (th != null && !(th instanceof ITimeoutException)) {
			redirectUrl.append("&").append(REDIRECT_ERROR_KEY).append("=")
					.append(HtmlUtil.urlEncode(ExceptionUtil.getSimpleMessage(th)));
		}
		try {
			resp.sendRedirect(redirectUrl.toString());
		} catch (IOException e) {
			logger.error("页面转向错误！", e);
		}
	}
}
