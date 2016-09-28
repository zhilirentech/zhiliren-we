/**
 * FunctionEntity.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.game.admin.signdays.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author  董锡福
 * @Date	 2016-08-11 
 */
@Data
@Table("up_sign_days")
@Comment
public class SignDaysEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("微信用户id")
	private String openId ;
	
	@Column
	@Comment("连续签到天数")
	private Integer signDays ;
	
	@Column
	@Comment("签到时间")
	private Date signTime ;
	
}
