package com.xiaoka.game.admin.authority.usermanage.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.xiaoka.game.admin.authority.usermanage.entity.UserRoleMap;
import com.xiaoka.game.common.access.AccessConfig;
import com.xiaoka.game.common.access.sign.MD5;
import com.xiaoka.game.common.enums.UserStatusEnum;
import com.xiaoka.game.common.enums.UserTypeEnum;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.ModForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserModForm extends ModForm{
	
	/**
	 * 登录名
	 */
	@NotEmpty
	private String username;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 用户状态:0-未激活，1-激活，2-冻结
	 * @see UserStatusEnum
	 */
	@Range(min =0,max = 2)
	private Integer status;
	
	/**
	 * 用户类型:0-前台用户，1-后台用户
	 * @see UserTypeEnum
	 */
	@Range(min =0,max = 1)
	private Integer userType;
	
	/**用户的角色*/
	private List<UserRoleMap> map = new ArrayList<UserRoleMap>() ;
	
	public void setPassword(String password){
		if(!Util.isEmpty(password))
			this.password = MD5.sign(password, AccessConfig.password_secret, AccessConfig.INPUT_CHARSET);
	}
}
