package com.xiaoka.game.admin.keyvalue.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("up_key_value")
@Comment("公告")
public class KeyValueEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column()
	@Comment("键")
	private String name;
	
	@Column()
	@Comment("值")
	private String nameVal;
	
	@Column()
	@Comment("是否删除")
	private Integer isDel;
	
}
