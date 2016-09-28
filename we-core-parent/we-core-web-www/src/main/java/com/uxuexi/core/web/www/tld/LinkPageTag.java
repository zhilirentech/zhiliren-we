/**
 * LinkPageTag.java
 * com.uxuexi.core.web.www.tld
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.uxuexi.core.common.util.Util;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 链接方式页面跳转
 * 主要用于链接样式的拼装
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class LinkPageTag extends PageTag {
	/**
	 * 两边显示链接个数
	 */
	private int edgeNum = 2;
	/**
	 * 中间显示连续的链接个数
	 */
	private int middleNum = 4;

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
		if (getPageNum() <= 1 && getTotal() <= getPageSize()) {
			return "";
		}
		StringBuilder content = new StringBuilder();
		content.append("<div class='").append(getCss()).append("'>");
		getFirst(content);
		getPrev(content);
		int np = getNumPages();
		int upper_limit = np - middleNum;
		int ne_half = middleNum / 2;
		int start = getPageNum() > ne_half ? Math.max(Math.min(getPageNum() - ne_half, upper_limit), 0) : 0;
		int stop = getPageNum() > ne_half ? Math.min(getPageNum() + ne_half + (middleNum % 2), np) : Math.min(
				middleNum, np);
		int begin;
		int end;
		if (start >= 0 && edgeNum > 0) {
			end = Math.min(edgeNum, start);
			for (int i = 1; i <= end; i++) {
				getPage(i, content);
			}
			if (edgeNum < start) {
				getEllipse(content);
			}
		}
		for (int i = start + 1; i <= stop; i++) {
			getPage(i, content);
		}
		if (stop < np && edgeNum > 0) {
			if (np - edgeNum > stop) {
				getEllipse(content);
			}
			begin = Math.max(np - edgeNum, stop);
			for (int i = begin + 1; i <= np; i++) {
				getPage(i, content);
			}
		}
		getNext(content);
		getLast(content);
		content.append("</div>");
		return content.toString();
	}

	private void getPage(final int pageNum, final StringBuilder content) {
		if (pageNum == getPageNum()) {
			getCurrent(pageNum, content);
			return;
		}
		buildLink(content, pageNum, null, null);
	}

	private void getCurrent(final int pageNum, final StringBuilder content) {
		content.append("<span class=\"vkopage_current\">").append(pageNum).append("</span>");
	}

	private void getEllipse(final StringBuilder content) {
		content.append("<span>...</span>");
	}

	private void getFirst(final StringBuilder content) {
		buildLink(content, 1, "首页", "vkopage_first");
	}

	private void getCurrent(final String showCssName, final String showContent, final StringBuilder content) {
		content.append("<span class=\"").append(showCssName);
		content.append(" vkopage_disabled\">").append(showContent).append("</span>");
	}

	private void getLast(final StringBuilder content) {
		buildLink(content, getNumPages(), "末页", "vkopage_last");
	}

	private void getPrev(final StringBuilder content) {
		if (getPageNum() <= 1) {
			getCurrent("prev", "上一页", content);
			return;
		}
		buildLink(content, getPageNum() - 1, "上一页", "vkopage_previous");
	}

	private void getNext(final StringBuilder content) {
		if (getPageNum() >= getNumPages()) {
			getCurrent("next", "下一页", content);
			return;
		}
		buildLink(content, getPageNum() + 1, "下一页", "vkopage_next");
	}

	/**
	 * 构建链接的方块
	 *
	 * @param content 拼接到的内容
	 * @param showNumber 待跳转的页面
	 * @param words 显示的文字
	 * @param css 添加的样式
	 */
	protected void buildLink(final StringBuilder content, final int showNumber, final String words, final String css) {
		content.append("<a href=\"").append(getRealLink(showNumber)).append("\"");
		if (!Util.isEmpty(css)) {
			content.append(" class=\"").append(css).append("\"");
		}
		content.append(">");
		if (Util.isEmpty(words)) {
			content.append(showNumber);
		} else {
			content.append(words);
		}
		content.append("</a>");
	}

	/**
	 * 子类需要实现的方法
	 * @param pageNum 要跳转到的页面
	 * @return 每个链接上的真实url
	 */
	protected String getRealLink(final int pageNum) {
		return "";
	}

	/**
	 * 获取总页数

	 *
	 * @return 根据总条数和页面元素个数进行计算
	 */
	private int getNumPages() {
		int num = getTotal() / getPageSize();
		int remains = getTotal() % getPageSize();
		if (remains >= 1) {
			num++;
		}
		return num;
	}
}
