/**
 * FunctionTreeTld.java
 * cn.vko.authority.tld
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.pager.Pager;
import org.nutz.mvc.Mvcs;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.util.FtlUtil;

import freemarker.template.Configuration;

/**
 * 分页标签
 * @author   庄君祥
 * @Date     2013-9-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageTld extends SimpleTagSupport {

	private boolean isDialog = false;
	private int begin = 10;
	private int end = 40;
	private int step = 10;
	private int pageNumShown = 10;
	private Pager pager;
	private String rel;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		createTree(out);
		super.doTag();
	}

	private void createTree(final JspWriter out) throws IOException {
		Map<String, Object> map = MapUtil.map();
		map.put("isDialog", isDialog);
		List<Integer> list = CollectionUtil.list();
		for (int i = begin; i <= end; i += step) {
			list.add(i);
		}
		map.put("list", list);
		map.put("pageNumShown", pageNumShown);
		map.put("pager", pager);
		map.put("rel", rel);
		Configuration cfg = Mvcs.getIoc().get(null, "configuration");
		String content = FtlUtil.build(cfg, File.separator + "WEB-INF" + File.separator + "share" + File.separator
				+ "ftl" + File.separator + "page.ftl", map);
		if (Util.isEmpty(content)) {
			return;
		}
		out.print(content);//输出结尾
	}
}
