package com.xiaoka.game.admin.authority.rolemanage.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.QueryForm;
import com.uxuexi.core.web.form.support.Condition;
import com.uxuexi.core.web.form.support.Condition.MatchType;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleQueryForm extends QueryForm{
	
	/**角色名称*/
	@Condition(match = MatchType.LIKE)
	private String name;
}
