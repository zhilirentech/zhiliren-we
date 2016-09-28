/**
 * MonitorAuthFilter.java
 * com.uxuexi.core.web.filter
 * Copyright (c) 2015, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uxuexi.core.common.util.CookieUtil;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.common.util.Md5Util;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.FailProcessor;

/**
 * 监控权限控制过滤器
 *
 * @author   庄君祥
 * @Date	 2015-3-10 	 
 */
public class MonitorAuthFilter implements Filter {
	private static final String PASSWORD_KEY = "password";
	private static final String USERNAME_KEY = "username";
	private static final int TIME_OUT = 3600;
	private static final String KEY = "javamelody";
	private String username;
	private String password;
	private String value;

	@Override
	public void init(FilterConfig config) throws ServletException {
		username = config.getInitParameter(USERNAME_KEY);
		password = config.getInitParameter(PASSWORD_KEY);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getRequestURI().indexOf("monitoring") == -1) {
			chain.doFilter(request, response);
			return;
		}
		if (!Util.isEmpty(password) && !Util.isEmpty(username)) {
			//一小时一变，即有效时间为一小时
			value = Md5Util.md5(DateTimeUtil.format(DateTimeUtil.toDate(DateTimeUtil.now()), "yyyy-MM-dd HH"));
			String cookieValue = CookieUtil.getCookieValue(KEY, req);
			String error_msg = "您访问的页面不存在";
			//验证是否有登录。简单实现
			if (!Util.isEmpty(cookieValue) && value.equals(cookieValue)) {
				chain.doFilter(request, response);
				return;
			}
			//如果没有登录，请登录。
			String un = request.getParameter(USERNAME_KEY);
			String p = request.getParameter(PASSWORD_KEY);
			if (Util.eq(un, username) && Util.eq(password, p)) {
				HttpServletResponse resp = (HttpServletResponse) response;
				//用户和密码对，则登录
				CookieUtil.addCookie(KEY, value, "", TIME_OUT, resp);
			} else {
				//错误，给出提示
				request.setAttribute(FailProcessor.REQ_ERROR_KEY, error_msg);
				throw ExceptionUtil.bEx(error_msg);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
