/**
 * IFetchUser.java
 * com.uxuexi.webbmp.db.interfaces
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.dao.impl.support;

/**
 * 用来取当前用户信息接口
 *
 * @author   庄君祥
 * @Date	 2013-9-24 	 
 */
public interface IFetchUser {
	/**
	 * 获取当前用户id
	 *
	 * @return 当前用户id
	 */
	public long getCurrentUserId();

	/**
	 * 获取当前用户id
	 *
	 * @return 当前用户id
	 */
	public long getCurrentUserIdWithEx();
}
