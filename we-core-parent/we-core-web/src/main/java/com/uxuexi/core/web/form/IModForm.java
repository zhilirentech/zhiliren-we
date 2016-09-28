/**
 * IModForm.java
 * com.uxuexi.form
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form;

import com.uxuexi.core.db.dao.IDbDao;

/**
 * ModForm接口
 * <p>
 * 具有修改表单行为
 * 新的接口统一使用命名 update @see IUpdateForm
 * @author 庄君祥
 * @Date 2012-5-10
 */
@Deprecated
public interface IModForm {
	public long getId();

	public void check(final IDbDao dbDao);
}
