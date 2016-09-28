/**
 * IModifyEntity.java
 * com.uxuexi.webbmp.db.interfaces
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.entity;

import java.sql.Timestamp;

/**
 * 修改的实体父类 
 * TODO
 * 获取修改人姓名这两个接口，我比较纠结
 * <p>
 * 1、这个姓名是否指的是修改人的唯一标识：比如前台的nickName,后台的realName
 * 2、允许不允许 修改，如果允许修改的话，这两个接口只是记录历史标识
 *
 * @author   庄君祥
 * @Date     2013-12-6
 */
public interface IUpdateEntity {
	/**
	 * 获取修改人主键
	 *
	 * @return 修改人主键
	*/
	public long getUpdaterId();

	/**
	 * 设置修改人主键
	 *
	 * @param updateId 修改人主键
	*/
	public void setUpdaterId(final long updaterId);

	//	/**
	//	 * 获取修改人姓名
	//	 *
	//	 * @return 修改人姓名
	//	*/
	//	public String getUpdateName();
	//
	//	/**
	//	 * 设置修改人姓名
	//	 *
	//	 * @param updateTime 修改人姓名
	//	*/
	//	public void setUpdateName(final String updateName);

	/**
	 * 获取修改时间
	 *
	 * @return 修改时间
	*/
	public Timestamp getUpdateTime();

	/**
	 * 设置修改时间
	 *
	 * @param updateName 修改时间
	*/
	public void setUpdateTime(final Timestamp updateTime);
}
