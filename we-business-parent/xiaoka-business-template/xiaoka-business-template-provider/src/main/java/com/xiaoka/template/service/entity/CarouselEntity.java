package com.xiaoka.template.service.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("carousel")
@Comment("轮播图")
public class CarouselEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;

	@Column()
	@Comment("图片地址")
	private String url;

	@Column()
	@Comment("外链地址")
	private String link;

	@Column()
	@Comment("描述")
	private String description;

	@Column()
	@Comment("序号")
	private Integer sort;
}
