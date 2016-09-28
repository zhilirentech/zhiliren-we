/**
 * ITreeEntity.java
 * cn.vko.dao.entity
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.db.entity.impl;

import java.util.List;

/**
 * 树状结构对象的通用接口
 * 
 * @author   彭文杰
 * @Date	 2012-5-1 	 
 */
public interface ITreeEntity<T> extends Comparable<T> {
	/**
	 * 获取父节点id
	 *
	 * @return 父节点id
	 */
	public long getParentId();

	/**
	 * 设置父节点id
	 */
	public void setParentId(final long id);

	/**
	 * 获取父节点
	 * 
	 * @return 父节点
	 */
	public T getParent();

	/**
	 * 设置父节点
	 */
	public void setParent(final T parent);

	/**
	 * 获取当前id
	 *
	 * @return 当前对象id
	 */
	public long getId();

	/**
	 * 获取显示在树控件的名称
	 *
	 * @return 名称
	 */
	public String getTName();

	/**
	 * 获取显示在树控件值
	 *
	 * @return 值
	 */
	public String getTValue();

	/**
	 * 获取显示在树控件是否勾选
	 *
	 * @return 是否勾选
	 */
	public boolean getChecked();

	/**
	 * 获取顺序号
	 *
	 * @return 顺序号
	 */
	public int getOrderNum();

	/**
	 * 获取子列表
	 *
	 * @return 当前对象的子列表
	 */
	public List<T> getSubs();

	/**
	 * 新增子对象
	 *
	 * @param sub 子对象
	 * @return 当前对象
	 */
	public T addSub(final T sub);

	/**
	 * 移出对应对象
	 *
	 * @param removeId 要移出对象的id
	 * @return 当前对象
	 */
	public T removeSub(final Long removeId);

	/**
	 * 判断是否是第一层
	 * @return 是否是第一层
	 */
	public boolean isFirst();

	/**
	 * 判断是否是最末
	 * @return 是否是最末
	 */
	public boolean isLast();

	/**
	 * 清空所有子节点
	 */
	public void clearSub();
}
