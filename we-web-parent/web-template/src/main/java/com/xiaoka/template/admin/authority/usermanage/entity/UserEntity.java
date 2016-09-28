/**
 * UserEntity.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.template.admin.authority.usermanage.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.xiaoka.template.common.enums.UserStatusEnum;
import com.xiaoka.template.common.enums.UserTypeEnum;
import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.common.util.Util;

/**
 * 用户实体
 * @author   朱晓川
 * @Date	 2014-10-21	 
 */
@Data
@Table("s_user")
@Comment
public class UserEntity{
	
	/**
	 * 主键
	 */
	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("登录名")
	private String username;
	
	@Column
	@Comment("登录密码")
	private String password;
	
	/**
	 * @see UserStatusEnum
	 */
	@Column
	@Comment("用户状态:0-未激活，1-激活，2-冻结")
	private Integer status;
	
	/**
	 * @see UserTypeEnum
	 */
	@Column
	@Comment("用户类型:0-前台用户，1-后台用户")
	private Integer userType;
	
	@Column
	@Comment("创建时间")
	private Timestamp createTime;
	
	/**用户类型名称*/
	private String userTypeName;
	
	/**用户状态名称*/
	private String userStatusName ;
	
	public String getUserStatusName(){
		if(!Util.isEmpty(status)){
			userStatusName = EnumUtil.getValue(UserStatusEnum.class, status);
		}
		return userStatusName ;
	}
	
	public String getUserTypeName(){
		if(!Util.isEmpty(userType)){
			userTypeName = EnumUtil.getValue(UserTypeEnum.class, userType) ;
		}
		return userTypeName ;
	}
	
}
