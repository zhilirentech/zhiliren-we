package com.linyun.airline.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.AddForm;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictInfoAddForm extends AddForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**字典类别代码*/
	private String typeCode = "";
		
	/**字典代码*/
	private String dictCode = "";
		
	/**字典信息*/
	private String dictName = "";
		
	/**描述*/
	private String description = "";
		
	/**字典信息状态,0-冻结，1-启用，2--删除*/
	private Integer status = 1;
		
}