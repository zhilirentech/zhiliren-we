/**
 * weViewMaker.java
 * com.uxuexi.core.web.view
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view;

import java.util.regex.Pattern;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.IocException;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.cms.Cms;
import com.uxuexi.core.web.view.interfaces.impl.CmsTransfer;

import freemarker.template.Configuration;

/**
 * 自定义视图
 * 
 * @author   庄君祥
 * @Date	 Dec 26, 2013 	 
 */
public class WeViewMaker implements ViewMaker {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(WeViewMaker.class);

	@Override
	public View make(final Ioc ioc, final String type, final String value) {
		View view = fetchView(type, value, ioc);
		if (Util.isEmpty(view)) {
			return null;
		}
		try {
			//	添加处理器
			Cms cms = ioc.get(Cms.class, "cms");
			CmsTransfer.me().setCms(cms).warpper(type, view);
		} catch (IocException e) {
			logger.error("============>有任务的cms处理，请查看是否配置文件出错 " + e.getMessage());

		}

		return view;
	}

	/**
	 *　获取视图
	 *　TODO 这个再说吧
	 * @param value TODO
	 *
	 * @param type　类型
	 * @return 视图
	*/
	private View fetchView(final String type, final String value, final Ioc ioc) {
		Pattern jspPattern = Pattern.compile("\\s*jsp\\s*");
		if (jspPattern.matcher(type).find()) {
			return new JspTransferView(value);
		}
		Pattern jsonPattern = Pattern.compile("\\s*json\\s*");
		if (jsonPattern.matcher(type).find()) {
			return new Utf8JsonTransferView(null);
		}
		Pattern ftlPattern = Pattern.compile("\\s*ftl\\s*");
		if (ftlPattern.matcher(type).find()) {
			return new FtlTransferView(value, ioc.get(Configuration.class, "configuration"));
		}
		Pattern xmlPattern = Pattern.compile("\\s*xml\\s*");
		if (xmlPattern.matcher(type).find()) {
			return new XmlTransferView();
		}
		if ("fail".equals(type)) {
			return new FailView();
		}
		return null;
	}
}
