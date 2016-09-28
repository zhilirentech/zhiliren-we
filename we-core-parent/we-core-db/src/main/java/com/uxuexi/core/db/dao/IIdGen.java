/**
 * IIdGen.java
 * com.uxuexi.core.db.dao
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.dao;

/**
 * 主键生成策略
 *
 * @author   庄君祥
 * @Date	 2013-12-7 	 
 */
public interface IIdGen {
	/**
	 * 获取主键
	 *
	 * @return 主键
	*/
	public long getId();
}
