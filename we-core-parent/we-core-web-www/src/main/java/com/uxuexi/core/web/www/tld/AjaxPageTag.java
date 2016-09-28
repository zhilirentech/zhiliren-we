/**
 * AjaxPageTag.java
 * com.uxuexi.core.web.www.tld
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.www.tld.support.TagUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ajax方式页面跳转,供页面预加载第一页内容
 * 然后再ajax加载其他内容时使用
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class AjaxPageTag extends PageTag {
	public static final String AJAX_CSSNAME = "ipage";
	/**
	 * 页面样式，目前支持 1 link,2 more(点击获取更多)
	 */
	private String pageStyle = "1";
	/**
	 * 数据为空时的提示信息
	 */
	private String emptyMsg = "";
	/**
	 * 提交的数据
	 */
	private String data = "{isAjax:true}";
	/**
	 * 列表更新对象
	 */
	private String list;
	/**
	 * 列表更新时，替换的内部属性
	 */
	private String items;
	/**
	 * 是否加载第一页
	 */
	private boolean loadFirst = false;

	/**
	 * 是否是嵌套分页
	 * 
	 */
	private boolean nestedPage = false;
	/**
	 * 是否ajaxp
	 */
	private boolean ajaxp = false;
	/**
	 * 分页方块显示个数
	 */
	private int displayNum = 10;

	@Override
	public int doStartTag() throws JspException {
		try {
			super.doStartTag();
			pageContext.getOut().write(getContent());
		} catch (IOException e) {
			throw new JspException("分页构造失败", e);
		}
		return EVAL_PAGE;
	}

	protected String getContent() {
		if (getPageNum() <= 1 && getTotal() <= getPageSize() && !loadFirst) {
			return "";
		}
		StringBuilder content = new StringBuilder();
		content.append("<div");
		TagUtil.appendAttrWithEx(content, "class", getCss());
		TagUtil.appendAttrWithEx(content, "pageurl", getLink());
		TagUtil.appendAttrWithEx(content, "pagelist", list);
		TagUtil.appendAttr(content, "pageitem", items);
		TagUtil.appendAttr(content, "pagenum", getPageNum());
		TagUtil.appendAttr(content, "pagesize", getPageSize());
		TagUtil.appendAttr(content, "pagecount", getTotal());
		TagUtil.appendAttr(content, "pagestyle", pageStyle);
		TagUtil.appendAttr(content, "pagefirst", loadFirst);
		TagUtil.appendAttr(content, "nestedPage", nestedPage);
		TagUtil.appendAttr(content, "pageempty", emptyMsg);
		TagUtil.appendAttr(content, "pageajaxp", ajaxp);
		TagUtil.appendAttr(content, "pagedata", data);
		//TagUtil.appendAttr(content, "pagedisplay", displayNum);

		content.append("></div>");
		return content.toString();
	}

	@Override
	protected String getCss() {
		if (Util.isEmpty(super.getCss())) {
			return AJAX_CSSNAME;
		}
		return AJAX_CSSNAME + " " + super.getCss();
	}
}
