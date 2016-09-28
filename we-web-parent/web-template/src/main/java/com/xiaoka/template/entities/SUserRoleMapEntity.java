package com.xiaoka.template.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Table("s_user_role_map")
public class SUserRoleMapEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Integer id;
	
	@Column
    @Comment("用户id")
	private Integer userId = 0;
	
	@Column
    @Comment("角色id")
	private Integer roleId = 0;
	

}