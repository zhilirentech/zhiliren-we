package com.xiaoka.game.admin.bulletin.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("up_bulletin")
@Comment("公告")
public class BulletinEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column()
	@Comment("类型")
	private int type;
	
	@Column()
	@Comment("内容")
	private String content;
	
	@Column()
	@Comment("是否删除")
	private Integer isDel;
	
}
