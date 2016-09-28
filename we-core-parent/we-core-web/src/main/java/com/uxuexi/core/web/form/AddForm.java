/**
 * AddForm.java
 * com.uxuexi.web.form
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.form;

import com.uxuexi.core.db.dao.IDbDao;

/**
 * 默认的新增form，提供空的check方法
 * @author   庄君祥
 * @Date	 2013-10-11 	 
 */
public class AddForm implements IAddForm {

	@Override
	public void check(final IDbDao dbDao) {

	}

}
