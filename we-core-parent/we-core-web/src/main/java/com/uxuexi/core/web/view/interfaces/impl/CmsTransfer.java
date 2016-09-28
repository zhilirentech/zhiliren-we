/**
 * CmsTransfer.java
 * com.uxuexi.core.web.view.interfaces.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view.interfaces.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.mvc.View;

import com.uxuexi.core.web.cms.Cms;

/**
 * cms引擎进行字符串转换
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsTransfer extends AbstractTransfer {
	private Cms cms;

	private CmsTransfer() {
	}

	@Override
	public String transfer(final HttpServletRequest req, final HttpServletResponse resp, final String str) {
		return cms.parse(req, str);
	}

	@Override
	public void warpper(final String type, final View view) {
		add("c", type, view);
	}

	public CmsTransfer setCms(final Cms cms) {
		this.cms = cms;
		return this;
	}

	public Cms getCms() {
		return cms;
	}

	public static CmsTransfer me() {
		return new CmsTransfer();
	}
}
