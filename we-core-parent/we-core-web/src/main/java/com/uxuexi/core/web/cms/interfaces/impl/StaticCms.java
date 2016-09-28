/**
 * StaticCms.java
 * com.uxuexi.core.cms.interfaces.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.cms.interfaces.impl;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.uxuexi.core.web.cms.ParseUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cms中静态内容处理类
 * 如果公用的头、footer
 * 处理 <cms:static st="head"/>,会从SevletContext中获取对应的数据
 * @author   庄君祥
 * @Date	 2013-12-23 	 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StaticCms extends AbstractCms {
	private ServletContext context;

	@Override
	protected String getType() {
		return "static";
	}

	@Override
	protected void changeMatch(final HttpServletRequest req, final Set<String> needDeal,
			final Map<String, String> result) {
		for (String str : needDeal) {
			Map<String, String> props = ParseUtil.getProps(str);
			String st = props.get("st");
			result.put(str, String.valueOf(context.getAttribute(st)));
		}
	}
}
