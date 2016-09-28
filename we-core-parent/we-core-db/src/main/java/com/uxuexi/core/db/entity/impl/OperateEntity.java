/**
 * AbstractEntity.java
 * com.uxuexi.webbmp.entity
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.entity.impl;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;

import com.uxuexi.core.db.entity.ICreateEntity;
import com.uxuexi.core.db.entity.IUpdateEntity;

/**
 * 操作时需要记录实体的父类
 * 
 * @author   庄君祥
 * @Date     2013-9-23
 */
@Data
public class OperateEntity implements ICreateEntity, IUpdateEntity {

	@Column
	@Comment("创建者主键")
	@ColDefine(type = ColType.INT, width = 20, notNull = true)
	private long createrId;

	//	@Column
	//	@Comment("创建者名称")
	//	@ColDefine(type = ColType.VARCHAR, width = 50, notNull = true)
	//	private String createName;

	@Column
	@Comment("创建时间")
	@ColDefine(type = ColType.DATETIME, notNull = true)
	private Timestamp createTime;

	@Column
	@Comment("修改者主键")
	@ColDefine(type = ColType.INT, width = 20)
	private long updaterId;

	//	@Column
	//	@Comment("修改者名称")
	//	@ColDefine(type = ColType.VARCHAR, width = 50)
	//	private String updateName;

	@Column
	@Comment("修改时间")
	@ColDefine(type = ColType.DATETIME)
	private Timestamp updateTime;

}
