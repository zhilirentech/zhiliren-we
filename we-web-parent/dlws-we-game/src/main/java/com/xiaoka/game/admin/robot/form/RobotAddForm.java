package com.xiaoka.game.admin.robot.form;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

import com.xiaoka.game.common.access.AccessConfig;
import com.xiaoka.game.common.access.sign.MD5;
import com.xiaoka.game.common.constants.CommonConstants;
import com.xiaoka.game.common.enums.IsDelEnum;
import com.xiaoka.game.common.enums.UserStatusEnum;
import com.xiaoka.game.common.enums.UserTypeEnum;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class RobotAddForm extends AddForm {
	/**
	 * 机器人openId
	 */
	private String openId = CommonConstants.XIAOKA_ROBOT_PREFIX+DateTimeUtil.millis();
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
	/**
	 * 创建时间
	 */
	private Timestamp createTime= DateTimeUtil.nowDateTime();
	/**
	 * 是否删除
	 */
	private Integer isDel = IsDelEnum.IS.intKey();
	
}
