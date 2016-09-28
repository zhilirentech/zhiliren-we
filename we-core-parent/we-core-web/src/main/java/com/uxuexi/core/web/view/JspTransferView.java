/**
 * JspTransferView.java
 * com.uxuexi.core.web.view
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.view.JspView;

import com.uxuexi.core.common.exception.ITimeoutException;
import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.util.CacheControlUtil;
import com.uxuexi.core.web.util.RequestUtil;
import com.uxuexi.core.web.view.interfaces.IAddTransfer;
import com.uxuexi.core.web.view.interfaces.IStringTransfer;
import com.uxuexi.core.web.view.support.HtmlCompressor;
import com.uxuexi.core.web.view.support.ResponseWrapper;

/**
 * 自定义可以进行转换的jspView类
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
public class JspTransferView extends JspView implements IAddTransfer {

	public static final String IS_AJAX_KEY = "isAjax";
	private List<IStringTransfer> transfers = CollectionUtil.list();

	public JspTransferView(final String name) {
		super(name);
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
	public void render(final HttpServletRequest req, final HttpServletResponse resp, final Object obj) throws Exception {
		PrintWriter writer = resp.getWriter();
		//		if (Util.isEmpty(transfers) && !RequestUtil.isAjaxP(req)) {
		//			super.render(req, resp, obj);
		//			return;
		//		}

		String path = getPath(req, obj);
		RequestDispatcher rd = req.getRequestDispatcher(path);
		if (rd == null) {
			throw Lang.makeThrow("Fail to find Forward '%s'", path);
		}
		ResponseWrapper rw = new ResponseWrapper(resp);
		rd.include(req, rw);
		String result = "";
		try {
			result = rw.getContent();
		} catch (Throwable e) {
			throw new Exception(e);
		}
		for (IStringTransfer st : transfers) {
			result = st.transfer(req, resp, result);
		}
		result = RequestUtil.toJsonP(req, resp, result, true);
		resp.setContentLength(-1);

		boolean isNeedCache = true;
		if (obj instanceof Throwable) {
			isNeedCache = false;
		} else if (obj instanceof ITimeoutException) {
			isNeedCache = false;
		}
		if (isNeedCache) {
			CacheControlUtil.cache(resp, Mvcs.getActionContext().getMethod().getDeclaredAnnotations());
		} else {
			CacheControlUtil.noCache(resp);
		}

		writer.write(HtmlCompressor.compress(result));
		resp.flushBuffer();
	}

	@SuppressWarnings("null")
	private String getPath(final HttpServletRequest req, final Object obj) {
		String path = evalPath(req, obj);
		String args = "";
		if (path != null && path.contains("?")) { //将参数部分分解出来
			args = path.substring(path.indexOf('?'));
			path = path.substring(0, path.indexOf('?'));
		}

		String ext = getExt();
		// 空路径，采用默认规则
		if (Strings.isBlank(path)) {
			path = Mvcs.getRequestPath(req);
			path = "/WEB-INF" + (path.startsWith("/") ? "" : "/") + Files.renameSuffix(path, ext);
		}
		// 绝对路径 : 以 '/' 开头的路径不增加 '/WEB-INF'
		else if (path.charAt(0) == '/') {
			if (!path.toLowerCase().endsWith(ext)) {
				path += ext;
			}
		}
		// 包名形式的路径
		else {
			path = "/WEB-INF/" + path.replace('.', '/') + ext;
		}

		// 执行 Forward
		path = path + args;
		return path;
	}

	@Override
	protected String getExt() {
		String ext = ".jsp";
		if (Mvcs.getReq() == null) {
			return ext;
		}
		if (Util.eq("true", Mvcs.getReq().getParameter(IS_AJAX_KEY))) {
			return "_ajax.jsp";
		}
		return ext;
	}
}
