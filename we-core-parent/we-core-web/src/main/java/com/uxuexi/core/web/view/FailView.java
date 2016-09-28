/**
 * FailView.java
 * com.uxuexi.core.web.view
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view;

import static com.uxuexi.core.common.util.ExceptionUtil.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;

import org.nutz.json.JsonFormat;
import org.nutz.mvc.View;

import com.uxuexi.core.web.chain.FailProcessor;
import com.uxuexi.core.web.util.CacheControlUtil;
import com.uxuexi.core.web.util.RequestUtil;

/**
 * 自定义失败视图
 * 
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
@Data
public class FailView implements View {

	public FailView() {

	}

	@Override
	public void render(final HttpServletRequest req, final HttpServletResponse resp, final Object obj) throws Exception {
		if (!RequestUtil.isAjax(req)) {
			CacheControlUtil.noCache(resp);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/share/500.jsp");
			if (obj instanceof Throwable) {
				req.setAttribute(FailProcessor.REQ_ERROR_KEY, getSimpleMessage((Throwable) obj));
			}
			rd.forward(req, resp);
			return;
		}
		new Utf8JsonTransferView(JsonFormat.compact()).render(req, resp, obj);
	}
}
