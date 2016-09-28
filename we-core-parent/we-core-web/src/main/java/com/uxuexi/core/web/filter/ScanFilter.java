/**
 * ScanFilter.java
 * com.uxuexi.web.filter
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.nutz.resource.Scans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;

/**
 * 配置nutz的扫描目录的filter
 * <p>
 * 此filter应该在nutz的filter之前加载
 * @author   庄君祥
 * @author   庄君祥
 * @Date	 2013-3-4 	 
 */
public class ScanFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(ScanFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	/**
	 * 加载需要扫描的包，用于@相关注入，参见web.xml中scanFilter的ScanFiles参数
	 * <p>
	 * 根据配置扫描对应的文件,<b>如果没配置，则不扫描</b>
	 */
	@Override
	public void init(final FilterConfig fc) throws ServletException {
		String scanFiles = fc.getInitParameter("scanFiles");
		if (Util.isEmpty(scanFiles)) {
			return;
		}
		String[] sfs = scanFiles.split(",");
		for (String sf : sfs) {
			try {
				Scans.me().registerLocation(Class.forName(sf.trim()));
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage() + "-->" + sf.trim());
			}
		}
	}

}
