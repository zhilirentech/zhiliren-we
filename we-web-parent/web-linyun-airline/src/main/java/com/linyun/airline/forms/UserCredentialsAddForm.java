package com.linyun.airline.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.AddForm;
import java.util.Date;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCredentialsAddForm extends AddForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**用户id*/
	private Long userId;
		
	/**登录平台*/
	private Integer platform;
		
	/**第三方平台用户标识*/
	private String unionId;
		
	/**创建时间*/
	private Date createTime;
		
	/**上次登录时间*/
	private Date lastLoginTime;
		
}