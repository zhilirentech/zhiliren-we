package com.xiaoka.game.wap.home.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.web.form.AddForm;
import com.xiaoka.game.common.enums.IsDelEnum;

@Data
public class AttendForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 充值总金额额
	 */
	private Double totalMoney;
	/**
	 * 选择签到日期
	 */
	private String attendDays;
	


}
