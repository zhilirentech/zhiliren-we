package com.linyun.airline.admin.authority.usermanage.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

import com.linyun.airline.common.access.AccessConfig;
import com.linyun.airline.common.access.sign.MD5;
import com.linyun.airline.common.enums.UserStatusEnum;
import com.linyun.airline.common.enums.UserTypeEnum;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserAddForm extends AddForm {
	
	/**
	 * 登录名
	 */
	@NotEmpty
	private String username;
	
	/**
	 * 登录密码
	 */
	@NotEmpty
	private String password;
	
	/**
	 * 用户状态:0-未激活，1-激活，2-冻结
	 * @see UserStatusEnum
	 */
	private boolean status;
	/**
	 * 用户类型:0-前台用户，1-后台用户
	 * @see UserTypeEnum
	 */
	private Integer userType;
	
	/**创建时间*/
	private Timestamp createTime;
	
	public void setPassword(String password){
		if(!Util.isEmpty(password))
			this.password = MD5.sign(password, AccessConfig.password_secret, AccessConfig.INPUT_CHARSET);
	}
}
