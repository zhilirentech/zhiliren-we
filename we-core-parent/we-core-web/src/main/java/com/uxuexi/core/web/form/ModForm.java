/**
 * ModForm.java
 * com.uxuexi.form
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form;

import javax.validation.constraints.Min;

import com.uxuexi.core.db.dao.IDbDao;

import lombok.Data;

/**
 * 所有modForm的超类
 * 
 * @author 庄君祥
 * @Date 2012-5-4
 */
@Data
public class ModForm implements IModForm {
	@Min(1)
	private long id;

	@Override
	public void check(final IDbDao dbDao) {

	}
}
