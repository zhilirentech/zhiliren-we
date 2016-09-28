/**
 * ParamLinkPageTag.java
 * com.uxuexi.core.web.www.tld
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld;

/**
 * 拼接参数的分页标签
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
@SuppressWarnings("serial")
public class ParamLinkPageTag extends LinkPageTag {
	@Override
	protected String getRealLink(final int pageNum) {
		StringBuilder link = new StringBuilder(getLink());
		if (link.indexOf("?") > -1) {
			link.append("&");
		} else {
			link.append("?");
		}
		link.append("pageNumber=").append(pageNum).append("&pageSize=").append(getPageSize());
		return link.toString();
	}
}
