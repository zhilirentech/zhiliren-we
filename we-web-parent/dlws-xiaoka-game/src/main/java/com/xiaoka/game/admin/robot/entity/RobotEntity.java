/**
 * FunctionEntity.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.game.admin.robot.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author  wzg
 * @Date	 2016-08-11 
 */
@Data
@Table("up_robot")
@Comment
public class RobotEntity  implements Serializable{
	
	
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("机器人openId")
	private String openId ;
	
	@Column
	@Comment("投入金额")
	private Double inAmount ;
	
	@Column
	@Comment("开始参于日期")
	private Date beginDate;
	
	@Column
	@Comment("参于天数")
	private Integer days;
	
	@Column
	@Comment("状态")
	private Integer status;
	
	@Column
	@Comment("创建时间")
	private Timestamp createTime;
	
	@Column
	@Comment("是否删除")
	private Integer isDel ;

}
