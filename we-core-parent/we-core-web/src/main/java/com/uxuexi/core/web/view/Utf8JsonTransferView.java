/**
 * Utf8JsonTransferView.java
 * com.uxuexi.core.web.view
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.view.UTF8JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.exception.ITimeoutException;
import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.CacheControlUtil;
import com.uxuexi.core.web.util.RequestUtil;
import com.uxuexi.core.web.view.interfaces.IAddTransfer;
import com.uxuexi.core.web.view.interfaces.IStringTransfer;
import com.uxuexi.core.web.view.support.HtmlCompressor;

/**
 * 自定义可以进行转换的jsonView类
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
public class Utf8JsonTransferView extends UTF8JsonView implements IAddTransfer {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(Utf8JsonTransferView.class);

	private List<IStringTransfer> transfers = CollectionUtil.list();
	private JsonFormat format;
	private Object data;

	@Override
	public UTF8JsonView setData(final Object data) {
		this.data = data;
		super.setData(data);
		return this;
	}

	public Utf8JsonTransferView(final JsonFormat format) {
		super(format);
		this.format = format;
	}

	@Override
	public void addTransfer(final IStringTransfer transfer) {
		if (transfers == null) {
			transfers = CollectionUtil.list();
		}
		if (transfer == null) {
			return;
		}
		transfers.add(transfer);
	}

	@Override
	public void render(final HttpServletRequest req, final HttpServletResponse resp, final Object obj)
			throws IOException {
		//		if (Util.isEmpty(transfers) && !RequestUtil.isAjaxP(req)) {
		//			super.render(req, resp, obj);
		//			return;
		//		}
		Object o = obj;
		boolean isNeedCache = true;
		if (obj instanceof Throwable) {
			isNeedCache = false;
			o = JsonResult.toJsonMap((Throwable) obj);
		} else if (obj instanceof ITimeoutException) {
			isNeedCache = false;
			o = JsonResult.timeOut("");
		}

		//如果是字符串则直接返回了
		String result = Json.toJson(null == o ? data : o, format);
		for (IStringTransfer st : transfers) {
			result = st.transfer(req, resp, result);
		}

		//http响应设置
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("application/json");
		result = RequestUtil.toJsonP(req, resp, result, false);
		resp.setContentLength(-1);

		if (isNeedCache) {
			CacheControlUtil.cache(resp, Mvcs.getActionContext().getMethod().getDeclaredAnnotations());
		} else {
			CacheControlUtil.noCache(resp);
		}
		//TODO 这个要小心 
		try {
			resp.getWriter().write(HtmlCompressor.compress(result));
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		resp.flushBuffer();
	}

}
