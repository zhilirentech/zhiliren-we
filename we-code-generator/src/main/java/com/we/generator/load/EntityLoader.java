/**
 * EntityLoader.java
 * com.we.generator.load
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package com.we.generator.load;

import java.util.Map;

import org.nutz.ioc.Ioc;

/**
 * 加载实体信息
 * <p>
 *
 * @author   朱晓川
 * @Date	 2016年8月29日 	 
 */
public interface EntityLoader {

	/**
	 * 加载实体信息
	 * <p>
	 *
	 * @param ioc        Ioc容器?这个参数有点问题，不应该依赖于容器
	 * @param basePkg    基础包（比如com.xiaoka.game）
	 * @param baseUri    基础url
	 * @param entityPkg  实体所在的包名（比如entity）
	 */
	public abstract Map<String, EntityDescriptor> load(Ioc ioc, String basePkg, String baseUri, String entityPkg)
			throws Exception;

}
