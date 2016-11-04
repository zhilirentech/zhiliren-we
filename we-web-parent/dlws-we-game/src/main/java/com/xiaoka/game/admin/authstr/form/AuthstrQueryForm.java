package com.xiaoka.game.admin.authstr.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.QueryForm;
import com.uxuexi.core.web.form.support.Condition;
import com.uxuexi.core.web.form.support.Condition.MatchType;
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthstrQueryForm extends QueryForm{
	/**
	 * openId
	 */
	@Condition(match = MatchType.LIKE)
	private String openId ;
}
