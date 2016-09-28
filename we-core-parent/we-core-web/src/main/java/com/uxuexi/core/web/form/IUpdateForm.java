/**
 * IUpdateForm.java
 * com.uxuexi.core.web.form
 * Copyright (c) 2015, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.form;

import com.uxuexi.core.db.dao.IDbDao;

/**
 * UpdateForm接口
 * <p>
 * 具有修改表单行为
 *
 * @author   xzq
 * @Date	 Mar 9, 2015 	 
 */
public interface IUpdateForm {
	public long getId();

	public void check(final IDbDao dbDao);
}
