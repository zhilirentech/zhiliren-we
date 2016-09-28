/**
 * FunctionTreeTld.java
 * cn.vko.authority.tld
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.mvc.Mvcs;

import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.web.util.FtlUtil;

import freemarker.template.Configuration;

/**
 * 分页标签
 * @author   庄君祥
 * @Date     2013-9-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ToggleTableTld extends SimpleTagSupport {

	private String url;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		createHTML(out);
		super.doTag();
	}

	private void createHTML(final JspWriter out) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(createOne(false, 0)).append(createOne(true, 1));
		out.print(sb.toString());//输出结尾
	}

	private String createOne(final boolean inside, final int step) {
		String divId = "uintBox" + (System.currentTimeMillis() + step);
		StringBuilder sb = new StringBuilder(url);
		sb.append(url.indexOf("?") == -1 ? '?' : '&');
		sb.append("inside=").append(inside);
		sb.append("&rel=").append(divId);
		Map<String, Object> map = MapUtil.map();
		map.put("url", sb.toString());
		map.put("rel", divId);
		Configuration cfg = Mvcs.getIoc().get(null, "configuration");
		String content = FtlUtil.build(cfg, File.separator + "WEB-INF" + File.separator + "share" + File.separator
				+ "ftl" + File.separator + "toggletable.ftl", map);
		return content;
	}
}
