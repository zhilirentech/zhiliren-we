/**
 * SexTag.java
 * com.mytags
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.tld;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.uxuexi.core.common.enums.IEnum;
import com.uxuexi.core.common.exception.impl.ParamException;
import com.uxuexi.core.common.util.EnumUtil;

/**
 * 系统自定义标签
 * @author   LYP
 * @Date	 2014-5-13 	 
 */
public class SysTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private int code;

	private String type;

	/**
	 * TODO sys.tld right path
	 * TODO <classpathentry kind="src" path="src/main/resources"/>
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {

		@SuppressWarnings("rawtypes")
		Class c = null;
		try {
			c = Class.forName(type);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (!IEnum.class.isAssignableFrom(c)) {
			throw new ParamException("该类型不是指定的枚举");
		}
		JspWriter out = this.pageContext.getOut();
		try {
			out.println(EnumUtil.getValue(c, code));
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return super.doStartTag();

	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}
}
