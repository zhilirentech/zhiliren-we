package com.linyun.airline.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Table("dict_info")
public class DictInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Integer id;
	
	@Column
    @Comment("字典类别代码")
	private String typeCode = "";
	
	@Column
    @Comment("字典代码")
	private String dictCode = "";
	
	@Column
    @Comment("字典信息")
	private String dictName = "";
	
	@Column
    @Comment("描述")
	private String description = "";
	
	@Column
    @Comment("字典信息状态,0-冻结，1-启用，2--删除")
	private Integer status = 1;
	

}