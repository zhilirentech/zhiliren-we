/**
 * OperateTreeEntity.java
 * com.uxuexi.core.db.entity.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.entity.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;

import com.uxuexi.core.db.entity.ITreeEntity;

/**
 * 操作时需要记录实体的父类
 * 
 * @author   庄君祥
 * @Date	 Mar 13, 2014 	 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperateTreeEntity extends OperateEntity implements ITreeEntity {
	@Column
	@Comment("父节点主键")
	@ColDefine(type = ColType.INT, width = 20, notNull = true)
	private long parentId;
	@Column
	@Comment("父节点主键")
	@ColDefine(type = ColType.INT, width = 10, notNull = true)
	private int orderNum;

}
