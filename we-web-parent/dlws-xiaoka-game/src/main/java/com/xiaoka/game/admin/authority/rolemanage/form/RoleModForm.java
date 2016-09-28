package com.xiaoka.game.admin.authority.rolemanage.form;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.ModForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleModForm extends ModForm{
	/**角色名称*/
	@NotEmpty
	private String name;
	
	/**备注*/
	private String remark;
	
	/**创建时间*/
	private Timestamp createTime;
	
	/**角色关联的功能id列表,数字和逗号组成,可以为空*/
	@Pattern(regexp = "(\\d+,?)*") 
	private String functionIds ;
}
