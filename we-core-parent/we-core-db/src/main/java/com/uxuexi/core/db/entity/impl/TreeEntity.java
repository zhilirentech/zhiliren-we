/**
 * TreeEntity.java
 * cn.vko.dao.entity
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.db.entity.impl;

import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.lang.Lang;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.Util;

/**
 * 树状结构的实体超类
 * @author   彭文杰
 * @author   徐世超
 * @author	   赵立伟
 * @Date	 2012-5-1 	 
 */
@Data
@ToString(exclude = { "subs", "parent" })
public abstract class TreeEntity implements ITreeEntity<TreeEntity> {
	/**
	 * 父节点的id
	 */
	@Column
	@ColDefine(type = ColType.INT, width = 20)
	@Comment("父节点id")
	protected long parentId;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 100)
	@Comment("名称")
	protected String name;

	@Column
	@ColDefine(type = ColType.INT, width = 5)
	@Comment("顺序号")
	protected int orderNum;

	private boolean checked;
	/**
	 * 父节点对象
	 */
	protected TreeEntity parent;
	/**
	 * 子节点对象
	 */
	protected List<TreeEntity> subs = CollectionUtil.list();

	@Override
	public int compareTo(final TreeEntity o) {
		if (o == null) {
			return 1;
		}
		if (o.getOrderNum() > this.getOrderNum()) {
			return -1;
		}
		if (o.getOrderNum() < this.getOrderNum()) {
			return 1;
		}
		if (o.getId() > this.getId()) {
			return -1;
		}
		if (o.getId() < this.getId()) {
			return 1;
		}
		return 0;
	}

	@Override
	public TreeEntity addSub(final TreeEntity sub) {
		if (subs == null) {
			subs = Lang.list();
		}
		subs.add(sub);
		Collections.sort(subs);
		sub.setParent(this);
		return this;
	}

	@Override
	public TreeEntity removeSub(final Long removeId) {
		if (Util.isEmpty(getSubs())) {
			return this;
		}
		for (int i = 0, length = subs.size(); i < length; i++) {
			if (removeId == subs.get(i).getId()) {
				subs.remove(i);
			}
		}
		return this;
	}

	@Override
	public boolean isFirst() {
		return getParentId() == 0;

	}

	@Override
	public boolean isLast() {
		return Util.isEmpty(getSubs());
	}

	@Override
	public void clearSub() {
		subs = null;
	}
}
