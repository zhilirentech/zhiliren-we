package com.xiaoka.template.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Table("carousel")
public class CarouselEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Long id;
	
	@Column
    @Comment("图片地址")
	private String url;
	
	@Column
    @Comment("外链地址")
	private String link;
	
	@Column
    @Comment("描述")
	private String description;
	
	@Column
    @Comment("序号")
	private Integer sort;
	

}