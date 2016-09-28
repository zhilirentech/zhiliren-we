/**
 * FunctionTreeTld.java
 * cn.vko.authority.tld
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.entity.impl.ITreeEntity;
import com.uxuexi.core.db.entity.impl.TreeEntity;

/**
 * 树标签
 * @author   庄君祥
 * @Date	 2012-5-14 	 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreeTld extends SimpleTagSupport {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(TreeTld.class);

	private List<TreeEntity> items;
	private String var;

	@Override
	public void doTag() throws JspException, IOException {
		if (items == null || items.size() == 0) {
			logger.info("items 不能为空");
			return;
		}
		if (Util.isEmpty(var)) {
			logger.info("var 不能为空");
			return;
		}
		JspWriter out = getJspContext().getOut();
		createTree(items, out);
		super.doTag();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createTree(final List<TreeEntity> nodeList, final JspWriter out) throws JspException, IOException {
		if (nodeList == null || nodeList.size() == 0) {
			return;
		}
		Iterator<TreeEntity> it = nodeList.iterator();
		while (it.hasNext()) {
			ITreeEntity node = it.next();
			out.print("<li>");
			this.getJspContext().setAttribute(var, node);
			this.getJspBody().invoke(null);//先执行一次 
			//递归孩子们
			if (node.getSubs() != null && node.getSubs().size() != 0) {
				out.print("<ul>");
				createTree(node.getSubs(), out);
				out.print("</ul>");
			}
			out.print("</li>");//输出结尾
		}
	}
}
