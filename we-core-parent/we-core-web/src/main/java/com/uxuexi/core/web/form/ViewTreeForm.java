/**
 * ViewTreeForm.java
 * com.uxuexi.form
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form;

import lombok.Data;

/**
 * 树的viewform
 * 
 * @author 徐世超
 * @Date 2012-5-17
 */
@Data
public class ViewTreeForm implements IViewForm {
	private long parentId;
	private String parentName;
	private String name;
	protected int orderNum;
}
