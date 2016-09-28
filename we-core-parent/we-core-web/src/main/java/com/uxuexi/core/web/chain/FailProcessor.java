/**
 * FailProcessor.java
 * com.uxuexi.web.common.chain
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.chain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.impl.processor.ViewProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.exception.IBusinessException;
import com.uxuexi.core.common.exception.ITimeoutException;
import com.uxuexi.core.common.util.Util;

/**
 * web项目统一的异常处理
 * 
 * @author 庄君祥
 * @Date   2012-4-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FailProcessor extends ViewProcessor {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(FailProcessor.class);

	/**
	 * request存放错误信息的key
	 */
	public static final String REQ_ERROR_KEY = "REQ_ERROR_KEY";

	@Override
	public void init(final NutConfig config, final ActionInfo ai) throws Throwable {
		view = evalView(config, ai, ai.getFailView());
	}

	/**
	 * 处理错误信息
	 * <p>
	 * 首先根据错误类别对错误进行日志记录
	 * 根据错误view的方式，返回错误信息
	 * @see org.nutz.mvc.impl.processor.ViewProcessor#process(org.nutz.mvc.ActionContext)
	 */
	@Override
	public void process(final ActionContext ac) throws Throwable {
		Throwable th = ac.getError();
		handleError(ac, th);
	}

	/**
	 * 错误处理逻辑
	 *
	 * @param ac 请求的上下文
	 * @param th 异常信息
	 */
	protected void handleError(final ActionContext ac, final Throwable th) {
		ac.getRequest().setAttribute("javax.servlet.error.status_code", 500);//防止jsp解析错误的时候报错。
		if (th instanceof IBusinessException || th instanceof ITimeoutException) {
			return;
		}
		String url = "unknown";
		if (ac.getRequest() != null && !Util.isEmpty(ac.getRequest().getRequestURL())) {
			url = ac.getRequest().getRequestURL().toString();
		}
		logger.error("exception happen,please handle it!the url is " + url, th); //$NON-NLS-1$
	}

}
