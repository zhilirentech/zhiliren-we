package com.xiaoka.game.admin.banner.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.QueryForm;
import com.uxuexi.core.web.form.support.Condition;

@Data
@EqualsAndHashCode(callSuper = true)
public class BannerQueryForm extends QueryForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标题
	 */
	@Condition
	private String title;
	/**
	 * 类型
	 */
	@Condition
	private Integer type;
	
}
