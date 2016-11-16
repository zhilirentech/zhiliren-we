package com.linyun.airline.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Table("dict_type")
public class DictTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Integer id;
	
	@Column
    @Comment("字典类别代码")
	private String typeCode;
	
	@Column
    @Comment("字典类别名称")
	private String typeName;
	
	@Column
    @Comment("描述")
	private String description;
	
	@Column
    @Comment("状态,0-冻结，1-启用，2--删除")
	private Integer status;
	

}