/**
 * WwwFailProcessor.java
 * com.uxuexi.core.web.www.chain
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.chain;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.NutConfig;

import com.uxuexi.core.common.exception.IBusinessException;
import com.uxuexi.core.common.exception.IParamException;
import com.uxuexi.core.common.exception.ITimeoutException;
import com.uxuexi.core.web.chain.FailProcessor;
import com.uxuexi.core.web.config.ConfigKey;
import com.uxuexi.core.web.config.KvConfig;
import com.uxuexi.core.web.util.CacheControlUtil;
import com.uxuexi.core.web.view.FailView;
import com.uxuexi.core.web.view.LoginView;

/**
 * 网站端异常处理类
 * @author   庄君祥
 * @Date	 2013-12-23 	 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WwwFailProcessor extends FailProcessor {
	private KvConfig kvConfig;

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
		if (th instanceof ITimeoutException) {
			new LoginView(getLoginUrl()).render(ac.getRequest(), ac.getResponse(), th);
			return;
		}
		if (view instanceof FailView) {
			view.render(ac.getRequest(), ac.getResponse(), th);
			return;
		}
		if (th instanceof IBusinessException || th instanceof IParamException) {
			CacheControlUtil.noCache(ac.getResponse());
			ac.getRequest().setAttribute(REQ_ERROR_KEY, getSimpleMessage(th));
			super.process(ac);
			return;
		}
		new FailView().render(ac.getRequest(), ac.getResponse(), th);
	}

	/**
	 * 获取登录的页面路径
	 * 如果是TimeOutExpetion，需要进行页面转向
	 *
	 * @return 路径
	 */
	protected String getLoginUrl() {
		return kvConfig.getValue(ConfigKey.LOGIN_PAGE);
	}
}
