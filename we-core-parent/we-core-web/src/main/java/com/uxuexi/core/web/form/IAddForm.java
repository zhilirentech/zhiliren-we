/**
 * IAddForm.java
 * com.uxuexi.form
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form;

import com.uxuexi.core.db.dao.IDbDao;

/**
 * AddForm的接口
 * <p>
 * 标识位
 * <p>
 * 具有add行为
 * @author 庄君祥
 * @Date 2012-5-10
 */
public interface IAddForm {
	public void check(final IDbDao dbDao);
}
