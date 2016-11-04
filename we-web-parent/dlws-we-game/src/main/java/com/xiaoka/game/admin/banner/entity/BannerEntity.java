package com.xiaoka.game.admin.banner.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("up_banner")
@Comment("轮播图")
public class BannerEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;

	@Column()
	@Comment("标题")
	private String title;

	@Column()
	@Comment("图片地址")
	private String imgUrl;
	
	@Column()
	@Comment("类型")
	private int type;
	
	@Column()
	@Comment("序号")
	private Integer sort;
	
	@Column()
	@Comment("状态")
	private Integer status;
	
	@Column()
	@Comment("是否删除")
	private Integer isDel;
	
}
