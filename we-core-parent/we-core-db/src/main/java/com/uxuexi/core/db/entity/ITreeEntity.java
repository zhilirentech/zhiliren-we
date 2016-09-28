/**
 * ITreeEntity.java
 * com.uxuexi.core.db.entity.interfaces
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.entity;

/**
 * 树状结构对象的通用接口
 * <p>
 * TODO 先简单，如果有用到的时候，必须加起来再加，建议如果和业务相关的别放这里。
 * <p>
 * 比如原来的getTValue()方法。有特殊功能自己实现去吧
 * TODO
 * <p>
 * 其他获取子节点等的，再讨论
 *
 * @author   庄君祥
 * @Date	 2013-12-6 	 
 */
public interface ITreeEntity {
	/**
	 * 获取父节点id
	 *
	 * @return 父节点id
	 */
	public long getParentId();

	/**
	 * 设置父节点id
	 */
	public void setParentId(final long pagentId);

	/**
	 * 获取顺序号
	 *
	 * @return 顺序号
	 */
	public int getOrderNum();
}
