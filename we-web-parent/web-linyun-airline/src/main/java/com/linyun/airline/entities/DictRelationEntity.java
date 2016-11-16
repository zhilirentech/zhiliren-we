package com.linyun.airline.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Table("dict_relation")
public class DictRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Integer id;
	
	@Column
    @Comment("字典id")
	private Integer sourceId;
	
	@Column
    @Comment("关联字典id")
	private Integer targetId;
	

}