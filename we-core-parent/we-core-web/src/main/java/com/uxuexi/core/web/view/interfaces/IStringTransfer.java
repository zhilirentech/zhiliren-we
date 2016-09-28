/**
 * IStringWrapper.java
 * com.uxuexi.core.web.view
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

/**
 * 处理返回字符串的接口
 * @author   庄君祥
 * @author 	 庄君祥
 * @Date	 2013-12-24 	 
 */
public interface IStringTransfer {

	String transfer(final HttpServletRequest req, final HttpServletResponse resp, final String str);

	void warpper(final String type, final View view);
}
