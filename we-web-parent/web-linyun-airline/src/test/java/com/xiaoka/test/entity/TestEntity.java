package com.xiaoka.test.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("test")
@Comment("测试表")
public class TestEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column()
	@Comment("名称")
	private String name;
}
