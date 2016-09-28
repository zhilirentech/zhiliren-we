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

import com.uxuexi.core.db.entity.IUpdateEntity;

/**
 * 修改时需要记录实体的父类
 * 
 * @author   庄君祥
 * @Date     2013-9-23
 */
@Data
public class UpdateEntity implements IUpdateEntity {

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
