/**
 * ICreateEntity.java
 * com.uxuexi.webbmp.db.interfaces
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.entity;

import java.sql.Timestamp;

/**
 * 新添的实体父类 
 *
 * @author   庄君祥
 * @Date     2013-12-6
 */
public interface ICreateEntity {
	/**
	 * 获取创建人主键
	 *
	 * @return 创建人主键
	*/
	public long getCreaterId();

	/**
	 * 设置创建人主键
	 *
	 * @param createId 创建人主键
	*/
	public void setCreaterId(final long createrId);

	/**
	 * 获取创建时间
	 *
	 * @return 创建时间
	*/
	public Timestamp getCreateTime();

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	*/
	public void setCreateTime(final Timestamp createTime);

	//	/**
	//	 * 获取创建人姓名
	//	 *
	//	 * @return 创建人姓名
	//	*/
	//	public String getCreateName();
	//
	//	/**
	//	 * 设置创建人姓名
	//	 *
	//	 * @param createName 创建人姓名
	//	*/
	//	public void setCreateName(final String createName);
}
