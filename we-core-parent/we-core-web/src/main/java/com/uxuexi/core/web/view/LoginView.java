/**
 * LoginView.java
 * com.uxuexi.core.web.view
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;

import org.nutz.json.JsonFormat;
import org.nutz.mvc.View;

import com.uxuexi.core.web.util.CacheControlUtil;
import com.uxuexi.core.web.util.RequestUtil;

/**
 * 自定义登录视图
 * 
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
@Data
public class LoginView implements View {
	private String loginUrl;

	public LoginView(final String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Override
	public void render(final HttpServletRequest req, final HttpServletResponse resp, final Object obj) throws Exception {
		if (!RequestUtil.isAjax(req)) {
			CacheControlUtil.noCache(resp);
			Throwable th = null;
			if (obj instanceof Throwable) {
				th = (Throwable) obj;
			}
			RequestUtil.redirectTo(req, resp, loginUrl, th);
			return;
		}
		 new Utf8JsonTransferView(JsonFormat.compact()).render(req, resp, obj);
	}
}
