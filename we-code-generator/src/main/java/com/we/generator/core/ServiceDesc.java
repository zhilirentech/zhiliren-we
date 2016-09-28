/**
 * ModuleDesc.java
 * com.we.generator.core
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package com.we.generator.core;

import lombok.Data;

/**
 * 服务类文件描述
 * @author   朱晓川
 * @Date	 2016年8月31日 	 
 */
@Data
public class ServiceDesc {

	/**
	 * 服务类文件所在的完整包名
	 */
	private String packageName;

	/**
	 * 服务类名
	 */
	private String serviceClassName;

	/**
	 * 服务默认相关的实体名
	 */
	private String entityClassName;

	/**
	 * 相关实体的完整类名，用于import
	 */
	private String fullEntityClassName;

}
