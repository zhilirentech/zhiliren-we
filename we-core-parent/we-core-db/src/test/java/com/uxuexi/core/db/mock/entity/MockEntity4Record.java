/**
 * DbMockEntity.java
 * com.uxuexi.core.db.mock.entity
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.mock.entity;

import java.sql.Date;

import lombok.Data;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 数据库mock实体
 *
 * @author   庄君祥
 * @Date	 2013-12-10 	 
 */
@Table("mock_entity_4_record")
@Comment("数据库mock实体")
@Data
public class MockEntity4Record {
	@Column
	@ColDefine(type = ColType.INT, width = 20, notNull = true)
	@Comment("主键")
	@Id
	private long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 50, notNull = true)
	@Comment("名称")
	private Date date;
	@Column
	@ColDefine(type = ColType.FLOAT, width = 10, precision = 2, notNull = true)
	@Comment("类型")
	private double price;
	@Column
	@ColDefine(type = ColType.BOOLEAN, notNull = true)
	@Comment("状态")
	private boolean valid;

	public MockEntity4Record() {
	}

	public MockEntity4Record(final Date date, final double price, final boolean valid) {
		this.date = date;
		this.price = price;
		this.valid = valid;
	}

}
