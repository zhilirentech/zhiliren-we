/**
 * RoleEntity.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.game.admin.authority.rolemanage.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 角色实体
 * @author   朱晓川
 * @Date	 2014-10-21	 
 */
@Table("s_role")
@Data
@Comment
public class RoleEntity {
	
	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("角色名称")
	private String name;
	
	@Column
	@Comment("备注")
	private String remark;
	
	@Column()
	@Comment("创建时间")
	private Timestamp createTime;
	
	/**标记该角色是否属于某个登录用户*/
	private boolean checked = false ;

}
