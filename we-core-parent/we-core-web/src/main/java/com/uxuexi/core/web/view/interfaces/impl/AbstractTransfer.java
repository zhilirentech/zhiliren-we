/**
 * AbstractTransfer.java
 * com.uxuexi.core.web.view.interfaces.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view.interfaces.impl;

import org.nutz.mvc.View;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.view.interfaces.IAddTransfer;
import com.uxuexi.core.web.view.interfaces.IStringTransfer;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 * @author   庄君祥
 * @Date	 Dec 26, 2013 	 
 */
public abstract class AbstractTransfer implements IStringTransfer {
	/**
	 * 是否转化器
	 *
	 * @param String　参数
	 * @param type　类型
	 * @param view 视图
	 * @return 是否可以添加
	*/
	@SuppressWarnings("unused")
	protected void add(final String args, final String type, final View view) {
		if (Util.isEmpty(view)) {
			return;
		}
		if (!(view instanceof IAddTransfer)) {
			return;
		}
		IAddTransfer addTransfer = (IAddTransfer) view;
		//全部都cms化
		//		Pattern jsonPPattern = Pattern.compile("-" + args);
		//		if (jsonPPattern.matcher(type).find()) {
		//			addTransfer.addTransfer(this);
		//		}

		addTransfer.addTransfer(this);
	}
}
