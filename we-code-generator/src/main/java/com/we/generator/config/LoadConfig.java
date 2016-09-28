/**
 * LoadConfig.java
 * com.we.generator.load
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package com.we.generator.config;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 配置信息类
 * <p>
 *
 * @author   朱晓川
 * @Date	 2016年8月30日 	 
 */
public class LoadConfig {

	/**模板路径*/
	public static final String TEMPLATE_PATH = "code-generator/template/";

	/**db-ioc配置文件路径*/
	public static final String IOC_DBCFG_PATH = "/code-generator/ioc/db.js";
	public static final String IOC_KVCFG_PATH = "/code-generator/ioc/kv.js";

	/**公共的Entity所在的包名*/
	public static final String ENTITY_PKG_NAME = "entities";

	/**公共的form所在包*/
	public static final String FORM_PKG_NAME = "forms";

	/**Module所在的包名*/
	public static final String MODULE_PKG_NAME = "module";

	/**Service所在的包名*/
	public static final String SERVICE_PKG_NAME = "service";

	/**java文件输出路径*/
	public static final String JAVA_OUTPUT = "src/main/java";

	/**jsp文件输出路径*/
	public static final String JSP_OUTPUT = "src/main/webapp/WEB-INF";

	/**默认生成的方法名*/
	public static List<String> defaultMethods = Lists.newArrayList("list", "add", "update", "delete", "batchDelete");

}
