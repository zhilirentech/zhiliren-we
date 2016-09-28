/**
 * EncodingFilter.java
 * com.uxuexi.filter
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 主拦截器用于处理请求及jsp文件的编码,这个一定放到最前面
 * 
 * @author   庄君祥

 * @Date	 2012-4-26
 */
public class EncodingFilter implements Filter {

	private static String encoding = "UTF-8";

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html; charset=UTF-8");

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(final FilterConfig config) throws ServletException {
		setEncode(config);
	}

	private static void setEncode(final FilterConfig config) {
		if (config.getInitParameter("encoding") != null) {
			encoding = config.getInitParameter("encoding");
		}
	}
}
