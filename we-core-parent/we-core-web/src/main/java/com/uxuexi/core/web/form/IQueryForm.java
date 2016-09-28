/**
 * IQueryForm.java
 * com.uxuexi.form
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form;

import org.nutz.dao.Cnd;

/**
 * QueryForm的接口
 * <p>
 * 具有查询表单行为
 * @author 庄君祥
 * @Date 2012-5-10
 */
public interface IQueryForm {
	public Cnd createCnd();
}
