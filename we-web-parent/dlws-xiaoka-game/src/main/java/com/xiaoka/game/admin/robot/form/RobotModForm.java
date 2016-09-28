package com.xiaoka.game.admin.robot.form;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.ModForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class RobotModForm extends ModForm {
	/**
	 * 开始参于日期
	 */
	private Date beginDate;
	
	/**
	 * 参于天数
	 */
	private Integer days;
	
	/**
	 * 参于金额
	 */
	private Double inAmount;
	/**
	 *状态
	 */
	private Integer status;
	
}
