package com.xiaoka.game.admin.bulletin.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.QueryForm;
import com.uxuexi.core.web.form.support.Condition;

@Data
@EqualsAndHashCode(callSuper = true)
public class BulletinQueryForm extends QueryForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 类型
	 */
	@Condition
	private Integer type;
	
}
