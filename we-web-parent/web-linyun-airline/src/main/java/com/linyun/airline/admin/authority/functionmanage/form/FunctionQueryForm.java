package com.linyun.airline.admin.authority.functionmanage.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.QueryForm;
import com.uxuexi.core.web.form.support.Condition;
import com.uxuexi.core.web.form.support.Condition.MatchType;

@Data
@EqualsAndHashCode(callSuper = true)
public class FunctionQueryForm extends QueryForm{
	
	/**功能名称*/
	@Condition(match = MatchType.LIKE)
	private String name ;
	
}
