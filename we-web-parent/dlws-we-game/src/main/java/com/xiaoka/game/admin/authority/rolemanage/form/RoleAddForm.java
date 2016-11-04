package com.xiaoka.game.admin.authority.rolemanage.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleAddForm extends AddForm{
	
	/**角色名称*/
	private String name;
	
	/**备注*/
	private String remark;
	
	/**创建时间*/
	private Timestamp createTime;
}
