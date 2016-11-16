package com.linyun.airline.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.AddForm;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class SUserRoleMapAddForm extends AddForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**用户id*/
	private Integer userId = 0;
		
	/**角色id*/
	private Integer roleId = 0;
		
}