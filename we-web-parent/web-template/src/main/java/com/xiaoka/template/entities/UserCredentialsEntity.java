package com.xiaoka.template.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;


@Data
@Table("user_credentials")
public class UserCredentialsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Integer id;
	
	@Column
    @Comment("用户id")
	private Long userId;
	
	@Column
    @Comment("登录平台")
	private Integer platform;
	
	@Column
    @Comment("第三方平台用户标识")
	private String unionId;
	
	@Column
    @Comment("创建时间")
	private Date createTime;
	
	@Column
    @Comment("上次登录时间")
	private Date lastLoginTime;
	

}