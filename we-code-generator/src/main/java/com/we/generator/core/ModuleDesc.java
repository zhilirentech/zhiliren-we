/**
 * ModuleDesc.java
 * com.we.generator.core
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package com.we.generator.core;

import java.util.List;

import lombok.Data;

import com.google.common.collect.Lists;
import com.uxuexi.core.common.util.StringUtil;

/**
 * 模块描述
 * @author   朱晓川
 * @Date	 2016年8月31日 	 
 */
@Data
public class ModuleDesc {

	/**
	 * 类文件所在的完整包名
	 */
	private String packageName;

	/**
	 * 类名
	 */
	private String moduleClassName;

	/**返回的视图*/
	private String viewType;

	/**module的访问地址*/
	private String atUrl;

	/**
	 * 完整服务类名
	 */
	private String serviceFullClassName;

	/**
	 * 服务类名
	 */
	private String serviceClassName;

	/**
	 * 服务实例名
	 */
	private String serviceInstanceName;

	/**
	 * 服务默认相关的实体名
	 */
	private String entityClassName;

	/**
	 * 相关实体的完整类名，用于import
	 */
	private String fullEntityClassName;

	private List<ActionDesc> actionList = Lists.newArrayList();

	public String getFormName() {
		return StringUtil.trimRight(entityClassName, "Entity") + "Form";
	}

	public String getFullFormName() {
		return StringUtil.trimRight(fullEntityClassName, "entities") + "forms." + getFormName();
	}

	public String getAddFormName() {
		return StringUtil.trimRight(entityClassName, "Entity") + "AddForm";
	}

	public String getFullAddFormName() {
		return StringUtil.trimRight(fullEntityClassName, "entities") + "forms." + getAddFormName();
	}

	public String getUpdateFormName() {
		return StringUtil.trimRight(entityClassName, "Entity") + "UpdateForm";

	}

	public String getFullUpdateFormName() {
		return StringUtil.trimRight(fullEntityClassName, "entities") + "forms." + getUpdateFormName();
	}

}
