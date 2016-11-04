/**
 * FunctionEntity.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.game.admin.activity.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author  董锡福
 * @Date	 2014-10-21	 
 */
@Data
@Table("up_activity")
@Comment
public class ActivityEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("活动标题")
	private String title ;
	
	@Column
	@Comment("内容")
	private String content ;
	
	@Column
	@Comment("活动封面（图片的url）")
	private String cover;
	
	@Column
	@Comment("活动状态（0：未开始，1：正在进行，2：已结束）")
	private int status ;
	
	@Column
	@Comment("创建人")
	private String founder;
	
	@Column()
	@Comment("活动开始时间")
	private Timestamp beginTime;
	
	@Column()
	@Comment("活动结束时间")
	private Timestamp endTime;
	
	@Column()
	@Comment("创建时间")
	private Timestamp createTime ;
	
	@Column()
	@Comment("是否删除")
	private int isDel ;
}
