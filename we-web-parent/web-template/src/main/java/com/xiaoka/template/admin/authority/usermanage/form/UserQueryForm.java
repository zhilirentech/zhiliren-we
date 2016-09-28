package com.xiaoka.template.admin.authority.usermanage.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.QueryForm;
import com.uxuexi.core.web.form.support.Condition;
import com.uxuexi.core.web.form.support.Condition.MatchType;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserQueryForm extends QueryForm {
	
	/**
	 * 姓名
	 */
	@Condition(match = MatchType.LIKE)
	private String username ;

}
