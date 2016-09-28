package com.xiaoka.template.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.AddForm;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictRelationAddForm extends AddForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**字典id*/
	private Integer sourceId;
		
	/**关联字典id*/
	private Integer targetId;
		
}