package com.xiaoka.template.admin.login.form;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data
public class LoginForm {
	
	/**登录名*/
	@NotEmpty
	private String loginName ;
	
	/**密码*/
	@NotEmpty
	private String  password ;
	
	/**
	 * 验证码
	 */
	@NotEmpty
	private String validateCode ;
	
	/**错误消息*/
	private String errMsg ;

}
