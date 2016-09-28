/**
 * ModTreeForm.java
 * com.uxuexi.form
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修改树的form
 * 
 * @author 徐世超
 * @Date 2012-5-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModTreeForm extends ModForm {
	/**
	 * 名称
	 */
	@NotEmpty
	private String name;
	/**
	 * 顺序号
	 */
	@Min(value = 1)
	protected int orderNum;
}
