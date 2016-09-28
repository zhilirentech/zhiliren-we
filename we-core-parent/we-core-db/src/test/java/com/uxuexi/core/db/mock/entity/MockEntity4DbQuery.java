/**
 * DbQueryMockEntity.java
 * com.uxuexi.core.db.mock.entity
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.mock.entity;

import lombok.Data;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 数据库查询mock实体
 *
 * @author   庄君祥
 * @Date	 2013-12-9 	 
 */
@Table("mock_entity_4_query")
@Comment("数据库mock实体")
@Data
public class MockEntity4DbQuery {
	@Column
	@ColDefine(type = ColType.INT, width = 20, notNull = true)
	@Comment("主键")
	@Id
	private long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 50, notNull = true)
	@Comment("名称")
	private String name;

	public MockEntity4DbQuery() {
	}

	public MockEntity4DbQuery(final String name) {
		this.name = name;
	}

}
