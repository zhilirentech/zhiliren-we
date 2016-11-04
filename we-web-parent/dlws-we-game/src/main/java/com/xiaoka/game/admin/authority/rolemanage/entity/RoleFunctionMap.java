/**
 * RoleFunctionMap.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.game.admin.authority.rolemanage.entity;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 角色功能关系实体
 * @author   朱晓川
 * @Date	 2014-10-21	 
 */
@Table("s_role_function_map")
@Data
@Comment
public class RoleFunctionMap {
	
	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("角色id")
	private long roleId;
	
	@Column
	@Comment("功能id")
	private long functionId;
	
	
}
